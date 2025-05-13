package com.tutor_buddy.model;

public class Tutor {

        private Long id;
        private String name;
        private String subject;
        private String location;

        public Tutor() {}

        public Tutor(Long id, String name, String subject, String location) {
            this.id = id;
            this.name = name;
            this.subject = subject;
            this.location = location;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }

        @Override
        public String toString() {
            return "Tutor{id=" + id + ", name='" + name + "', subject='" + subject + "', location='" + location + "'}";
        }
    }

