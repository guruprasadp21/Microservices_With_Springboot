package com.example.hotel.controllers;

import com.example.hotel.entity.HotelEntity;
import com.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/addHotel")
    public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelEntity hotelDetails)
    {
        HotelEntity hotel = hotelService.createHotel(hotelDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping("/getAllHotel")
    public ResponseEntity<List<HotelEntity>> getAllHotel()
    {
        List<HotelEntity> hotelList = hotelService.getHotelList();
        return ResponseEntity.ok(hotelList);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Optional<HotelEntity>> getHotelById(@PathVariable String hotelId) {
        Optional<HotelEntity> hotelById = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelById);
    }
}
