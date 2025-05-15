package com.tutor_buddy.service;

import com.tutor_buddy.model.Booking;
import com.tutor_buddy.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        booking.setStatus("upcoming");
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByUserAndStatus(Long userId, String status) {
        return bookingRepository.findByUserIdAndStatus(userId, status);
    }
}