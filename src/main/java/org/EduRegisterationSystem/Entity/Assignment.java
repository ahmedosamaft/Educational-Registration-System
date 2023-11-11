package org.EduRegisterationSystem.Entity;

import java.util.Objects;
import java.util.Vector;

public class Assignment {
    private String content;
    private int maxGrade;
    private Course course;
    private Vector<AssignmentSolution> assignmentSolutions = new Vector<>();


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Vector<AssignmentSolution> getAssignmentSolutions() {
        return assignmentSolutions;
    }

    public void addSolution (AssignmentSolution solution) {
        this.assignmentSolutions.add(solution);
        solution.setAssignment(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return content.equals(that.content) && course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, course);
    }
}
