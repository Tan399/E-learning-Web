package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Model.UserDTO;
import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Repository.UserRepository;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import com.example.ProjectElearning.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }


    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO userDTO) {

        User updatedUser = userService.updateUser(id,userDTO);
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
