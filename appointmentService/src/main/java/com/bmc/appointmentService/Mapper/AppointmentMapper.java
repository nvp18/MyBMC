package com.bmc.appointmentService.Mapper;

import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.bmc.appointmentService.Entity.Appointment;

public class AppointmentMapper {

    public static AppointmentDTO convertEntityToDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setAppointmentId(appointment.getAppointmentId());
        appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDTO.setDoctorId(appointment.getDoctorId());
        appointmentDTO.setTimeSlot(appointment.getTimeSlot());
        appointmentDTO.setUserId(appointment.getUserId());
        appointmentDTO.setEmailId(appointment.getUserEmailId());
        return appointmentDTO;
    }

    public static Appointment convertDTOToEntity(AppointmentDTO appointmentDTO){
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setDoctorId(appointmentDTO.getDoctorId());
        appointment.setUserId(appointmentDTO.getUserId());
        appointment.setTimeSlot(appointmentDTO.getTimeSlot());
        appointment.setUserEmailId(appointmentDTO.getEmailId());
        appointment.setUserName(appointmentDTO.getUsername());
        return appointment;
    }
}
