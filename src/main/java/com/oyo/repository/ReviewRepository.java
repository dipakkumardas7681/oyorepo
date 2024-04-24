package com.oyo.repository;

import com.oyo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT r FROM Review r JOIN User u on r.user=u.id JOIN Hotel h on r.hotel=h.id WHERE u.id=:userId AND h.id=:hotelId")
    List<Review> findReviewByUserAndHotel(@Param("userId") long userId , @Param("hotelId") long hotelId);

    @Query("select r from Review r JOIN User u on r.user=u.id JOIN Hotel h on r.hotel=h.id where u.id=:userId or h.id=:userId")
    List<Review> findReviewByUserOrHotel(@Param("userId") long userId);
}
