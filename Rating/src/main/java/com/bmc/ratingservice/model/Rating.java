package com.bmc.ratingservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
public class Rating {

    public String doctorId;

    public int rating;

    public Rating(String doctorId, int rating) {
        this.doctorId = doctorId;
        this.rating = rating;
    }

    public Rating() {
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
