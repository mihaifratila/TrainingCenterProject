package org.example;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;
public class TimeSpentCalculator {

    private static final int WORKING_HOUR_START = 10;
    private static final int WORKING_HOUR_END = 18;
    private static final int HOURS_PER_DAY = 24;
    private static final int WORKING_HOURS_PER_DAY = WORKING_HOUR_END - WORKING_HOUR_START;

    public static int getWorkingHoursSinceNow(final LocalDateTime startingTime) {
        LocalDateTime now = LocalDateTime.now();
        return getExactWorkingHoursBetweenPeriod(startingTime, now);
    }

    public static int getAllDaysInBetween(LocalDate fromDay, LocalDate toDay) {
        //Store the total of days between that starting day and the last day
            return (int) (ChronoUnit.DAYS.between(fromDay, toDay));
    }

    public static int getAllHoursBetweenPeriod(final LocalDateTime startingTime,
                                               final LocalDateTime endingTime) {
        if (startingTime == null || endingTime == null) {
            throw new IllegalStateException();
        }
        //Convert LocalDateTime to LocalDate
        LocalDate fromDay = startingTime.toLocalDate();
        LocalDate toDay = endingTime.toLocalDate();

        int allDaysBetween = getAllDaysInBetween(fromDay, toDay);
        long allHoursBetween = IntStream.range(0, allDaysBetween).count() * HOURS_PER_DAY;

        return (int) allHoursBetween;
    }

    public static int getWorkingHoursBetweenPeriod(final LocalDateTime startingTime, final LocalDateTime endingTime) {
        if (startingTime == null || endingTime == null) {
            throw new IllegalStateException();
        }

        //Convert LocalDateTime to LocalDate
        LocalDate fromDay = startingTime.toLocalDate();
        LocalDate toDay = endingTime.toLocalDate();

        int allDaysBetween = getAllDaysInBetween(fromDay, toDay);
        //Calculate the number of working hours between the starting and ending day
        // by using an IntStream ranged from 0 to allDaysBetween
        // the stream is the filtered using a lambda function that
        // iterates over all the days between the starting date and the ending date,
        // verifies if it is a working day, and if it is, increments the counter
        // After that, the value of the counter is multiplied with the value of the working hours.
        long allWorkingHours = IntStream.range(0, allDaysBetween).filter(
                i -> isWorkingDay(startingTime.plusDays(i))).count() * WORKING_HOURS_PER_DAY;

        return (int) allWorkingHours;
    }

    public static int getExactWorkingHoursBetweenPeriod(final LocalDateTime startingTime,
                                                        final LocalDateTime endingTime) {
        int workedHours = getWorkingHoursBetweenPeriod(startingTime, endingTime);
        int differenceBetweenStartingHourAndActualHour = getDifferenceBetweenStartingHourAndActualHour(startingTime,
                                                                                                       endingTime);
        int currentDayWorkedHours = getCurrentDayWorkedHours(endingTime);

        return workedHours - differenceBetweenStartingHourAndActualHour + currentDayWorkedHours;
    }

    public static int getDifferenceBetweenStartingHourAndActualHour(LocalDateTime startingTime,
                                                                    LocalDateTime endingTime) {
        int differenceBetweenStartingHourAndActualHour = 0;
//if it's a working day
        if (isWorkingDay(startingTime)) {
            // if inside working hours
            if (isWorkingHour(startingTime) && startingTime.getDayOfYear() != endingTime.getDayOfYear()) {

                return differenceBetweenStartingHourAndActualHour = (int) (Duration.between(
                        startingTime.toLocalDate().atTime(WORKING_HOUR_START, 0),
                        startingTime).abs().toHours());
            }
        }
        return differenceBetweenStartingHourAndActualHour;
    }

    public static int getCurrentDayWorkedHours(LocalDateTime endingTime) {
        int currentDayWorkedHours = 0;
        if(isWorkingHour(endingTime)) {
            return currentDayWorkedHours = (int) (Duration.between(
                    endingTime.toLocalDate().atTime(WORKING_HOUR_START, 0),
                    endingTime).abs().toHours());
        }
        return currentDayWorkedHours;
    }

 public static int getExactWorkingHoursForSelectedTime(LocalDateTime selectedTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        return Math.abs(getExactWorkingHoursBetweenPeriod(
                                                  currentTime, selectedTime));
 }

    private static boolean isWorkingHour(final LocalDateTime time) {
        int hour = time.getHour();
        return WORKING_HOUR_START <= hour && hour <= WORKING_HOUR_END;
    }

    public static boolean isWorkingDay(final LocalDateTime time) { return time.getDayOfWeek().getValue() < DayOfWeek.SATURDAY.getValue(); }
    public static int getWorkingHoursPerDay() { return WORKING_HOURS_PER_DAY; }
    public static int getHoursPerDay() { return HOURS_PER_DAY; }
}
