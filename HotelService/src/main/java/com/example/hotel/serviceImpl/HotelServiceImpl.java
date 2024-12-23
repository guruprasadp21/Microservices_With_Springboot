package com.example.hotel.serviceImpl;

import com.example.hotel.entity.HotelEntity;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.repo.HotelRepository;
import com.example.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public HotelEntity createHotel(HotelEntity hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<HotelEntity> getHotelList() {
        return hotelRepo.findAll();
    }

    @Override
    public Optional<HotelEntity> getHotelById(String hotelId) {
        return Optional.ofNullable(hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given ID is not found" + hotelId)));
    }
}
