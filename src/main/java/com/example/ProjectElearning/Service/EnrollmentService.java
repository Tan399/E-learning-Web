package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Model.Enrollment;
import com.example.ProjectElearning.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return  enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}