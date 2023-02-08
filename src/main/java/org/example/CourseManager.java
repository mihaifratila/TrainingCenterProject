package org.example;

import org.example.Course;
import org.example.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {

    private final Student student;
    private final static Map<String, List<Course>> coursePaths = new HashMap<>();
    public CourseManager(Student student) {
        coursePaths.put("Java Developer",
                        Arrays.asList(
                                      Course.Java,
                                      Course.JDBC,
                                      Course.Spring));
        coursePaths.put("AQE",
                        Arrays.asList(
                                      Course.TestDesign,
                                      Course.PageObject,
                                      Course.Spring));
        this.student = student;

    }
    public int getTotalCourseTime(Map<String, List<Course>> coursePath) {
        int totalCourseTime = 0;
        for(List<Course> courses : coursePath.values()) {
            for(Course course : courses) {
                totalCourseTime += course.getCourseDuration();
            }
        }
        return totalCourseTime;
    }

}
