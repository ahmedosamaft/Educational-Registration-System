package org.EduRegisterationSystem.Entity;

import java.util.Objects;
import java.util.Vector;

public class Doctor extends Person {
    private Vector<Course> teachingCourses = new Vector<>();
    public Doctor(){}
    public Doctor(String user_name, String password, String name,String id,String email) {
            super(user_name,password,name,id,email);
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

}
