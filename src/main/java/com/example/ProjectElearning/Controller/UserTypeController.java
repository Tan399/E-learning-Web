package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Model.UserTypeDTO;
import com.example.ProjectElearning.Service.UserTypeService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-types")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;


    @GetMapping
    public ResponseEntity<List<UserType>> getAllUserTypes() {

        List<UserType> userTypes= userTypeService.getAllUserTypes();

        if (userTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity< UserType> getUserTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(userTypeService.getUserTypeById(id));
    }

    @PostMapping
    public ResponseEntity<UserType> createUserType(@RequestBody UserTypeDTO userType) {
        UserType userType1=new UserType();
        userType1.setType(userType.getType());
        return new  ResponseEntity<UserType>(userTypeService.createUserType(userType1),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteUserType(@PathVariable Long id) {
        userTypeService.deleteUserType(id);
    }
}