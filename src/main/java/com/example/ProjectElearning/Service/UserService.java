package com.example.ProjectElearning.Service;


import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Model.UserDTO;
import com.example.ProjectElearning.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        System.out.println("3");
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }


    public User updateUser(Long id,UserDTO userDTO) {
        User existingUser  = getUserById(id);
        if (existingUser  != null) {
            existingUser .setFirstname(userDTO.getFirstname());
            existingUser .setLastname(userDTO.getLastname());
            existingUser .setEmail(userDTO.getEmail());
            existingUser .setPassword(userDTO.getPassword());
            existingUser .setGender(userDTO.getGender());

            return userRepository.save(existingUser );
        } else {
            return null;
        }
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

