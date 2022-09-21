package com.bmc.payment.DTO;

import java.time.LocalDateTime;

public class PaymentDTO {

    public String id;

    public String appointmentID;

    public LocalDateTime createdDate;

    public PaymentDTO(String id, String appointmentID, LocalDateTime createdDate) {
        this.id = id;
        this.appointmentID = appointmentID;
        this.createdDate = createdDate;
    }

    public PaymentDTO() {
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
