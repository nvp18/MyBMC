package com.bmc.payment.dao;


import com.bmc.payment.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentDAO extends MongoRepository<Payment, String> {

}
