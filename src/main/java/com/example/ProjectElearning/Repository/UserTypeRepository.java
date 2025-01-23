package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
