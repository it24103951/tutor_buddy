package com.tutor_buddy.service;

import com.tutor_buddy.model.User;
import com.tutor_buddy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerStudent(User user) {
        user.setRole("student");
        user.setJoinedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setBio(updatedUser.getBio());
        user.setSubjectsTaught(updatedUser.getSubjectsTaught());
        user.setProfilePicture(updatedUser.getProfilePicture());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public List<User> getTutors() {
        return userRepository.findByRole("tutor");
    }
}