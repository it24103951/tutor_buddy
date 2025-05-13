package com.tutor_buddy.model;

public class BSTNode {


        private Tutor tutor;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(Tutor tutor) {
            this.tutor = tutor;
            this.left = null;
            this.right = null;
        }

        public Tutor getTutor() { return tutor; }
        public void setTutor(Tutor tutor) { this.tutor = tutor; }
        public BSTNode getLeft() { return left; }
        public void setLeft(BSTNode left) { this.left = left; }
        public BSTNode getRight() { return right; }
        public void setRight(BSTNode right) { this.right = right; }
    }

