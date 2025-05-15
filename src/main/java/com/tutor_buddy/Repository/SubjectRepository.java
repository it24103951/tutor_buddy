package com.tutor_buddy.Repository;


import com.tutor_buddy.model.Subject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @Repository
    public class SubjectRepository {
        private Map<Long, Subject> subjects = new HashMap<>();
        private long sequence = 1;

        public Subject save(Subject subject) {
            if (subject.getId() == null) {
                subject.setId(sequence++);
            }
            subjects.put(subject.getId(), subject);
            return subject;
        }

        public List<Subject> findAll() {
            return new ArrayList<>(subjects.values());
        }

        // Initialize some subjects for testing
        public SubjectRepository() {
            Subject science = new Subject();
            science.setId(sequence++);
            science.setName("Science");
            subjects.put(science.getId(), science);

            Subject mathematics = new Subject();
            mathematics.setId(sequence++);
            mathematics.setName("Mathematics");
            subjects.put(mathematics.getId(), mathematics);

            Subject english = new Subject();
            english.setId(sequence++);
            english.setName("English");
            subjects.put(english.getId(), english);

            Subject oop = new Subject();
            oop.setId(sequence++);
            oop.setName("OOP");
            subjects.put(oop.getId(), oop);

            Subject dsa = new Subject();
            dsa.setId(sequence++);
            dsa.setName("DSA");
            subjects.put(dsa.getId(), dsa);

            Subject dm = new Subject();
            dm.setId(sequence++);
            dm.setName("DM");
            subjects.put(dm.getId(), dm);

            Subject tw = new Subject();
            tw.setId(sequence++);
            tw.setName("TW");
            subjects.put(tw.getId(), tw);
        }
    }

