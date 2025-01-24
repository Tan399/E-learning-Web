package com.example.ProjectElearning.Service;


import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.User;
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
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public User updateUser(User user) {
        User existingUser  = getUserById(user.getUserid());
        if (existingUser  != null) {
            existingUser .setFirstname(user.getFirstname());
            existingUser .setLastname(user.getLastname());
            existingUser .setEmail(user.getEmail());
            existingUser .setPassword(user.getPassword());
            existingUser .setGender(user.getGender());
            existingUser .setUserType(user.getUserType());
            return userRepository.save(existingUser );
        } else {
            return null;
        }
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

