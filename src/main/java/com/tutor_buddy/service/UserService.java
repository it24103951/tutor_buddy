
package com.tutor_buddy.service;



import com.tutor_buddy.model.BSTNode;
import com.tutor_buddy.model.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
    @Service
    public class UserService {
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
                    e.printStackTrace();
                }
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 8) {
                        User user = new User(
                                Long.parseLong(parts[0].trim()),
                                parts[1].trim(),
                                parts[2].trim(),
                                parts[3].trim(),
                                parts[4].trim(),
                                parts[5].trim(),
                                parts[6].trim(),
                                parts[7].trim(),
                                parts.length > 8 && !parts[8].trim().isEmpty() ? Integer.parseInt(parts[8].trim()) : null,
                                parts.length > 9 ? parts[9].trim() : "",
                                parts.length > 10 && !parts[10].trim().isEmpty() ? Double.parseDouble(parts[10].trim()) : null,
                                parts.length > 11 ? parts[11].trim() : "",
                                parts.length > 12 ? parts[12].trim() : "",
                                parts.length > 13 ? parts[13].trim() : ""
                        );
                        users.add(user);
                        if (user.getRole().equals("Tutor")) {
                            insertTutor(user);
                        }
                        nextId = Math.max(nextId, user.getId() + 1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void saveUsersToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (User user : users) {
                    writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPhone() + "," +
                            user.getPassword() + "," + user.getRole() + "," + user.getSubjects() + "," +
                            (user.getEducationLevel() != null ? user.getEducationLevel() : "") + "," +
                            (user.getExperience() != null ? user.getExperience() : "") + "," +
                            (user.getBio() != null ? user.getBio() : "") + "," +
                            (user.getHourlyRate() != null ? user.getHourlyRate() : "") + "," +
                            (user.getAvailability() != null ? user.getAvailability() : "") + "," +
                            (user.getStatus() != null ? user.getStatus() : "") + "," +
                            (user.getProfilePicture() != null ? user.getProfilePicture() : ""));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
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
            user.setId(nextId++);
            users.add(user);
            if (user.getRole().equals("Tutor")) {
                insertTutor(user);
            }
            saveUsersToFile();
            return user;
        }

        public User login(String email, String password) {
            return users.stream()
                    .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);
        }

        public User getUserById(Long id) {
            return users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        public List<User> getTutorsBySubject(String subject) {
            List<User> tutors = new ArrayList<>();
            inOrderTraversal(root, tutors);
            if (subject != null && !subject.isEmpty()) {
                tutors = mergeSort(tutors);
                return tutors.stream()
                        .filter(t -> t.getSubjects().contains(subject))
                        .collect(Collectors.toList());
            }
            return tutors.isEmpty() ? tutors : mergeSort(tutors);
        }

        private List<User> mergeSort(List<User> tutors) {
            if (tutors.size() <= 1) {
                return tutors;
            }
            int mid = tutors.size() / 2;
            List<User> left = new ArrayList<>(tutors.subList(0, mid));
            List<User> right = new ArrayList<>(tutors.subList(mid, tutors.size()));
            left = mergeSort(left);
            right = mergeSort(right);
            return merge(left, right);
        }

        private List<User> merge(List<User> left, List<User> right) {
            List<User> result = new ArrayList<>();
            int i = 0, j = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i).getSubjects().compareToIgnoreCase(right.get(j).getSubjects()) <= 0) {
                    result.add(left.get(i++));
                } else {
                    result.add(right.get(j++));
                }
            }
            result.addAll(left.subList(i, left.size()));
            result.addAll(right.subList(j, right.size()));
            return result;
        }

        public void updateUser(User updatedUser) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().equals(updatedUser.getId())) {
                    users.set(i, updatedUser);
                    break;
                }
            }
            // Rebuild BST for tutors
            root = null;
            for (User user : users) {
                if (user.getRole().equals("Tutor")) {
                    insertTutor(user);
                }
            }
            saveUsersToFile();
        }

        public void deleteUser(Long id) {
            users.removeIf(u -> u.getId().equals(id));
            // Rebuild BST for tutors
            root = null;
            for (User user : users) {
                if (user.getRole().equals("Tutor")) {
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
    }

