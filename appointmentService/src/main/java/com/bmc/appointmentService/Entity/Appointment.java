package com.bmc.appointmentService.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "appointment_id")
    private String appointmentId;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "prior_medical_history")
    private String priorMedicalHistory;

    @Column(name = "status")
    private String status="PaymentPending";

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_email_id")
    private String userEmailId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "doctor_name")
    private String doctorName;

    public Appointment() {
    }

    public Appointment(String appointmentId, Date appointmentDate, LocalDateTime createdDate, String doctorId, String priorMedicalHistory
            , String status, String symptoms, String timeSlot, String userId, String userEmailId, String userName, String doctorName) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.createdDate = createdDate;
        this.doctorId = doctorId;
        this.priorMedicalHistory = priorMedicalHistory;
        this.status = status;
        this.symptoms = symptoms;
        this.timeSlot = timeSlot;
        this.userId = userId;
        this.userEmailId = userEmailId;
        this.userName = userName;
        this.doctorName = doctorName;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPriorMedicalHistory() {
        return priorMedicalHistory;
    }

    public void setPriorMedicalHistory(String priorMedicalHistory) {
        this.priorMedicalHistory = priorMedicalHistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
