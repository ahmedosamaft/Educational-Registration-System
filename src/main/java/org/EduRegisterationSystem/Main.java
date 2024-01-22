package org.EduRegisterationSystem;

import org.EduRegisterationSystem.Controller.UserFlowController;

public class Main {
    public static void main(String[] argv) {
        UserFlowController userFlowController = new UserFlowController();
        userFlowController.ShowMainMenu();
    }
}
