package com.tutor_buddy.controller;



import com.tutor_buddy.model.Tutor;
import com.tutor_buddy.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

    @Controller
    public class TutorController {

        @Autowired
        private TutorService tutorService;

        @GetMapping("/")
        public String home() {
            return "index";
        }

        @GetMapping("/tutorRegister")
        public String showRegisterForm() {
            return "tutorRegister";
        }

        @PostMapping("/register")
        public String registerTutor(@RequestParam String name, @RequestParam String subject, @RequestParam String location) {
            Tutor tutor = new Tutor(null, name, subject, location);
            tutorService.createTutor(tutor);
            return "redirect:/";
        }

        @GetMapping("/search")
        public String showSearchForm(Model model, @RequestParam(required = false) String subject) {
            model.addAttribute("tutors", tutorService.searchTutors(subject));
            return "search";
        }
    }

