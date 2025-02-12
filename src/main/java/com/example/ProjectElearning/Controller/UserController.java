package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Exception.AccessDeniedException;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.UserRepository;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import com.example.ProjectElearning.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoderr;

    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> createUser(@PathVariable Long id,@Valid @RequestBody UserDTO user) {


        UserType userType=userTypeRepository.findById(id).get();
        User user1=new User();
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setGender(user.getGender());
        user1.setUserType(userType);
        List<User> users=userType.getUsers();
        users.add(user1);
        userType.setUsers(users);

        userTypeRepository.save(userType);

        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<User> > getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        System.out.println("id hai "+id);
        User user=userService.getUserById(id);
        UserResponseDTO userResponseDTO=new UserResponseDTO(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getGender(),
                user.getUserType().getType()
        );
        userResponseDTO.setPassword(user.getPassword());
        userResponseDTO.setImage(user.getImage());
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }


//    @PutMapping("/{id}")
//    public  ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO userDTO) {
//
//        User updatedUser = userService.updateUser(id,userDTO);
//        return updatedUser != null ? new ResponseEntity<User>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser2( @PathVariable Long id,
                                              @RequestPart("userInfo") String userInfo,
                                              @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {


        User user = userRepository.findByUserid(id);
        UserRequestDTO dto = new ObjectMapper().readValue(userInfo, UserRequestDTO.class);
        user.setFirstname(dto.getFirstName());
        user.setLastname(dto.getLastName());
        user.setGender(dto.getGender());


        if (file != null && !file.isEmpty()) {
            user.setImage(file.getBytes());
        }

        User updatedUser = userRepository.save(user);
        return updatedUser != null ? new ResponseEntity<User>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getCourseImage(@PathVariable Long id) {
        User course = userService.getUserById(id);
        byte[] image = course.getImage();

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/password/{id}")
    public ResponseEntity<HttpStatus> updatePassword(@PathVariable Long id,@RequestBody PasswordDTO passwordDTO){
        User user=userRepository.findByUserid(id);
        try{
            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),passwordDTO.getCurrentPassword()));
        }catch (Exception e){
            throw new AccessDeniedException("Incorrect Current Password!!");
        }

        System.out.println("pass"+passwordDTO.getCurrentPassword());
        System.out.println("current "+user.getPassword());
        System.out.println("new "+encoderr.encode(passwordDTO.getCurrentPassword()));

            user.setPassword(encoderr.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
