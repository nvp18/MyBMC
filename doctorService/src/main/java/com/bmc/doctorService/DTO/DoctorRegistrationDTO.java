package com.bmc.doctorService.DTO;

import java.util.Date;

public class DoctorRegistrationDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String speciality;
    private String dob;
    private String mobile;
    private String emailId;
    private String pan;
    private String status;
    private Date registrationDate;

    public DoctorRegistrationDTO(String id, String firstName, String lastName, String speciality, String dob, String mobile, String emailId, String pan, String status, Date registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.dob = dob;
        this.mobile = mobile;
        this.emailId = emailId;
        this.pan = pan;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public DoctorRegistrationDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
