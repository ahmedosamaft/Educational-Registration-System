package org.EduRegisterationSystem.Controller;

import org.EduRegisterationSystem.Helper.Helper;

public class UserFlowController {
    private StudentController studentController;
    private DoctorController doctorController;
    private CourseController courseController;
    public  void showData(){
        studentController.showStudents();
        doctorController.showDoctors();
        courseController.showCourses();
    }
    public UserFlowController(){
        studentController = new StudentController();
        doctorController = new DoctorController();
        courseController = new CourseController();
        showData();
        System.out.println("NOTE: You should use any of previous users - PASSWORD IS SAME AS USERNAME");
    }
    public void ShowMainMenu(){
        while(true)
        {
            System.out.println("\nPlease make a choice:");
            System.out.println("\t│─1 - Login");
            System.out.println("\t│─2 - Shutdown system");
            System.out.println("\t│─Enter Choice: ");
            System.out.print("\t└");

           try {
               int option = Integer.parseInt(Helper.in.next());
               if(option == 1)
                  DoLogin();
               else if(option == 2)
                   break;
               else
                   throw (new NumberFormatException("ERROR: Expected range is 1 to 2"));
           } catch (NumberFormatException E){
               System.out.println(E.getMessage());
           }
        }
    }

    public void DoLogin(){
        System.out.println("\nPlease enter user name and password:");
        String username;
        String password;

        System.out.println("Username: ");;
        username = Helper.in.next();

        System.out.println("Password: ");;
        password = Helper.in.next();

        if(studentController.IsValidUser(username,password)){
            studentController.TakeControl(username, password);
        } else if(doctorController.IsValidUser(username,password)){
            doctorController.TakeControl(username, password);
        }


    }

}
