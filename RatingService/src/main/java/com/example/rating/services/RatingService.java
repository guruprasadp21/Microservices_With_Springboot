package com.example.rating.services;

import com.example.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating ratingDetails);

    List<Rating> getAllRating();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);
}
