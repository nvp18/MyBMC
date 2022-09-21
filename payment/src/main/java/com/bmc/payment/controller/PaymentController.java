package com.bmc.payment.controller;


import com.bmc.payment.DTO.PaymentDTO;
import com.bmc.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payments")
    @PreAuthorize("hasAuthority('PAYMENT')")
    public ResponseEntity<PaymentDTO> payment(@RequestParam("appointmentId") String appointmentID){
        PaymentDTO res = paymentService.makePayment(appointmentID);
        return ResponseEntity.ok(res);
    }

}
