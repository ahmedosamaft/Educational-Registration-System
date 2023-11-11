package org.EduRegisterationSystem;

import org.EduRegisterationSystem.Controller.CourseController;
import org.EduRegisterationSystem.Controller.DoctorController;
import org.EduRegisterationSystem.Controller.StudentController;
import org.EduRegisterationSystem.Controller.UserFlowController;

public class Main {
    public static void main(String[] argv) {
        UserFlowController userFlowController = new UserFlowController();
        userFlowController.ShowMainMenu();
    }
}