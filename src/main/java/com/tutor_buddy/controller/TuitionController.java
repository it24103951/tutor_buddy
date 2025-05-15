package com.tutor_buddy.controller;

import com.tutor_buddy.model.User;
import com.tutor_buddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView; // Added this import

@Controller
public class TuitionController {

    private final UserService userService;

    public TuitionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @GetMapping("/studentregister")
    public ModelAndView studentRegister() {
        ModelAndView mav = new ModelAndView("studentregister");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/studentregister")
    public ModelAndView studentRegister(@ModelAttribute("user") User user) {
        user.setRole("Student");
        userService.registerUser(user);
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
}