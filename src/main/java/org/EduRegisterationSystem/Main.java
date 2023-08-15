package org.EduRegisterationSystem;

import java.util.GregorianCalendar;

public class Main {
    public static void generateData(){
        StudentManger.generateDummyData();
        DoctorManger.generateDummyData();
        CourseManger.generateDummyData();
    }
    public static void showData(){
        StudentManger.showStudents();
        DoctorManger.showDoctors();
        CourseManger.showCourses();
    }

    public static void main(String[] argv) {
        generateData();
        showData();
        System.out.println("NOTE: You should use any of previous users - PASSWORD IS SAME AS USERNAME");
        UserFlowController.ShowMainMenu();
    }
}