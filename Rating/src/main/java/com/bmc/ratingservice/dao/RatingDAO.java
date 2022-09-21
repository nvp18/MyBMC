package com.bmc.ratingservice.dao;

import com.bmc.ratingservice.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingDAO extends MongoRepository<Rating, Integer> {

}
