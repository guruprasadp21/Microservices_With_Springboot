package com.example.userService.controller;

import com.example.userService.Service.UserServices;
import com.example.userService.entity.UserEntity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        UserEntity user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
     int retryCount=1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallback")
    //@Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String userId) {
        System.out.println("Get single user handler::::UserController : getUserById starts");
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        UserEntity userById = userService.getUserById(userId);
        System.out.println("UserController : getUserById ends");
        return ResponseEntity.ok(userById);

    }

    //Creating Fallback method for circuit breaker
    public ResponseEntity<UserEntity> ratingHotelFallback(String userId, Throwable throwable) {
            logger.info("Fallback is executed because service is down", throwable.getMessage());
            UserEntity user = UserEntity.builder()
                    .email("dummy@gmail.com")
                    .userName("dummy")
                    .about("This user is created dummy because some services are down")
                    .userId("111111")
                    .build();
            return new ResponseEntity<>(user, HttpStatus.SERVICE_UNAVAILABLE);

    }


}
