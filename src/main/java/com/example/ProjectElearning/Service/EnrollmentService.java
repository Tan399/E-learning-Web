package com.example.ProjectElearning.Service;

import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.Enrollment;
import com.example.ProjectElearning.Model.temp;
import com.example.ProjectElearning.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));}

    public Enrollment createEnrollment(Enrollment enrollment) {
        return  enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }



    public List<Enrollment> getEnrollmentByUserId(Long id) {
        return enrollmentRepository.findByUserId(id);}


    public void sendmail( temp object){

        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(object.getTo());
        mail.setSubject(object.getSubject());
        mail.setText(object.getBody());
        javaMailSender.send(mail);

    }

}