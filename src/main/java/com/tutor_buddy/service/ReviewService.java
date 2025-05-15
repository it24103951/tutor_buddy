package com.tutor_buddy.service;


import com.tutor_buddy.model.Review;
import com.tutor_buddy.model.Tutor;
import com.tutor_buddy.Repository.ReviewRepository;
import com.tutor_buddy.Repository.TutorRepository;
import com.tutor_buddy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

    @Service
    public class ReviewService {
        @Autowired
        private ReviewRepository reviewRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private TutorRepository tutorRepository;

        public Review submitReview(Review review) {
            if (userRepository.findById(review.getUserId()) == null) {
                throw new RuntimeException("User not found");
            }
            Tutor tutor = tutorRepository.findAll().stream()
                    .filter(t -> t.getFullName().equals(review.getTutorName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Tutor not found"));
            review.setTutorId(tutor.getId());
            review.setDate(LocalDateTime.now());
            return reviewRepository.save(review);
        }
    }

