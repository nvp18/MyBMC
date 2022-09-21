package com.bmc.payment.mapper;


import com.bmc.payment.DTO.PaymentDTO;
import com.bmc.payment.entity.Payment;

public class PaymentMapper {

    public static PaymentDTO convertEntityToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAppointmentID(payment.getAppointmentID());
        paymentDTO.setCreatedDate(payment.getCreatedDate());
        return paymentDTO;
    }
}
