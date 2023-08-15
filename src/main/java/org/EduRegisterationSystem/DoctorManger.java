package org.EduRegisterationSystem;

import java.util.Arrays;
import java.util.Vector;

public class DoctorManger {
    static public Vector<Doctor> doctors = new Vector<>();
    public static Doctor currentDoctor = new Doctor();

    static public void generateDummyData() {
        Vector<String> names = new Vector<>(Arrays.asList("Ali", "Mostafa", "Hani", "Mohamed", "Ashraf", "Samy", "Morad", "Sayed", "Hussien"));
        for (int i = 1; i <= 9; ++i) {
            Doctor doctor = new Doctor();
            doctor.setUserName(Helper.generateID(i, 3, "d"));
            doctor.setPassword(Helper.generateID(i, 3, "d"));
            doctor.setName(names.get((int) (Math.random() * 9)) + " " + names.get((int) (Math.random() * 9)));
            doctor.setId(Helper.generateID(i, 3, "d") + (int) (Math.random() * 12345));
            doctor.setEmail(Helper.generateID(i, 3, "d") + "@gmail.com");
            doctors.add(doctor);
        }
    }

    static public void showDoctors() {
        System.out.println(doctors);
    }


    static public boolean IsValidUser(String username, String password) {
        for (Doctor doctor : doctors)
            if (doctor.equals(username, password)) return true;
        return false;
    }


    static public void getUser(String username, String password) {
        for (Doctor doctor : doctors)
            if (doctor.equals(username, password)) {
                currentDoctor = doctor;
                return;
            }
    }

    static public void TakeControl(String username, String password) {
        getUser(username, password);
        System.out.println("Welcome dr/ " + currentDoctor.getName() + ". You are logged in!");
        Vector<String> menu = new Vector<>(Arrays.asList("List My Courses", "Log out"));
        while (true) {
            try {
                Helper.runMenu(menu);
                int option = Integer.parseInt(Helper.in.next());
                if (option == 1)
                    ListMyCourses();
                else if (option == 2) break;
                else
                    throw (new NumberFormatException("ERROR: Expected range is 1 to 5"));
            } catch (NumberFormatException E) {
                System.out.println(E.getMessage());
            }
        }
    }

    static public void ListMyCourses() {
        int pos = 1;
        ShowCourses(pos);
        if (currentDoctor.getTeachingCourses().isEmpty()) return;
        System.out.printf("\nWhich ith [1 - %d] course to view? ", currentDoctor.getTeachingCourses().size());

        try {
            int option = Integer.parseInt(Helper.in.next());
            if (option >= 1 && option <= currentDoctor.getTeachingCourses().size()) {
                Course chosen = currentDoctor.getTeachingCourses().get(option - 1);
                System.out.println("Registered Students: " + chosen.getRegisteredStudents().size() + " Student");
                System.out.println("Total Assignments: " + chosen.getAssignments().size() + " Assignments");
                pos = 1;
                for (Assignment assignment : chosen.getAssignments()) {
                    System.out.println(pos++ + " - Assignment Code - " + assignment.getContent());
                }
                System.out.printf("\nWhich ith [1 - %d] Assignment to view? ", chosen.getAssignments().size());
                option = Integer.parseInt(Helper.in.next());
                if (option >= 1 && option <= chosen.getAssignments().size()) {
                    Assignment chosenAss = chosen.getAssignments().get(option - 1);
                    pos = 1;
                    if(chosenAss.getAssignmentSolutions().isEmpty()){
                        System.out.println("No Submitted Solutions!");
                        return;
                    }
                    for (AssignmentSolution sol : chosenAss.getAssignmentSolutions()) {
                        System.out.println(pos++ + " - Solution for Student " + sol.getStudent() + " - Grade " + (sol.isGraded() ? sol.getGrade() : "NA") + " / " + chosenAss.getMaxGrade() + " - Answer: " + sol.getAnswer());
                    }
                    System.out.printf("\nWhich ith [1 - %d] Solution to give Grade? ", chosenAss.getAssignmentSolutions().size());
                    option = Integer.parseInt(Helper.in.next());
                   if(option>= 1 && option <= chosenAss.getAssignmentSolutions().size()){
                       AssignmentSolution toMarked = chosenAss.getAssignmentSolutions().get(option - 1);
                       System.out.println("Give a grade out of " + chosenAss.getMaxGrade() + ": ");
                       double mark = Helper.in.nextDouble();
                       if(mark >= 0 && mark <= chosenAss.getMaxGrade()){
                           toMarked.setGraded(true);
                           toMarked.setGrade(mark);
                           System.out.println("Marked Successfully!");
                       }
                   }

                } else
                    throw (new NumberFormatException("ERROR: Expected range is 1 to " + chosen.getAssignments().size()));
            } else
                throw (new NumberFormatException("ERROR: Expected range is 1 to " + currentDoctor.getTeachingCourses().size()));
        } catch (NumberFormatException E) {
            System.out.println(E.getMessage());
        }
    }

    static public void ShowCourses(int pos) {
        for (Course teachedCourse : currentDoctor.getTeachingCourses()) {
            System.out.printf("\t %d - Course '%s' - Code '%S'\n", pos++, teachedCourse.getName(), teachedCourse.getCode());
        }
    }

}
