package org.EduRegisterationSystem.Entity;

import java.util.Objects;
import java.util.Vector;

public class Student {
    private String userName, password, name, id, email;
    private Vector<Course> registeredCourses = new Vector<>();
    private Vector<AssignmentSolution> assignmentSolutions = new Vector<>();

    public Vector<AssignmentSolution> getAssignmentSolutions() {
        return assignmentSolutions;
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

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    public boolean equals(String username,String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public AssignmentSolution getAssignmentSolution(Assignment assignment){
        for (AssignmentSolution sol : assignmentSolutions) {
            if(sol.getAssignment().equals(assignment)) return sol;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Student {" +
                "user_name = '" + userName + '\'' +
                ", name = '" + name + '\'' +
                ", id = '" + id + '\'' +
                ", email = '" + email + '\'' +
                ",total registered course = " +  registeredCourses.size() + "}";
    }
}
