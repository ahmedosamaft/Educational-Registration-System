package org.EduRegisterationSystem.Entity;

import java.util.Objects;
import java.util.Vector;

public class Student extends Person {
    private Vector<Course> registeredCourses = new Vector<>();
    private Vector<AssignmentSolution> assignmentSolutions = new Vector<>();

    public Vector<AssignmentSolution> getAssignmentSolutions() {
        return assignmentSolutions;
    }

    public Student() {
    }

    public Student(String user_name, String password, String name, String id, String email) {
        super(user_name, password, name, id, email);
    }

    public void addSolutions(AssignmentSolution solution) {
        this.assignmentSolutions.add(solution);
        solution.setStudent(this);
    }

    public Vector<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(Vector<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public void addCourse(Course course) {
        this.registeredCourses.add(course);
    }

    public AssignmentSolution getAssignmentSolution(Assignment assignment) {
        for (AssignmentSolution sol : assignmentSolutions) {
            if (sol.getAssignment().equals(assignment)) return sol;
        }
        return null;
    }

}
