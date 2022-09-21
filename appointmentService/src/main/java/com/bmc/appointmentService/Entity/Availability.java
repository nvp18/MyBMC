package com.bmc.appointmentService.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "availability_date")
    private Date availabilityDate;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "is_booked", nullable = false)
    private boolean isBooked=false;

    @Column(name = "time_slot")
    private String timeSlot;

    public Availability(Long id, Date availabilityDate, String doctorId, boolean isBooked, String timeSlot) {
        this.id = id;
        this.availabilityDate = availabilityDate;
        this.doctorId = doctorId;
        this.isBooked = isBooked;
        this.timeSlot = timeSlot;
    }

    public Availability() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(Date availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
