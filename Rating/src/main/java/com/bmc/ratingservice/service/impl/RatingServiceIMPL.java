package com.bmc.ratingservice.service.impl;

import com.bmc.ratingservice.DTO.RatingDTO;
import com.bmc.ratingservice.Mapper.RatingMapper;
import com.bmc.ratingservice.model.Rating;
import com.bmc.ratingservice.dao.RatingDAO;
import com.bmc.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceIMPL implements RatingService {

    @Autowired
    public RatingDAO ratingDAO;

    @Value("${rating.topic}")
    public String topic;

    @Autowired
    private KafkaTemplate<String, RatingDTO> kafkaTemplate;

    @Override
    public void submitRating(Rating rating) {
        Rating savedRating = ratingDAO.save(rating);
        publishRating(savedRating);
    }

    public void publishRating(Rating rating) {
        RatingDTO ratingDTO = RatingMapper.convertEntityToDTO(rating);
        kafkaTemplate.send(topic,ratingDTO);
    }
}
