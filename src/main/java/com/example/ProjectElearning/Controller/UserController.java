package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Repository.UserRepository;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<User> createUser(@PathVariable Long id,@RequestBody User user) {
        UserType userType=new UserType();
        userType.setId(id);
        if(id==1){
            userType.setType("User");
        }else{
            System.out.println("entered");
            userType.setType("Instructor");
        }
            User user1=userService.createUser(user);
        user1.setUserType(userType);
        List<User> users=userTypeRepository.findById(id).get().getUsers();
        users.add(user1);
        userType.setUsers(users);
        userRepository.save(user1);
        return ResponseEntity.ok(user1);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }


    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserid(id);
        return userService.updateUser(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser (id);
    }
}
