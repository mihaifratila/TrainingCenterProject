import org.example.Course;
import org.example.Student;
import org.example.TimeSpentCalculator;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


public class StudentTest {

    private static final int JAVA_COURSE_DURATION = Course.Java.getCourseDuration();
    private static final int JDBC_COURSE_DURATION = Course.JDBC.getCourseDuration();
    private static final int SPRING_COURSE_DURATION = Course.Spring.getCourseDuration();
    private static final int TESTDESIGN_COURSE_DURATION = Course.TestDesign.getCourseDuration();
    private static final int PAGEOBJECT_COURSE_DURATION = Course.PageObject.getCourseDuration();
    private static final int SELENIUM_COURSE_DURATION = Course.Selenium.getCourseDuration();

    private static Student student;
    private static List<Course> courses;
    private static TimeSpentCalculator timeCalculator;


    @Before
    public void before() {
        courses = Arrays.asList(Course.Java, Course.JDBC, Course.Spring);
        student = new Student("Mihai",
                                "Java Developer",
                 LocalDateTime.parse("2023-01-06T00:00:00"),
                                          timeCalculator);

    }
    @Test
    public void studentCanEnrollInACourse() {
        //given clause present in the Before method

        //when
        student.startStudyingCourse(courses.get(0));

        // then
        assertTrue(student.getIsCurrentlyStudying());
    }

    @Test
    public void studentCannotEnrollInMultipleCoursesAtOnce() {
        //given clause present in the Before method

        //when
        for (Course course : courses) {
            student.startStudyingCourse(course);
        }

        // then
        assertTrue(courses.get(0).getIsActive());
        assertFalse(courses.get(1).getIsActive());
        assertFalse(courses.get(2).getIsActive());

    }

    @Test
    public void studyTimeIsCalculatedCorrectly() {

        // The student can study for 8 hours a day
        // If the course duration is surpassed by the study duration
        // The course should be marked as complete and the student should
        // be automatically enrolled to the next one
    }
    @Test
    public void studentIsAbleToFinishACourse() {
        //given clause present in the Before method

        //when
        // after the course is started and the duration
        // of the course if finished, the course should be completed

        // then
    }
}
