package com.tutor_buddy.controller;


import com.tutor_buddy.model.Review;
import com.tutor_buddy.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/reviews")
    public class ReviewController {
        @Autowired
        private ReviewService reviewService;

        @PostMapping
        public Review submitReview(@RequestBody Review review) {
            return reviewService.submitReview(review);
        }
    }

