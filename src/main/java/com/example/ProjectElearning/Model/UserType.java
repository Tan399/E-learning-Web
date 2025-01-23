package com.example.ProjectElearning.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "userType")
    private List<User> users;

    public UserType(Long id, String type) {
        this.id = id;
        this.type = type;
        this.users = users;
    }

    public UserType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
