import org.example.TimeSpentCalculator;
import org.junit.BeforeClass;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class TimeSpentCalculatorTest {

    private static final LocalDate endDate = LocalDate.now();
    private static LocalDateTime startTime;
    private static final LocalDateTime endTime = LocalDateTime.now();

    @Test
    public void calculatorCanGetTheDaysBetweenTwoPeriods() {
        LocalDate startDate = LocalDate.of(2022,12,31);
        final int EXPECTED_PERIOD = (int) ChronoUnit.DAYS.between(startDate, endDate);

        assertEquals(EXPECTED_PERIOD, TimeSpentCalculator.getAllDaysInBetween(startDate, endDate));
    }

    @Test
    public void calculatorCanReturnTheTotalAmountOfHoursThatPassed() {
        startTime = LocalDateTime.parse("2023-01-03T00:00:00");
        final int TOTAL_EXPECTED_HOURS = (endTime.getDayOfYear() - startTime.getDayOfYear()) *
                                          TimeSpentCalculator.getHoursPerDay();

        assertEquals(TOTAL_EXPECTED_HOURS, TimeSpentCalculator.getAllHoursBetweenPeriod(startTime, endTime));
    }

    @Test
    public void calculatorCanReturnTheTotalAmountOfWorkingHours() {
        LocalDate startDate = LocalDate.of(2023,1,1);
        startTime = LocalDateTime.parse("2023-01-01T00:00:00");
        int allDaysInBetween = TimeSpentCalculator.getAllDaysInBetween(startDate, endDate);
        int workingDaysCounter = 0;
        for(int i = 0; i < allDaysInBetween; i++) {
            if(TimeSpentCalculator.isWorkingDay(startTime.plusDays(i))) {
                workingDaysCounter++;
            }
        }

        final int EXPECTED_WORKING_HOURS = (workingDaysCounter *
                                            TimeSpentCalculator.getWorkingHoursPerDay());

        assertEquals(EXPECTED_WORKING_HOURS,
                     TimeSpentCalculator.getWorkingHoursBetweenPeriod(startTime, endTime));
    }

    @Test
    public void calculatorCanReturnTheExactAmountOfWorkingHours() {

        //when
        startTime = LocalDateTime.parse("2023-01-31T22:00:00");
        final int allDaysInBetween = TimeSpentCalculator.getAllDaysInBetween(startTime.toLocalDate(), endDate);
        final int EXPECTED_EXACT_WORKING_HOURS;
        final int firstDayWorkingHours;
        final int workingHoursBetweenFirstAndLastDay;
        final int currentDayWorkingHours;
        // if daysinbetween = 0 -> takes in consideration only the days worked in current day
        // loop through all days in between and add the working hours (except for the first and the current day)
        // those values will be calculated separately
        if (allDaysInBetween <= 0) {
            EXPECTED_EXACT_WORKING_HOURS = TimeSpentCalculator.getCurrentDayWorkedHours(endTime);
        } else if (allDaysInBetween == 1) {
            EXPECTED_EXACT_WORKING_HOURS = (TimeSpentCalculator.getWorkingHoursPerDay() -
                                            TimeSpentCalculator.getDifferenceBetweenStartingHourAndActualHour(startTime, endTime)) +
                                            TimeSpentCalculator.getCurrentDayWorkedHours(endTime);
        } else {
            int workingDaysCounter = 0;
            for(int i = 1; i < allDaysInBetween; i++) {
                if(TimeSpentCalculator.isWorkingDay(startTime.plusDays(i))) {
                    workingDaysCounter++;
                }
            }
            if(TimeSpentCalculator.isWorkingDay(startTime)) {
                firstDayWorkingHours = TimeSpentCalculator.getWorkingHoursPerDay() -
                        TimeSpentCalculator.getDifferenceBetweenStartingHourAndActualHour(startTime, endTime);
            } else {
                firstDayWorkingHours = 0;
            }

            workingHoursBetweenFirstAndLastDay = workingDaysCounter *
                    TimeSpentCalculator.getWorkingHoursPerDay();
            currentDayWorkingHours = TimeSpentCalculator.getCurrentDayWorkedHours(endTime);

            EXPECTED_EXACT_WORKING_HOURS = firstDayWorkingHours + currentDayWorkingHours + workingHoursBetweenFirstAndLastDay;
        }


        // then
        assertEquals(EXPECTED_EXACT_WORKING_HOURS, TimeSpentCalculator.getExactWorkingHoursBetweenPeriod(startTime, endTime));
    }

    @Test
    public void calculatorCanReturnHoursSincePastAction() {

        // if the selected time is in the future
        LocalDateTime lastAction = LocalDateTime.parse("2023-01-31T18:00:00");

        assertEquals(0, TimeSpentCalculator.getExactWorkingHoursForSelectedTime(lastAction));
        // calculate the difference between the selected time and the current day
    }

    @Test
    public void calculatorCanReturnTheResultsAsDaysAndHours() {
        // get the total amount of hours
        // create a days counter variable
        // if 24 hrs have passed, increment the counter
        // subtract the 24 hrs from the total amount of hours
    }
}
