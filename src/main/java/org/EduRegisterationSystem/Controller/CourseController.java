package org.EduRegisterationSystem.Controller;

import org.EduRegisterationSystem.Data.*;
import org.EduRegisterationSystem.Entity.Course;
import org.EduRegisterationSystem.Entity.Student;

public class CourseController {
    private Repository<Course> courseRepository;

    public CourseController() {
        courseRepository = CourseRepository.getInstance();
    }


    public void showCourses() {
        System.out.println(courseRepository.getAll());
    }

}
