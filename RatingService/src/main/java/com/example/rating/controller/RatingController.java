package com.example.rating.controller;

import com.example.rating.entity.Rating;
import com.example.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating ratingDetails) {
       return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(ratingDetails));

    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {

        return ResponseEntity.ok(ratingService.getAllRating());
    }

    @GetMapping("/userRating/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotelRating/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
