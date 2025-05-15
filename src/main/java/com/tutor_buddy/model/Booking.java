package com.tutor_buddy.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private Long id;
    private Long userI;
    private Long tutorId;
    private String subject;
    private LocalDateTime sessionDate;
    private String startTime;
    private String endTime;
    private double duration; // in hours
    private String paymentMethod;
    private double total;
    private String status; // "upcoming", "completed", "canceled"
    private String specialRequests;

    @Data
    static class Availability {
        private String day;
        private String startTime;
        private String endTime;
    }

}
