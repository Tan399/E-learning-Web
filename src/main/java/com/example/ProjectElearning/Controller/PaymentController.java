package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.Payment;
import com.example.ProjectElearning.Model.PaymentDTO;
import com.example.ProjectElearning.Model.User;
import com.example.ProjectElearning.Service.PaymentService;
import com.example.ProjectElearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {

        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        Payment payment=paymentService.getPaymentById(id);
        PaymentDTO paymentDTO=new PaymentDTO();
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setUserId(payment.getUser().getUserid());
        return ResponseEntity.ok(paymentDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment=new Payment();
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setAmount(paymentDTO.getAmount());
        User user=userService.getUserById(paymentDTO.getUserId());
        payment.setUser(user);
        user.getPayments().add(payment);
        userService.updateUser(user);



        return new ResponseEntity<PaymentDTO>(paymentDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
