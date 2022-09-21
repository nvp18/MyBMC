package com.userService.user.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Pattern;

@Document(collection = "User")
public class User {

    @Id
    public String id;

    @Pattern(regexp = "^[a-zA-Z]+$",message = "First Name")
    public String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$",message = "Last Name")
    public String lastName;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message = "Date Of Birth")
    public String dob;

    @Pattern(regexp = "^[1-9]{1}\\d{9}$",message = "Mobile")
    public String mobile;

    @Pattern(regexp = "^[a-z\\d_]+@[a-z]+.[a-z]{2,4}$",message = "EmailID")
    public String emailId;

    public String createdDate;

    public User(String id, String firstName, String lastName, String dob, String mobile, String emailId, String createdDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.mobile = mobile;
        this.emailId = emailId;
        this.createdDate = createdDate;
    }

    public User() {
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
