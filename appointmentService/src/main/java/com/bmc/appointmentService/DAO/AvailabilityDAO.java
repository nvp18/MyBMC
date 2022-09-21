package com.bmc.appointmentService.DAO;

import com.bmc.appointmentService.Entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AvailabilityDAO extends JpaRepository<Availability, Long> {
    Optional<List<Availability>> findAllBydoctorId(String doctorId);

    void deleteAllBydoctorId(String doctorId);

    Optional<Availability> findBydoctorIdAndTimeSlotAndAvailabilityDate(String doctorId, String timeSlot, Date appointmentDate);
}
