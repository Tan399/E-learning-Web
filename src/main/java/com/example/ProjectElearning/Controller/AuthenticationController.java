package com.example.ProjectElearning.Controller;




import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Model.UserResponseDTO;
import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Repository.UserRepository;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import com.example.ProjectElearning.Service.AuthenticationResponse;
import com.example.ProjectElearning.Service.AuthenticationService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;



    @PostMapping("/register/{role}")
    public ResponseEntity<UserResponseDTO> register(@PathVariable String role, @RequestBody User request){
        Optional<User> user=userRepository.findByEmail(request.getEmail());
        if(user.isPresent()){
            System.out.println("email validtor");
            throw new ValidationException("Email already exists");

        }
        return ResponseEntity.ok( authenticationService.register(request,role));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        System.out.println(request.getEmail());
        return ResponseEntity.ok( authenticationService.login(request));

    }

    @GetMapping("/secure")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Hello");

    }

    @GetMapping("/secure/n")
    public ResponseEntity<String> login2(){
        return ResponseEntity.ok("Hello");

    }
}

