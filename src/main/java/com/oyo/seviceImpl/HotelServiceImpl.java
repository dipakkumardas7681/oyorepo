package com.oyo.seviceImpl;

import com.oyo.entity.Hotel;
import com.oyo.repository.HotelRepository;
import com.oyo.service.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getHotel(String locationName) {
        return hotelRepository.findHotelByLocationOrCountry(locationName);
    }
}
