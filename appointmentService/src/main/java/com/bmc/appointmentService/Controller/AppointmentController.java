package com.bmc.appointmentService.Controller;

import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.bmc.appointmentService.DTO.AvailabilityDTO;
import com.bmc.appointmentService.DTO.PrescriptionDTO;
import com.bmc.appointmentService.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/doctor/{doctorId}/availability")
    @PreAuthorize("hasAuthority('DOCTOR_AVAILABILITY_UPDATE')")
    public ResponseEntity postDoctorAvailabilty(@RequestBody AvailabilityDTO availabilityDTO,@PathVariable(value = "doctorId") String doctorId){
        appointmentService.postDoctorAvailability(availabilityDTO,doctorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/doctor/{doctorId}/availability")
    @PreAuthorize("hasAuthority('DOCTOR_AVAILABILITY')")
    public ResponseEntity<AvailabilityDTO> getDoctorAvailability(@PathVariable(value = "doctorId") String doctorId){
        AvailabilityDTO availabilityDTO = appointmentService.getDoctorAvailability(doctorId);
        return ResponseEntity.ok(availabilityDTO);
    }

    @PostMapping(value = "/appointments")
    @PreAuthorize("hasAuthority('BOOK_APPOINTMENT')")
    public  ResponseEntity<String> bookAppointment(@RequestBody AppointmentDTO appointmentDTO){
        String appointmentId = appointmentService.bookAppointment(appointmentDTO);
        return ResponseEntity.ok(appointmentId);
    }

    @GetMapping(value = "/appointments/{appointmentId}")
    @PreAuthorize("hasAuthority('APPOINTMENT_DETAILS')")
    public  ResponseEntity<AppointmentDTO> findAppointmentsByAppointmentId(@PathVariable(value = "appointmentId") String appointmentId){
        AppointmentDTO appointmentDTO = appointmentService.findAppointment(appointmentId);
        return ResponseEntity.ok(appointmentDTO);
    }

    @GetMapping(value = "/users/{userId}/appointments")
    @PreAuthorize("hasAuthority('USER_APPOINTMENTS')")
    public ResponseEntity<List<AppointmentDTO>> findUserAppointments(@PathVariable(value = "userId") String userId){
        List<AppointmentDTO> userAppointments = appointmentService.findAppointmentsForUser(userId);
        return ResponseEntity.ok(userAppointments);
    }

    @PostMapping(value = "/prescriptions")
    @PreAuthorize("hasAuthority('SEND_PRESCRIPTION')")
    public ResponseEntity sendPrescription(@RequestBody PrescriptionDTO prescriptionDTO){
        appointmentService.sendPrescription(prescriptionDTO);
        return ResponseEntity.ok().build();
    }
}
