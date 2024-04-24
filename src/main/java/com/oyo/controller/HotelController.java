package com.oyo.controller;

import com.oyo.entity.Hotel;
import com.oyo.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @GetMapping("{locationName}")
    public ResponseEntity<List<Hotel>> fetchHotel(@PathVariable String locationName){
        List<Hotel> property = hotelService.getHotel(locationName);
        return new ResponseEntity<>(property , HttpStatus.OK);
    }
}
