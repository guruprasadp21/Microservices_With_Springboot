package com.example.hotel.service;

import com.example.hotel.entity.HotelEntity;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    //Create
     HotelEntity createHotel(HotelEntity hotel);

     //get hotel list
    List<HotelEntity> getHotelList();

    //get hotel by ID
    Optional<HotelEntity> getHotelById(String Id);

}
