package com.bmc.appointmentService.DTO;

import java.util.Date;

public class AppointmentDTO {

    private String appointmentId;

    private String doctorId;

    private String userId;

    private String timeSlot;

    private Date appointmentDate;

    private String emailId;

    private String userName;

    public AppointmentDTO(String appointmentId, String doctorId, String userId, String timeSlot, Date appointmentDate,String emailId,String userName) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.timeSlot = timeSlot;
        this.appointmentDate = appointmentDate;
        this.emailId=emailId;
        this.userName=userName;
    }

    public AppointmentDTO() {
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
