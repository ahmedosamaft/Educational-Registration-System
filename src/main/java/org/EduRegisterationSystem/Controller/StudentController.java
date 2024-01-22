package org.EduRegisterationSystem.Controller;

import org.EduRegisterationSystem.Data.*;
import org.EduRegisterationSystem.Entity.*;
import org.EduRegisterationSystem.Helper.Helper;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

public class StudentController {
    private Repository<Student> studentRepository;
    private Repository<Course> courseRepository;
    private Student currentStudent = new Student();

    public StudentController() {
        studentRepository = StudentRepository.getInstance();
        courseRepository = CourseRepository.getInstance();
    }

    public void showStudents() {
        System.out.println(studentRepository.getAll());
    }

    public boolean IsValidUser(String username, String password) {
        return studentRepository.contains(student -> student.equals(username, password));
    }

    public void getUser(String username, String password) {
        try {
            currentStudent = studentRepository.findBy(student -> student.equals(username, password));
        } catch (Exception e) {
            System.err.println("User not found");
        }
    }

    public void TakeControl(String username, String password) {
        getUser(username, password);
        System.out.println("Welcome " + currentStudent.getName() + ". You are logged in!");
        List<String> menu = new ArrayList<>(Arrays.asList("List My Courses", "Register in Course", "View Course", "Grades Report", "Log out"));
        while (true) {
            try {
                Helper.runMenu(menu);
                int option = Integer.parseInt(Helper.in.next());
                if (option == 1)
                    ListMyCourses();
                else if (option == 2)
                    RegisterInCourse();
                else if (option == 3)
                    ViewCourse();
                else if (option == 4)
                    ShowGradesReport();
                else if (option == 5) break;
                else
                    throw (new NumberFormatException("ERROR: Expected range is 1 to 5"));
            } catch (NumberFormatException E) {
                System.out.println(E.getMessage());
            }
        }
    }

    public void ListMyCourses() {
        for (Course course : currentStudent.getRegisteredCourses()) {
            System.out.println(course);
        }
    }

    public void RegisterInCourse() {
        List<Course> complimentCourses = new ArrayList<>();
        for (Course course : courseRepository.getAll())
            if (!currentStudent.getRegisteredCourses().contains(course)) complimentCourses.add(course);
        Helper.runMenu(complimentCourses);
        try {
            int option = Integer.parseInt(Helper.in.next());
            if (option >= 1 && option <= complimentCourses.size()) {
                complimentCourses.get(option - 1).addStudent(currentStudent);
                System.out.println("Registered Successfully!");
            } else
                throw (new NumberFormatException("ERROR: Expected range is 1 to 5"));
        } catch (NumberFormatException E) {
            System.out.println(E.getMessage());
        }
    }

    public void ViewCourse() {
        if (currentStudent.getRegisteredCourses().isEmpty()) {
            System.out.println("No registered courses");
            return;
        }
        int pos = 1;
        ShowCourses(pos);
        System.out.printf("\nWhich ith [1 - %d] course to view? ", currentStudent.getRegisteredCourses().size());
        try {
            int option = Integer.parseInt(Helper.in.next());
            if (option >= 1 && option <= currentStudent.getRegisteredCourses().size()) {
                Course chosen = currentStudent.getRegisteredCourses().get(option - 1);
                Map<Integer, Assignment> NSA = ShowCourse(chosen); // NotSubmittedAssignment
                if (NSA.isEmpty()) return;
                System.out.println("Submit an answer [Y/N]? ");
                String confirm = Helper.in.next().toLowerCase();
                if (confirm.equals("n") || confirm.equals("no")) return;
                System.out.printf("\nWhich ith [1 - %d] Assignment to Submit a solution? ", chosen.getAssignments().size());
                try {
                    option = Integer.parseInt(Helper.in.next());
                    if (option >= 1 && option <= chosen.getAssignments().size()) {
                        if (!NSA.containsKey(option)) {
                            System.out.println("Already Submitted Assignment!");
                        } else {
                            AssignmentSolution toSubmit = new AssignmentSolution();
                            System.out.println("Enter Answer: ");
                            String ans = Helper.in.next();
                            currentStudent.addSolutions(toSubmit);
                            toSubmit.setAssignment(NSA.get(option));
                            NSA.get(option).addSolution(toSubmit);
                            toSubmit.setAnswer(ans);
                            toSubmit.setGraded(false);
                        }
                    } else
                        throw (new NumberFormatException("ERROR: Expected range is 1 to " + chosen.getAssignments().size()));
                } catch (NumberFormatException E) {
                    System.out.println(E.getMessage());
                }
            } else
                throw (new NumberFormatException("ERROR: Expected range is 1 to " + currentStudent.getRegisteredCourses().size()));
        } catch (NumberFormatException E) {
            System.out.println(E.getMessage());
        }
    }

    public void ShowCourses(int pos) {
        for (Course registeredCourse : currentStudent.getRegisteredCourses()) {
            System.out.printf("\t %d - Course '%s' - Code '%S'\n", pos++, registeredCourse.getName(), registeredCourse.getCode());
        }
    }

    public Map<Integer, Assignment> ShowCourse(@NotNull Course chosen) {
        System.out.println("Course " + chosen.getName() + " with Code '" + chosen.getCode() + "'");
        System.out.println("taught by Doctor " + chosen.getDoctor().getName() + " - Email: " + chosen.getDoctor().getEmail());
        System.out.println("Course has " + chosen.getAssignments().size() + " assignment");
        Map<Integer, Assignment> notSubmittedAssignment = new HashMap<>();
        int pos = 1;
        for (Assignment assignment : chosen.getAssignments()) {
            AssignmentSolution solution = currentStudent.getAssignmentSolution(assignment);
            if (solution != null)
                System.out.println(pos++ + " - Submitted - " + (solution.isGraded() ? solution.getGrade() : "NA") + " / " + assignment.getMaxGrade() + " - Answer '" + solution.getAnswer() + "'");
            else {
                notSubmittedAssignment.put(pos, assignment);
                System.out.println(pos++ + " - NOT Submitted - NA / " + assignment.getMaxGrade());
            }
        }
        return notSubmittedAssignment;
    }

    public void ShowGradesReport() {
        Map<Course, List<AssignmentSolution>> solutionMap = new HashMap<>();
        for (AssignmentSolution assignmentSolution : currentStudent.getAssignmentSolutions()) {
            Assignment targetAssign = assignmentSolution.getAssignment();
            Course targetCourse = targetAssign.getCourse();
            solutionMap.putIfAbsent(targetCourse, new ArrayList<>());
            solutionMap.get(targetCourse).add(assignmentSolution);
        }
        for (Map.Entry<Course, List<AssignmentSolution>> entry : solutionMap.entrySet()) {
            double grade = 0;
            for (AssignmentSolution solution : entry.getValue())
                if (solution.isGraded()) grade += solution.getGrade();
            System.out.println("\tCourse '" + entry.getKey().getCode() + "' - Total Submissions " + entry.getValue().size() + " - Grade: " + grade);
        }
    }
}
