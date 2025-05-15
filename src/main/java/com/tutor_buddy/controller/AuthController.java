package com.tutor_buddy.controller;


import com.tutor_buddy.model.User;
import com.tutor_buddy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {
        @Autowired
        private AuthService authService;

        @PostMapping("/login")
        public User login(@RequestBody Map<String, String> loginData) {
            String email = loginData.get("email");
            String password = loginData.get("password");
            return authService.login(email, password);
        }
    }

