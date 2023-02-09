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

            while(true) {
                System.out.print("Please enter the student's name: ");
                studentName = userInput.nextLine();
                if(studentName.matches("[a-zA-z\\s]+")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid name.");
                }
            }
            userInput.close();
    }

    public String getStudentName() { return studentName; }
    public String getStudentStartingDateTime() { return StudentStartingDateTime.toString(); }
}
