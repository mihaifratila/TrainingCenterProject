package org.example;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Student {

    private final String studentName;
    private final String curriculum;
    private List<Course> enrolledCourses;
    private Course currentlyStudiedCourse;
    private final TimeSpentCalculator timeCalculator;
    private final LocalDateTime courseStartingTime;
    private boolean isCurrentlyStudying = false;


    public Student(String studentName, String curriculum, LocalDateTime courseStartingTime, TimeSpentCalculator timeCalculator) {
        this.studentName = studentName;
        this.curriculum = curriculum;
        this.courseStartingTime = courseStartingTime;
        this.timeCalculator = timeCalculator;

        enrollInCourses();
    }

    private void enrollInCourses() {
        if (this.curriculum.equals("Java Developer")) {
            this.enrolledCourses = Arrays.asList(Course.Java, Course.JDBC, Course.Spring);
        } else if (this.curriculum.equals("AQE")) {
            this.enrolledCourses = Arrays.asList(Course.TestDesign, Course.PageObject, Course.Selenium);
        } else {
            System.out.print("No adequate curriculum could be found in the training center.");
            this.enrolledCourses = null;
        }
    }

    public void startStudyingCourse(Course course) {
        if (!isCurrentlyStudying) {
            currentlyStudiedCourse = course;
            course.setIsActive(true);
            isCurrentlyStudying = true;
        }
    }

    public void completeCourse() {
        if (isCurrentlyStudying) {

        }
    }

    public boolean getIsCurrentlyStudying() {
        return isCurrentlyStudying;
    }

    public LocalDateTime getCourseStartingDate() {
        return courseStartingTime;
    }

    public int getTotalCoursesDurationInHours() {
        int totalHoursForEnrolledCourses = 0;
        for(Course course : enrolledCourses) {
            totalHoursForEnrolledCourses += course.getCourseDuration();
        }
        return totalHoursForEnrolledCourses;
    }


}
