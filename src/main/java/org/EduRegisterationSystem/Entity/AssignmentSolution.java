package org.EduRegisterationSystem.Entity;

import org.EduRegisterationSystem.Entity.Assignment;
import org.EduRegisterationSystem.Entity.Student;

public class AssignmentSolution {
    private double grade;
    private boolean isGraded;
    private String answer;
    private String staffComments;
    private Assignment assignment;
    private Student student;

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public void setGraded(boolean graded) {
        this.isGraded = graded;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStaffComments() {
        return staffComments;
    }

    public void setStaffComments(String staffComments) {
        this.staffComments = staffComments;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
