package com.oyo.service;

import com.oyo.entity.Hotel;

import java.util.List;

public interface HotelService {


    List<Hotel> getHotel(String locationName);

}
