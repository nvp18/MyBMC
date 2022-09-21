package com.bmc.payment.service;

import com.bmc.payment.DTO.PaymentDTO;

public interface PaymentService {

    PaymentDTO makePayment(String appointmentID);
}
