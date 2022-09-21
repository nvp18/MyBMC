package com.bmc.payment.service.impl;


import com.bmc.payment.DTO.PaymentDTO;
import com.bmc.payment.dao.PaymentDAO;
import com.bmc.payment.entity.Payment;
import com.bmc.payment.mapper.PaymentMapper;
import com.bmc.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceIMPL implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    KafkaTemplate<String, PaymentDTO> kafkaTemplate;

    @Value("${payment.topic}")
    private String topic;

    @Override
    public PaymentDTO makePayment(String appointmentID) {
        LocalDateTime createdDate = LocalDateTime.now();
        Payment payment = new Payment(appointmentID,createdDate);
        Payment savedPayment = paymentDAO.save(payment);
        PaymentDTO result = PaymentMapper.convertEntityToDTO(savedPayment);
        sendPaymentSuccess(result);
        return result;
    }

    private void sendPaymentSuccess(PaymentDTO p){
        kafkaTemplate.send(topic,p);
    }
}
