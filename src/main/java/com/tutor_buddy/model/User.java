package com.tutor_buddy.model;



import lombok.Data;

import java.time.LocalDateTime;

    @Data
    public class User {
        private Long id;
        private String fullName;
        private String email;
        private String phone;
        private String role; // "student" or "tutor"
        private LocalDateTime joinedDate;
        private String bio;
        private String subjectsTaught; // For tutors
        private String password; // Plain text for simplicity (in-memory)
        private String profilePicture;
    }

