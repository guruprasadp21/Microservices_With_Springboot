package com.example.rating.serviceImpl;

import com.example.rating.entity.Rating;
import com.example.rating.repository.RatingRepository;
import com.example.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository repository;


    @Override
    public Rating createRating(Rating ratingDetails) {
        String randomRatingId = UUID.randomUUID().toString();
        ratingDetails.setRatingId(randomRatingId);
        return repository.save(ratingDetails);
    }

    @Override
    public List<Rating> getAllRating() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
