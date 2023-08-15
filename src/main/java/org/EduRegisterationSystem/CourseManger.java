package org.EduRegisterationSystem;

import java.util.Vector;

public class CourseManger {
    static public Vector<Course> courses = new Vector<>();

    static public void generateDummyData() {
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
            Doctor doc = DoctorManger.doctors.get((int) (Math.random() * 9));
            cours.setDoctor(doc);
            int l = (int) (Math.random() * 5), r = (int) (Math.random() * 20);
            if (l > r) r = (int) (l + (Math.random() * 5) + 2);
            cours.registeredStudents.addAll(Helper.randomSubset(StudentManger.students, 0.2));
            for (int i = l; i < r; i++) {
                StudentManger.students.get(i).addCourse(cours);
            }
            for (r = 1 + (int) (Math.random() * 5); r > 0; --r) {
                Vector<Student> randomStudents = new Vector<>(Helper.randomSubset(StudentManger.students, 0.05));
                Assignment assign = new Assignment();
                cours.addAssignment(assign);
                assign.setContent("Assign " + Helper.generateID(r, 3, "#"));
                assign.setMaxGrade(10 + (int) (Math.random() * 50));
                for (Student stud : randomStudents) {
                    if(!stud.getRegisteredCourses().contains(cours)) continue;
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

    static public void showCourses() {
        System.out.println(courses);
    }

}
