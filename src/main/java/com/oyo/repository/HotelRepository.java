package com.oyo.repository;

import com.oyo.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    @Query("select h from Hotel h JOIN Location l on h.location=l.id JOIN Country c on h.country=c.id where l.locationName=:locationName or c.countryName=:locationName")
    List<Hotel> findHotelByLocationOrCountry(@Param("locationName") String locationName);

}
