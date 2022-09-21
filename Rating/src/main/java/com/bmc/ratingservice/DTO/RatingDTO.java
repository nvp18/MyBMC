package com.bmc.ratingservice.DTO;

public class RatingDTO {

    public String doctorId;

    public int rating;

    public RatingDTO(String doctorId, int rating) {
        this.doctorId = doctorId;
        this.rating = rating;
    }

    public RatingDTO() {
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
