package org.EduRegisterationSystem.Controller;

import org.EduRegisterationSystem.Helper.Helper;

public class UserFlowController {
    private StudentController studentController;
    private DoctorController doctorController;
    private CourseController courseController;

    public void showData() {
        studentController.showStudents();
        doctorController.showDoctors();
        courseController.showCourses();
    }

    public UserFlowController(StudentController studentController, DoctorController doctorController, CourseController courseController) {
        this.studentController = studentController;
        this.doctorController = doctorController;
        this.courseController = courseController;
        showData();
        System.out.println("NOTE: You should use any of previous users - PASSWORD IS SAME AS USERNAME");
    }

    public void ShowMainMenu() {
        while (true) {
            System.out.println("\nPlease make a choice:");
            System.out.println("\t1 - Login");
            System.out.println("\t2 - Shutdown system");
            System.out.print("\tEnter Choice: ");
            try {
                int option = Integer.parseInt(Helper.in.next());
                if (option == 1)
                    DoLogin();
                else if (option == 2)
                    break;
                else
                    throw (new NumberFormatException("ERROR: Expected range is 1 to 2"));
            } catch (NumberFormatException E) {
                System.err.println(E.getMessage());
            } catch (Exception e) {
                System.err.println("ERROR: Expected to be numeric");
            }
        }
    }

    public void DoLogin() {
        String username;
        String password;
        System.out.print("Username: ");
        username = Helper.in.next();
        System.out.print("Password: ");
        password = Helper.in.next();
        if (studentController.IsValidUser(username, password)) {
            studentController.TakeControl(username, password);
        } else if (doctorController.IsValidUser(username, password)) {
            doctorController.TakeControl(username, password);
        }
    }

}
