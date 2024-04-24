package com.oyo.seviceImpl;

import com.oyo.entity.Hotel;
import com.oyo.entity.Review;
import com.oyo.entity.User;
import com.oyo.repository.HotelRepository;
import com.oyo.repository.ReviewRepository;
import com.oyo.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    private HotelRepository hotelRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository, HotelRepository hotelRepository) {
        this.reviewRepository = reviewRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Review createReview(@AuthenticationPrincipal User user, Review review, long hotelId) {

        Optional<Hotel> opHotel = hotelRepository.findById(hotelId);
        if(opHotel.isPresent()) {
            Hotel hotel = opHotel.get();
            review.setHotel(hotel);
            review.setUser(user);
            Review savedReview = reviewRepository.save(review);
            return savedReview;
        }
        return null;
    }

    @Override
    public List<Review> getAllReviewByUserOrHotel(long userId) {
        List<Review> reviews = reviewRepository.findReviewByUserOrHotel(userId);
        return reviews;
    }
}
