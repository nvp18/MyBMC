package com.bmc.appointmentService.DAO;

import com.bmc.appointmentService.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentDAO extends JpaRepository<Appointment,String> {
    Optional<List<Appointment>> findByuserId(String userId);
}
