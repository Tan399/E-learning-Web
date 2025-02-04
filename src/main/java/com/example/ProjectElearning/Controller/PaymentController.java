package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Exception.ResourceNotFoundException;
import com.example.ProjectElearning.Model.*;
import com.example.ProjectElearning.Repository.PaymentRepository;
import com.example.ProjectElearning.Service.CourseService;
import com.example.ProjectElearning.Service.PaymentService;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> dtos=paymentService.getAllPayments().stream().map((payment)->{
            PaymentResponseDTO paymentDTO=new PaymentResponseDTO();

            paymentDTO.setPaymentDate(payment.getPaymentDate());
            paymentDTO.setAmount(payment.getAmount());
            paymentDTO.setUserId(payment.getUser().getUserid());
            paymentDTO.setUserId(payment.getId());
            paymentDTO.setCourseId(payment.getCourse().getCourseid());
            paymentDTO.setId(payment.getId());
            return paymentDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        Payment payment=paymentService.getPaymentById(id);
        PaymentResponseDTO paymentDTO=new PaymentResponseDTO();

        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setUserId(payment.getUser().getUserid());
        paymentDTO.setUserId(payment.getId());
        paymentDTO.setCourseId(payment.getCourse().getCourseid());
        paymentDTO.setId(payment.getId());
        return ResponseEntity.ok(paymentDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment=new Payment();
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setAmount(paymentDTO.getAmount());
        User user=userService.getUserById(paymentDTO.getUserId());
        Course course=courseService.getCourseById(paymentDTO.getCourseId());
        payment.setUser(user);
        payment.setCourse(course);
        user.getPayments().add(payment);
        paymentService.createPayment(payment);



        return new ResponseEntity<PaymentDTO>(paymentDTO, HttpStatus.CREATED);
    }


    @GetMapping("/instructor/{instructorId}/course-payments")
    public ResponseEntity<List<CoursePayment>> getPaymentsForInstructorCourses(@PathVariable Long instructorId) {
        List<CoursePayment> cp=paymentRepository.findPaymentsByInstructor(instructorId).stream().map((payment)->{
            return new CoursePayment(payment.getCourse().getCoursename(),payment.getUser().getFirstname()+" "+payment.getUser().getLastname(),payment.getAmount(),payment.getPaymentDate());
        }).collect(Collectors.toList());

        return ResponseEntity.ok(cp);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
