package com.example.userService.external.service;

import com.example.userService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @PostMapping("/ratings/createRating")
    Rating createRating(Rating values);


    Rating updateRating();
}
