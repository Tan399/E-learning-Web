package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {


    @Query(nativeQuery = true,
            value= "select count(id) from Enrollment group by course_id having course_id=:id")
    Long getEnrolledUserCount(@Param("id") long id);



    @Query(nativeQuery = true,
            value= "select * from Enrollment  where user_id=:id")
   List<Enrollment> findByUserId(Long id);




    @Query("SELECT e FROM Enrollment e WHERE e.course.instructorId.userid = :instructorId")
    List<Enrollment> findEnrollmentsByInstructorId(@Param("instructorId") Long instructorId);
}
