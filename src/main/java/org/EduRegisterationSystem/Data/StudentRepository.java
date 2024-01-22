package org.EduRegisterationSystem.Data;

import org.EduRegisterationSystem.Entity.Student;
import org.EduRegisterationSystem.Helper.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class StudentRepository implements Repository<Student> {
    private List<Student> students;

    private volatile static Repository studentRepository = null;

    private StudentRepository() {
        students = new ArrayList<>();
        generateDummyData();
    }

    public static Repository getInstance() {
        if (studentRepository == null) {
            synchronized (StudentRepository.class) {
                if (studentRepository == null) {
                    studentRepository = new StudentRepository();
                }
            }
        }
        return studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public void setAll(List<Student> entities) {
        students = entities;
    }

    @Override
    public void add(Student entity) {
        students.add(entity);
    }

    @Override
    public Student remove(int index) {
        return students.remove(index);
    }

    @Override
    public Student get(int index) {
        return students.get(index);
    }

    @Override
    public Student findBy(Predicate<Student> filter) throws Exception {
        for (var student : students) {
            if (filter.test(student)) return student;
        }
        throw new Exception("Student not found");
    }

    @Override
    public boolean contains(Predicate<Student> filter) {
        for (var student : students)
            if (filter.test(student)) return true;
        return false;
    }

    @Override
    public void set(int index, Student entity) {
        students.set(index, entity);
    }

    public void generateDummyData() {
        List<String> names = new ArrayList(Arrays.asList("Ali", "Mostafa", "Hani", "Mohamed", "Ashraf", "Samy", "Morad", "Sayed", "Ahmed"));
        for (int i = 1; i <= 20; ++i) {
            Student student = new Student();
            student.setUserName(Helper.generateID(i, 3, "s"));
            student.setPassword(Helper.generateID(i, 3, "s"));
            student.setName(names.get((int) (Math.random() * 9)) + " " + names.get((int) (Math.random() * 9)));
            student.setId(Helper.generateID(i, 3, "s") + (int) (Math.random() * 12345));
            student.setEmail(Helper.generateID(i, 3, "s") + "@gmail.com");
            students.add(student);
        }
    }

}
