package com.oyo.service;

import com.oyo.entity.Review;
import com.oyo.entity.User;

import java.util.List;


public interface ReviewService {

    Review createReview(User user , Review review, long hotelId);

    List<Review> getAllReviewByUserOrHotel(long userId);
}
