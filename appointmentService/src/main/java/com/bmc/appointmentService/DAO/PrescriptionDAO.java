package com.bmc.appointmentService.DAO;

import com.bmc.appointmentService.Entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionDAO extends MongoRepository<Prescription, String> {
}
