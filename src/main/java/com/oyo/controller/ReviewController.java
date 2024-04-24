package com.oyo.controller;

import com.oyo.entity.Review;
import com.oyo.entity.User;
import com.oyo.repository.ReviewRepository;
import com.oyo.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    private ReviewRepository reviewRepository;

    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }


    @PostMapping("/addReview/{hotelId}")
    public ResponseEntity<?> addReview(@AuthenticationPrincipal User user, @RequestBody Review review , @PathVariable long hotelId ){
        if(reviewRepository.findReviewByUserAndHotel(user.getId(), hotelId).isEmpty()){
            reviewService.createReview(user,review,hotelId);
            return new ResponseEntity<>("Review added successfully" , HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review already added" , HttpStatus.ALREADY_REPORTED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> fetchReview(@PathVariable long userId){
        List<Review> reviews = reviewService.getAllReviewByUserOrHotel(userId);
        return new ResponseEntity<>(reviews , HttpStatus.OK);
    }
}
