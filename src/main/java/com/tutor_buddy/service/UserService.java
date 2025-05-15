package com.tutor_buddy.service;

import com.tutor_buddy.model.BSTNode;
import com.tutor_buddy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private BSTNode root; // BST for tutors
    private List<User> users; // List for all users (students and tutors)
    private Long nextId = 1L;
    private static final String FILE_PATH = "users.txt";

    public UserService() {
        root = null;
        users = new ArrayList<>();
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("Failed to create users.txt file: {}", e.getMessage());
                throw new RuntimeException("Could not initialize user storage", e);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Minimum fields: id, name, email, phone, password, role
                    User user = new User(
                            Long.parseLong(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            parts.length > 6 ? parts[6].trim() : "",
                            parts.length > 7 ? parts[7].trim() : "",
                            parts.length > 8 && !parts[8].trim().isEmpty() ? Integer.parseInt(parts[8].trim()) : null,
                            parts.length > 9 ? parts[9].trim() : "",
                            parts.length > 10 && !parts[10].trim().isEmpty() ? Double.parseDouble(parts[10].trim()) : null,
                            parts.length > 11 ? parts[11].trim() : "",
                            parts.length > 12 ? parts[12].trim() : "",
                            parts.length > 13 ? parts[13].trim() : ""
                    );
                    users.add(user);
                    if ("Tutor".equals(user.getRole())) {
                        insertTutor(user);
                    }
                    nextId = Math.max(nextId, user.getId() + 1);
                }
            }
        } catch (IOException e) {
            logger.error("Failed to load users from file: {}", e.getMessage());
            throw new RuntimeException("Could not load users from file", e);
        }
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getId(),
                        user.getName() != null ? user.getName() : "",
                        user.getEmail() != null ? user.getEmail() : "",
                        user.getPhone() != null ? user.getPhone() : "",
                        user.getPassword() != null ? user.getPassword() : "",
                        user.getRole() != null ? user.getRole() : "",
                        user.getSubjects() != null ? user.getSubjects() : "",
                        user.getEducationLevel() != null ? user.getEducationLevel() : "",
                        user.getExperience() != null ? user.getExperience() : "",
                        user.getBio() != null ? user.getBio() : "",
                        user.getHourlyRate() != null ? user.getHourlyRate() : "",
                        user.getAvailability() != null ? user.getAvailability() : "",
                        "", // Removed getStatus() as it's not in User
                        ""  // Removed getProfilePicture() as it's not in User
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("Failed to save users to file: {}", e.getMessage());
            throw new RuntimeException("Could not save users to file", e);
        }
    }

    private void insertTutor(User tutor) {
        root = insertRec(root, new BSTNode(tutor));
    }

    private BSTNode insertRec(BSTNode root, BSTNode node) {
        if (root == null) {
            return node;
        }
        if (node.getTutor().getId() < root.getTutor().getId()) {
            root.setLeft(insertRec(root.getLeft(), node));
        } else if (node.getTutor().getId() > root.getTutor().getId()) {
            root.setRight(insertRec(root.getRight(), node));
        }
        return root;
    }

    private void inOrderTraversal(BSTNode node, List<User> tutors) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), tutors);
            tutors.add(node.getTutor());
            inOrderTraversal(node.getRight(), tutors);
        }
    }

    public User registerUser(User user) {
        if (user == null || user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
            throw new IllegalArgumentException("User and required fields (name, email, password, role) must not be null");
        }
        user.setId(nextId++);
        users.add(user);
        if ("Tutor".equals(user.getRole())) {
            insertTutor(user);
        }
        saveUsersToFile();
        return user;
    }

    public User login(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password must not be null");
        }
        return users.stream()
                .filter(u -> email.equals(u.getEmail()) && password.equals(u.getPassword()))
                .findFirst()
                .orElse(null);
    }

    public User getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        return users.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<User> getTutorsBySubject(String subject) {
        List<User> tutors = new ArrayList<>();
        inOrderTraversal(root, tutors);
        if (subject != null && !subject.trim().isEmpty()) {
            return tutors.stream()
                    .filter(t -> t.getSubjects() != null && t.getSubjects().contains(subject))
                    .collect(Collectors.toList());
        }
        return tutors;
    }

    public void updateUser(User updatedUser) {
        if (updatedUser == null || updatedUser.getId() == null) {
            throw new IllegalArgumentException("Updated user and ID must not be null");
        }
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found");
        }
        // Rebuild BST for tutors
        root = null;
        for (User user : users) {
            if ("Tutor".equals(user.getRole())) {
                insertTutor(user);
            }
        }
        saveUsersToFile();
    }

    public void deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        boolean removed = users.removeIf(u -> id.equals(u.getId()));
        if (!removed) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
        // Rebuild BST for tutors
        root = null;
        for (User user : users) {
            if ("Tutor".equals(user.getRole())) {
                insertTutor(user);
            }
        }
        saveUsersToFile();
    }

    public List<User> getAllTutors() {
        List<User> tutors = new ArrayList<>();
        inOrderTraversal(root, tutors);
        return tutors;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}