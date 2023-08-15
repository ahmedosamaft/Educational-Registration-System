package org.EduRegisterationSystem;

import java.util.Objects;
import java.util.Vector;

public class Doctor {
    private String userName, password, name,email,id;
    private Vector<Course> teachingCourses = new Vector<>();

    public Doctor(String user_name, String password, String name,String id,String email) {
        this.userName = user_name;
        this.password = password;
        this.name = name;
        this.id = id;
        this.name = name;
    }
    public Doctor() {}
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Vector<Course> getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(Vector<Course> teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    public void addCourse(Course course){
        this.teachingCourses.add(course);
    }

    @Override
    public String toString() {
        return "Doctor {" +
                "user_name = '" + userName + '\'' +
                ", password = '" + password + '\'' +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", id = '" + id + '\'' +
                ", total teaching courses = " + teachingCourses.size() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean equals(String username,String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }
}
