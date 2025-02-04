package com.example.ProjectElearning.Repository;

import com.example.ProjectElearning.Model.CoursePayment;
import com.example.ProjectElearning.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {


    @Query("SELECT cp FROM Payment cp " +
            "JOIN cp.course c " +
            "WHERE c.instructorId.userid = :instructorId")
    List<Payment> findPaymentsByInstructor(@Param("instructorId") Long instructorId);
}
