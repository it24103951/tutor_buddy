package com.tutor_buddy.controller;


import com.tutor_buddy.model.Booking;
import com.tutor_buddy.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/bookings")
    public class BookingController {
        @Autowired
        private BookingService bookingService;

        @PostMapping
        public Booking createBooking(@RequestBody Booking booking) {
            return bookingService.createBooking(booking);
        }

        @GetMapping
        public List<Booking> getBookings(@RequestParam Long userId,
                                         @RequestParam(required = false) String status) {
            if (status != null) {
                return bookingService.getBookingsByUserAndStatus(userId, status);
            }
            return bookingService.getBookingsByUser(userId);
        }
    }

