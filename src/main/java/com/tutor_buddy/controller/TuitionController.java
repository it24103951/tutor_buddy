package com.tutor_buddy.controller;

import com.tutor_buddy.model.User;
import com.tutor_buddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class TuitionController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/studentregister")
    public String showStudentRegisterForm() {
        return "studentregister";
    }

    @PostMapping("/studentregister")
    public String registerStudent(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
                                  @RequestParam String subjects, @RequestParam String educationLevel,
                                  @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "studentregister";
        }
        User user = new User(null, name, email, phone, password, "Student", subjects, educationLevel, null, "", null, "", "", "");
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
}