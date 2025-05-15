package com.tutor_buddy.service;


import com.tutor_buddy.model.Tutor;
import com.tutor_buddy.Repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class TutorService {
        @Autowired
        private TutorRepository tutorRepository;

        public Tutor registerTutor(Tutor tutor) {
            tutor.setStatus("pending");
            return tutorRepository.save(tutor);
        }

        public List<Tutor> getAllTutors() {
            return tutorRepository.findAll();
        }

        public Tutor getTutorById(Long id) {
            Tutor tutor = tutorRepository.findById(id);
            if (tutor == null) {
                throw new RuntimeException("Tutor not found");
            }
            return tutor;
        }

        public Tutor updateTutor(Long id, Tutor updatedTutor) {
            Tutor tutor = tutorRepository.findById(id);
            if (tutor == null) {
                throw new RuntimeException("Tutor not found");
            }
            tutor.setFullName(updatedTutor.getFullName());
            tutor.setEmail(updatedTutor.getEmail());
            tutor.setPhone(updatedTutor.getPhone());
            tutor.setHourlyRate(updatedTutor.getHourlyRate());
            tutor.setBio(updatedTutor.getBio());
            tutor.setEducation(updatedTutor.getEducation());
            tutor.setExperienceYears(updatedTutor.getExperienceYears());
            tutor.setSubjects(updatedTutor.getSubjects());
            tutor.setAvailability(updatedTutor.getAvailability());
            tutor.setStatus(updatedTutor.getStatus());
            tutor.setProfilePicture(updatedTutor.getProfilePicture());
            tutor.setSubjectExpertise(updatedTutor.getSubjectExpertise());
            return tutorRepository.save(tutor);
        }

        public void deleteTutor(Long id) {
            tutorRepository.delete(id);
        }

        public List<Tutor> getTutorsBySubject(String subject) {
            return tutorRepository.findBySubject(subject);
        }

        public List<Tutor> getTutorsByStatus(String status) {
            return tutorRepository.findByStatus(status);
        }

        // Sort tutors by subject expertise using Merge Sort
        public List<Tutor> sortTutorsByExpertise(List<Tutor> tutors) {
            if (tutors == null || tutors.size() <= 1) {
                return tutors;
            }
            Tutor[] tutorArray = tutors.toArray(new Tutor[0]);
            mergeSort(tutorArray, 0, tutorArray.length - 1);
            return List.of(tutorArray);
        }

        private void mergeSort(Tutor[] array, int left, int right) {
            if (left < right) {
                int mid = left + (right - left) / 2;
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }

        private void merge(Tutor[] array, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;

            Tutor[] leftArray = new Tutor[n1];
            Tutor[] rightArray = new Tutor[n2];

            // Copy data to temp arrays
            for (int i = 0; i < n1; i++) {
                leftArray[i] = array[left + i];
            }
            for (int j = 0; j < n2; j++) {
                rightArray[j] = array[mid + 1 + j];
            }

            // Merge the temp arrays back into array[left..right]
            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (leftArray[i].getSubjectExpertise() >= rightArray[j].getSubjectExpertise()) { // Sort in descending order
                    array[k] = leftArray[i];
                    i++;
                } else {
                    array[k] = rightArray[j];
                    j++;
                }
                k++;
            }

            // Copy remaining elements of leftArray[] if any
            while (i < n1) {
                array[k] = leftArray[i];
                i++;
                k++;
            }

            // Copy remaining elements of rightArray[] if any
            while (j < n2) {
                array[k] = rightArray[j];
                j++;
                k++;
            }
        }
    }

