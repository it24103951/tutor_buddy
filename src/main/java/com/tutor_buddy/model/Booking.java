package com.tutor_buddy.model;

public class Booking {


        private Long id;
        private Long userId; // Student ID
        private Long tutorId;
        private String subject;
        private String date;
        private String time;
        private String duration;
        private Double totalCost;
        private String paymentMethod;
        private String status; // "Upcoming", "Completed", "Cancelled"

        // Constructors
        public Booking() {}

        public Booking(Long id, Long userId, Long tutorId, String subject, String date, String time, String duration,
                       Double totalCost, String paymentMethod, String status) {
            this.id = id;
            this.userId = userId;
            this.tutorId = tutorId;
            this.subject = subject;
            this.date = date;
            this.time = time;
            this.duration = duration;
            this.totalCost = totalCost;
            this.paymentMethod = paymentMethod;
            this.status = status;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getTutorId() { return tutorId; }
        public void setTutorId(Long tutorId) { this.tutorId = tutorId; }
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        public String getDuration() { return duration; }
        public void setDuration(String duration) { this.duration = duration; }
        public Double getTotalCost() { return totalCost; }
        public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

