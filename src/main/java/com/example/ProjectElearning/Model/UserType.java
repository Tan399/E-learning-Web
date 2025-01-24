package com.example.ProjectElearning.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference("user-userType")
    @OneToMany(mappedBy = "userType",cascade = CascadeType.ALL)
    private List<User> users;

    public UserType(Long id, String type) {
        this.id = id;
        this.type = type;

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
