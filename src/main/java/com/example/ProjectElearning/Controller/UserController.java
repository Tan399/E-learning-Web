package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    private UserService userService;


    @PostMapping("/{id}")
    public ResponseEntity<User> createUser(@PathVariable Long id,@RequestBody User user) {
        if(id==1){
            UserType userType=new UserType(id,"User");
        }else{
            UserType userType=new UserType(id,"Admin");
        }

        System.out.println(user);
        return ResponseEntity.ok(userService.createUser(user));
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
        return userService.updateUser (user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser (id);
    }
}
