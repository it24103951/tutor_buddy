package com.tutor_buddy.service;

import com.tutor_buddy.model.BSTNode;
import com.tutor_buddy.model.Tutor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutorService {

    private BSTNode root;
    private Long nextId = 1L;
    private static final String FILE_PATH = "tutors.txt";

    // Load tutors from file into BST
    public TutorService() {
        root = null;
        loadTutorsFromFile();
    }

    private void loadTutorsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Tutor tutor = new Tutor(Long.parseLong(parts[0].trim()), parts[1].trim(), parts[2].trim(), parts[3].trim());
                    insertTutor(tutor);
                    nextId = Math.max(nextId, tutor.getId() + 1);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, proceed with empty BST
        }
    }

    // Save BST to file
    private void saveTutorsToFile() {
        List<Tutor> tutors = new ArrayList<>();
        inOrderTraversal(root, tutors);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Tutor tutor : tutors) {
                writer.write(tutor.getId() + "," + tutor.getName() + "," + tutor.getSubject() + "," + tutor.getLocation());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // In-order traversal to get list from BST
    private void inOrderTraversal(BSTNode node, List<Tutor> tutors) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), tutors);
            tutors.add(node.getTutor());
            inOrderTraversal(node.getRight(), tutors);
        }
    }

    // Insert tutor into BST
    private void insertTutor(Tutor tutor) {
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

    // Create a new tutor
    public Tutor createTutor(Tutor tutor) {
        tutor.setId(nextId++);
        insertTutor(tutor);
        saveTutorsToFile();
        return tutor;
    }

    // Search tutors by subject using BST and Merge Sort
    public List<Tutor> searchTutors(String subject) {
        List<Tutor> tutors = new ArrayList<>();
        inOrderTraversal(root, tutors);
        if (subject != null && !subject.isEmpty()) {
            tutors = mergeSort(tutors);
            return tutors.stream()
                    .filter(t -> t.getSubject().toLowerCase().contains(subject.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return mergeSort(tutors);
    }

    // Merge Sort implementation
    private List<Tutor> mergeSort(List<Tutor> tutors) {
        if (tutors.size() <= 1) {
            return tutors;
        }

        int mid = tutors.size() / 2;
        List<Tutor> left = new ArrayList<>(tutors.subList(0, mid));
        List<Tutor> right = new ArrayList<>(tutors.subList(mid, tutors.size()));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private List<Tutor> merge(List<Tutor> left, List<Tutor> right) {
        List<Tutor> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getSubject().compareToIgnoreCase(right.get(j).getSubject()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));
        return result;
    }
}

