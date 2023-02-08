package org.example;

import java.util.Scanner;

public class UserInputHandler {
    private final Scanner userInput = new Scanner(System.in);
    private String studentName;
    String coursePath;
    private StringBuilder StudentStartingDateTime;

    public UserInputHandler() {
        setStudentName();
    }

    public void setStudentName() {
        System.out.print("Please enter the student name: ");
        studentName = userInput.next("^[A-Za-z]+$");
    }

    public String getStudentName() { return studentName; }
    public String getStudentStartingDateTime() { return StudentStartingDateTime.toString(); }
}
