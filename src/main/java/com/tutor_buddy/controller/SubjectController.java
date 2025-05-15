package com.tutor_buddy.controller;


import com.tutor_buddy.model.Subject;
import com.tutor_buddy.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    @RequestMapping("/api/subjects")
    public class SubjectController {
        @Autowired
        private SubjectService subjectService;

        @GetMapping
        public List<Subject> getAllSubjects() {
            return subjectService.getAllSubjects();
        }
    }

