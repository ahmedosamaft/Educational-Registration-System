package org.EduRegisterationSystem.Data;

import org.EduRegisterationSystem.Controller.DoctorController;
import org.EduRegisterationSystem.Entity.*;
import org.EduRegisterationSystem.Helper.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CourseRepository implements Repository<Course> {
    private List<Course> courses;
    private volatile static Repository courseRepository = null;
    private Repository<Doctor> doctorRepository = null;
    private Repository<Student> studentRepository = null;

    private CourseRepository() {
        courses = new ArrayList<>();
        studentRepository = StudentRepository.getInstance();
        doctorRepository = DoctorRepository.getInstance();
        generateDummyData();
    }

    public static Repository getInstance() {
        if (courseRepository == null) {
            synchronized (CourseRepository.class) {
                if (courseRepository == null) {
                    courseRepository = new CourseRepository();
                }
            }
        }
        return courseRepository;
    }

    public List<Course> getAll() {
        return courses;
    }

    public void setAll(List<Course> courses) {
        this.courses = courses;
    }

    public void add(Course entity) {
        courses.add(entity);
    }

    public Course get(int index) {
        return courses.get(index);
    }

    @Override
    public Course findBy(Predicate<Course> filter) throws Exception {
        for (var course: courses) {
            if(filter.test(course)) return course;
        }
        throw new Exception("Course not found");
    }

    @Override
    public boolean contains(Predicate<Course> filter) {
        for (var course: courses) {
            if(filter.test(course)) return true;
        }
        return false;
    }

    public void set(int index, Course entity) {
        courses.set(index, entity);
    }

    public Course remove(int index) {
        return courses.remove(index);
    }

    private void generateDummyData() {
        Course course01 = new Course("Programming 1", "CS111");
        courses.add(course01);
        Course course02 = new Course("Programming 2", "CS112");
        courses.add(course02);
        Course course03 = new Course("Math 1", "MA113");
        courses.add(course03);
        Course course04 = new Course("Math 2", "MA114");
        courses.add(course04);
        Course course05 = new Course("Stat 1", "ST314");
        courses.add(course05);
        for (Course cours : courses) {
            Doctor doctor = doctorRepository.get((int) (Math.random() * 9));
            cours.setDoctor(doctor);
            int l = (int) (Math.random() * 5), r = (int) (Math.random() * 20);
            if (l > r) r = (int) (l + (Math.random() * 5) + 2);
            cours.getRegisteredStudents().addAll(Helper.randomSubset(studentRepository.getAll(), 0.2));
            for (int i = l; i < r; i++) {
                studentRepository.get(i).addCourse(cours);
            }
            for (r = 1 + (int) (Math.random() * 5); r > 0; --r) {
                List<Student> randomStudents = new ArrayList<>(Helper.randomSubset(studentRepository.getAll(), 0.05));
                Assignment assign = new Assignment();
                cours.addAssignment(assign);
                assign.setContent("Assign " + Helper.generateID(r, 3, "#"));
                assign.setMaxGrade(10 + (int) (Math.random() * 50));
                for (Student stud : randomStudents) {
                    if (!stud.getRegisteredCourses().contains(cours)) continue;
                    AssignmentSolution sol = new AssignmentSolution();
                    assign.addSolution(sol);
                    stud.addSolutions(sol);
                    sol.setAnswer(Helper.generateID((int) (Math.random() * 100000), 8, "ANS"));
                    if (Math.random() > 0.3) {
                        sol.setGraded(true);
                        sol.setGrade((int) ((Math.random() + 0.1) * assign.getMaxGrade()));
                    }
                }
            }
        }
    }
}
