package com.example.ProjectElearning.Service;



import com.example.ProjectElearning.Exception.AccessDeniedException;
import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Model.UserResponseDTO;
import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Repository.UserRepository;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserTypeRepository userTypeRepository;

    public UserResponseDTO register(User user,String role){
        Long id;
if(role.equals("USER")){
    id= 1L;
}else{
    id=2l;
}

        UserType userType=userTypeRepository.findById(id).get();
//        System.out.println(userType.getType());
        User user1=new User();
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setGender(user.getGender());
        user1.setUserType(userType);
        List<User> users=userType.getUsers();
        users.add(user1);
        userType.setUsers(users);

       userTypeRepository.save(userType);
        UserResponseDTO userResponseDTO=new UserResponseDTO(user1.getFirstname(),user1.getLastname(),user1.getEmail(),user1.getGender(),user1.getUserType().getType());

        return  userResponseDTO;

    }


    public AuthenticationResponse login(User request){
        System.out.println("email "+request.getEmail());
        System.out.println("username "+request.getUsername());
        System.out.println("password "+request.getPassword());

        try{
            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        }catch (Exception e){
            throw new AccessDeniedException("Invalid Credentials");
        }






        User user=userRepo.findByEmail(request.getUsername()).orElseThrow();

        System.out.println("email"+user.getEmail());
        System.out.println("pass"+user.getPassword());

        String token=jwtService.generateToken(user);
        System.out.println(token);

        return new AuthenticationResponse(token,user.getUserType().getType(),user.getUserid());





    }

}
