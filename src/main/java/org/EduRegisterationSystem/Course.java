package org.EduRegisterationSystem;

import java.util.Objects;
import java.util.Vector;

public class Course {
    private String name, code;
    Doctor doctor;
    Vector<Student> registeredStudents = new Vector<>();
    Vector<Assignment> assignments = new Vector<>();

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Vector<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignment.setCourse(this);
        this.assignments.add(assignment);
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.addCourse(this);
    }

    public void addStudent(Student student) {
        this.registeredStudents.add(student);
        student.addCourse(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Vector<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Vector<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public Course() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Course = '" + name + "'" +
                " code = '" + code + "'" +
                ", Doctor = '" + doctor.getName() + "'";
    }

}
