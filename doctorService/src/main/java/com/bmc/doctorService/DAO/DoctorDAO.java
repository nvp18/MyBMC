package com.bmc.doctorService.DAO;

import com.bmc.doctorService.Entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DoctorDAO extends MongoRepository<Doctor, String> {

    List<Doctor> findByStatusAndSpeciality(String status, String speciality);

    List<Doctor> findByStatus(String status);
}
