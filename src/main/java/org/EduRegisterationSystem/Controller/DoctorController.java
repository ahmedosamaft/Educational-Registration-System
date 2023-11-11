package org.EduRegisterationSystem.Controller;

import org.EduRegisterationSystem.Data.DoctorRepository;
import org.EduRegisterationSystem.Data.Repository;
import org.EduRegisterationSystem.Entity.*;
import org.EduRegisterationSystem.Helper.Helper;

import java.util.Arrays;
import java.util.Vector;

public class DoctorController {
    private Repository<Doctor> doctorRepository;
    public Doctor currentDoctor = new Doctor();

    public DoctorController() {
        doctorRepository = DoctorRepository.getInstance();
    }

    public void showDoctors() {
        System.out.println(doctorRepository.getAll());
    }


    public boolean IsValidUser(String username, String password) {
        return doctorRepository.contains(doctor -> doctor.equals(username, password));
    }


    public void getUser(String username, String password) {
        try {
            currentDoctor = doctorRepository.findBy(doctor -> doctor.equals(username, password));
        } catch (Exception e) {
            System.err.println("User not found");
        }
    }

    public void TakeControl(String username, String password) {
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

    public void ListMyCourses() {
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
                    if (chosenAss.getAssignmentSolutions().isEmpty()) {
                        System.out.println("No Submitted Solutions!");
                        return;
                    }
                    for (AssignmentSolution sol : chosenAss.getAssignmentSolutions()) {
                        System.out.println(pos++ + " - Solution for Student " + sol.getStudent() + " - Grade " + (sol.isGraded() ? sol.getGrade() : "NA") + " / " + chosenAss.getMaxGrade() + " - Answer: " + sol.getAnswer());
                    }
                    System.out.printf("\nWhich ith [1 - %d] Solution to give Grade? ", chosenAss.getAssignmentSolutions().size());
                    option = Integer.parseInt(Helper.in.next());
                    if (option >= 1 && option <= chosenAss.getAssignmentSolutions().size()) {
                        AssignmentSolution toMarked = chosenAss.getAssignmentSolutions().get(option - 1);
                        System.out.println("Give a grade out of " + chosenAss.getMaxGrade() + ": ");
                        double mark = Helper.in.nextDouble();
                        if (mark >= 0 && mark <= chosenAss.getMaxGrade()) {
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

    public void ShowCourses(int pos) {
        for (Course teachedCourse : currentDoctor.getTeachingCourses()) {
            System.out.printf("\t %d - Course '%s' - Code '%S'\n", pos++, teachedCourse.getName(), teachedCourse.getCode());
        }
    }

}
