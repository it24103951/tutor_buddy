package com.tutor_buddy.service;



import com.tutor_buddy.model.Booking;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class BookingService {
        private List<Booking> bookings;
        private Long nextId = 1L;
        private static final String FILE_PATH = "bookings.txt";

        public BookingService() {
            bookings = new ArrayList<>();
            loadBookingsFromFile();
        }

        private void loadBookingsFromFile() {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 10) {
                        Booking booking = new Booking(
                                Long.parseLong(parts[0].trim()),
                                Long.parseLong(parts[1].trim()),
                                Long.parseLong(parts[2].trim()),
                                parts[3].trim(),
                                parts[4].trim(),
                                parts[5].trim(),
                                parts[6].trim(),
                                Double.parseDouble(parts[7].trim()),
                                parts[8].trim(),
                                parts[9].trim()
                        );
                        bookings.add(booking);
                        nextId = Math.max(nextId, booking.getId() + 1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void saveBookingsToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Booking booking : bookings) {
                    writer.write(booking.getId() + "," + booking.getUserId() + "," + booking.getTutorId() + "," +
                            booking.getSubject() + "," + booking.getDate() + "," + booking.getTime() + "," +
                            booking.getDuration() + "," + booking.getTotalCost() + "," + booking.getPaymentMethod() + "," +
                            booking.getStatus());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Booking createBooking(Booking booking) {
            booking.setId(nextId++);
            bookings.add(booking);
            saveBookingsToFile();
            return booking;
        }

        public List<Booking> getBookingsByUserId(Long userId) {
            return bookings.stream()
                    .filter(b -> b.getUserId().equals(userId))
                    .collect(Collectors.toList());
        }

        public void updateBooking(Booking updatedBooking) {
            for (int i = 0; i < bookings.size(); i++) {
                if (bookings.get(i).getId().equals(updatedBooking.getId())) {
                    bookings.set(i, updatedBooking);
                    break;
                }
            }
            saveBookingsToFile();
        }

        public void cancelBooking(Long id) {
            for (Booking booking : bookings) {
                if (booking.getId().equals(id)) {
                    booking.setStatus("Cancelled");
                    break;
                }
            }
            saveBookingsToFile();
        }
    }

