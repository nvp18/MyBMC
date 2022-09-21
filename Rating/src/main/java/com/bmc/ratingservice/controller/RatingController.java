package com.bmc.ratingservice.controller;

import com.bmc.ratingservice.model.Rating;
import com.bmc.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping(value = "/ratings",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('RATING')")
    public ResponseEntity submitRating(@RequestBody Rating rating) {
        ratingService.submitRating(rating);
        return ResponseEntity.ok().build();
    }

}
