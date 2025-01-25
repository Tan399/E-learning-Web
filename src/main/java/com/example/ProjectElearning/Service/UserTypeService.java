package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;

    public List getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    public  UserType getUserTypeById(Long id) {
        return  userTypeRepository.findById(id).orElse(null);
    }

    public UserType createUserType(UserType userType) {
        return  userTypeRepository.save(userType);
    }

    public void deleteUserType(Long id) {
        userTypeRepository.deleteById(id);
    }
}