package com.bmc.doctorService.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Document(collection = "doctor")
public class Doctor {

    @Id
    private String _id;

    @Field
    @Pattern(regexp = "^[a-zA-Z]{1,20}$")
    private String firstName;

    @Field
    @Pattern(regexp = "^[a-zA-Z]{1,20}$")
    private String lastName;

    @Field
    private String speciality="GENERAL_PHYSICIAN";

    @Field
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String dob;

    @Field
    @Pattern(regexp = "^[1-9]{1}\\d{9}$")
    private String mobile;

    @Field
    @Pattern(regexp = "^[a-z\\d_]+@[a-z]+.[a-z]{2,4}$")
    private String emailId;

    @Field
    @Pattern(regexp = "^[A-Z]{5}\\d{4}[A-Z]{1}$")
    private String pan;

    @Field
    private String status;

    @Field
    private String approvedBy;

    @Field
    private String approverComments;

    @Field
    private Date registrationDate;

    @Field
    private Date verificationDate;

    @Field
    private Float rating=0.0f;

    @Field
    private Long ratedBy=0L;

    public Doctor(String _id, String firstName, String lastName, String speciality, String dob, String mobile, String emailId, String pan, String status, String approvedBy, String approverComments, Date registrationDate, Date verificationDate) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.dob = dob;
        this.mobile = mobile;
        this.emailId = emailId;
        this.pan = pan;
        this.status = status;
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
        this.registrationDate = registrationDate;
        this.verificationDate = verificationDate;
    }

    public Doctor() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;}

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(Long ratedBy) {
        this.ratedBy = ratedBy;
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

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }
}
