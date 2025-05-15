package com.tutor_buddy.service;


import com.tutor_buddy.model.User;
import com.tutor_buddy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class AuthService {
        @Autowired
        private UserRepository userRepository;

        public User login(String email, String password) {
            User user = userRepository.findByEmailAndPassword(email, password);
            if (user == null) {
                throw new RuntimeException("Invalid email or password");
            }
            return user;
        }
    }

