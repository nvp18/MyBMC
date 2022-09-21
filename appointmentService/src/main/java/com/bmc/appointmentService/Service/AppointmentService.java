package com.bmc.appointmentService.Service;

import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.bmc.appointmentService.DTO.AvailabilityDTO;
import com.bmc.appointmentService.DTO.PrescriptionDTO;

import java.util.List;

public interface AppointmentService {

    void postDoctorAvailability(AvailabilityDTO availabilityDTO,String doctorID);

    AvailabilityDTO getDoctorAvailability(String doctorId);

    String bookAppointment(AppointmentDTO appointmentDTO);

    AppointmentDTO findAppointment(String appointmentId);

    List<AppointmentDTO> findAppointmentsForUser(String userId);

    void sendPrescription(PrescriptionDTO prescriptionDTO);
}
