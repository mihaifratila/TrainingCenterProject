package org.example;

public enum Course {

    Java("Java", 16),
    JDBC("JDBC", 24),
    Spring("Spring", 16),
    TestDesign("Test Design", 10),
    PageObject("Page Object", 16),
    Selenium("Selenium", 16);

    private final int courseDuration;
    private final String courseName;
    private boolean isCourseActive;
    private boolean isCourseCompleted;

    Course(String courseName, int courseDuration) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public boolean getIsActive() {
        return isCourseActive;
    }

    public void setIsActive(boolean isActive) {
        this.isCourseActive = isActive;
    }

    public boolean isCourseCompleted() {
        return isCourseCompleted;
    }

    public void setCourseCompleted(boolean courseCompleted) {
        isCourseCompleted = courseCompleted;
    }
}
