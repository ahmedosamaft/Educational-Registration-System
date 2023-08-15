package org.EduRegisterationSystem;

import javax.print.Doc;
import java.io.IOError;
import java.util.Scanner;

public class UserFlowController {
    public static void ShowMainMenu(){
        while(true)
        {
            System.out.println("\nPlease make a choice:");
            System.out.println("\t1 - Login");
            System.out.println("\t2 - Shutdown system");
            System.out.println("\tEnter Choice: ");

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

    public static void DoLogin(){
        System.out.println("\nPlease enter user name and password:");
        String username;
        String password;

        System.out.println("Username: ");;
        username = Helper.in.next();

        System.out.println("Password: ");;
        password = Helper.in.next();

        if(StudentManger.IsValidUser(username,password)){
            StudentManger.TakeControl(username, password);
        } else if(DoctorManger.IsValidUser(username,password)){
            DoctorManger.TakeControl(username, password);
        }


    }

}
