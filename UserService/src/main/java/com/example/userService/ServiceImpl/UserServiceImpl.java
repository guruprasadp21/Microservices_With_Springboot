package com.example.userService.ServiceImpl;

import com.example.userService.Service.UserServices;

import com.example.userService.entity.HotelEntity;
import com.example.userService.entity.Rating;
import com.example.userService.entity.UserEntity;
import com.example.userService.exception.ResourceNotFoundException;
import com.example.userService.external.service.HotelService;
import com.example.userService.userRepository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserEntity saveUser(@RequestBody UserEntity user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public UserEntity getUserById(String userId) {
        System.out.println("UserServiceImpl : getUserById starts");
        UserEntity userEntity = null;

        userEntity = userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("Resource not found with this id : " + userId));

            //fetch rating
            Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/userRating/" + userEntity.getUserId() , Rating[].class);

            logger.info("{ } " + ratingOfUser);
            List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
            List<Rating> ratingList = ratings.stream().map(rating -> {
                        //api call to hotel service to get the hotel
                        //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                        HotelEntity hotel = hotelService.getHotel(rating.getHotelId());
                        //set the hotel to rating
                        rating.setHotel(hotel);
                        return rating;
                    }).collect(Collectors.toList());
            userEntity.setRatings(ratingList);

        System.out.println("UserServiceImpl : getUserById ends");
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUser() {
        return List.of();
    }

   /* @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> userEntities = new ArrayList<>();

        try {
            // Fetch all users
            userEntities = userRepo.findAll();

            // For each user, fetch ratings and enrich them with hotel details
            userEntities.forEach(userEntity -> {
                try {
                    // Fetch ratings for the user
                    Rating[] ratingOfUser = restTemplate.getForObject(
                            "http://RATINGSERVICE/ratings/userRating/" + userEntity.getUserId(),
                            Rating[].class
                    );

                    logger.info("Ratings fetched for user: " + userEntity.getUserId() + " - " + Arrays.toString(ratingOfUser));

                    // Convert the ratings array to a list and enrich with hotel details
                    List<Rating> ratings = Arrays.stream(ratingOfUser)
                            .map(rating -> {
                                // Fetch the hotel details for the rating
                                HotelEntity hotel = hotelService.getHotel(rating.getHotelId());
                                rating.setHotel(hotel); // Set the hotel in the rating
                                return rating;
                            }).collect(Collectors.toList());

                    // Set the enriched ratings in the user entity
                    userEntity.setRatings(ratings);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("Failed to get the ratings or hotel details for user: " + userEntity.getUserId());
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Failed to fetch users");
            throw new ResourceNotFoundException("Failed to fetch users");
        }

        return userEntities;
    }*/


    /*@Override
    public List<UserEntity> getAllUser() {

        List<UserEntity> userEntity = null;
        try {
            userEntity = userRepo.findAll().
                    orElseThrow(() -> new ResourceNotFoundException("Resource not found with this id : " + userId));
            //fetch rating
        return userRepo.findAll();
    }*/
}
