package com.bmc.payment.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payment")
public class Payment {

    @Id
    public String id;

    public String appointmentID;

    public LocalDateTime createdDate;

    public Payment(String id, String appointmentID, LocalDateTime createdDate) {
        this.id = id;
        this.appointmentID = appointmentID;
        this.createdDate = createdDate;
    }

    public Payment(String appointmentID, LocalDateTime createdDate) {
        this.appointmentID = appointmentID;
        this.createdDate = createdDate;
    }

    public Payment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


}
