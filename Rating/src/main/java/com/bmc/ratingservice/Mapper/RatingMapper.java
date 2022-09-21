package com.bmc.ratingservice.Mapper;

import com.bmc.ratingservice.DTO.RatingDTO;
import com.bmc.ratingservice.model.Rating;

public class RatingMapper {

    public static RatingDTO convertEntityToDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setDoctorId(rating.getDoctorId());
        return ratingDTO;
    }
}
