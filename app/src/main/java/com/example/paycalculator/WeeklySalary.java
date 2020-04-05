package com.example.paycalculator;
import java.util.ArrayList;
import java.util.List;

public class WeeklySalary {


    private List<Float> dailyHours = new ArrayList<>();

    //I used float because because most times salary wouldn't be whole numbers
    private float weekdayRate;
    private float satRate;
    private float sunRate;


    public WeeklySalary() {
        //constructor sets the default hourly rates which are almost always used.
        weekdayRate = 38;
        satRate = 57;
        sunRate = 78;
    }

    /*
    The following three methods (setWeekdayRate, setSatRate, setSunRate) use if statements to
    check if their parameter variables are  not empty in which case it sets them as the respective
     rate
     */

    public void setWeekdayRate(String rate) {
        if (!rate.isEmpty()) {
            weekdayRate = Float.parseFloat(rate);
        }

    }

    public void setSatRate(String rate) {
        if (!rate.isEmpty()) {
            satRate = Float.parseFloat(rate);
        }
    }

    public void setSunRate(String rate) {
        if (!rate.isEmpty()) {
            sunRate = Float.parseFloat(rate);
        }
    }


    public void setDailyHours(String hour) {

        if (hour.isEmpty()) {
            hour = "0";
        }
        //add hours to list. It relies on the list being called in order of monday to sunday
        dailyHours.add(Float.valueOf(hour));
    }

    public float getDailyPay(int day) {
        // day 0 - monday to day 6 - sunday
        // uses day as an index to reference the day and calculate and return the correct salary.
        // eg: 0 is monday. monday is multiplied by weekdayRate because it is less than 5 (saturday)
        float dayPay = 0;

        if (day < 5) {
            dayPay = dailyHours.get(day) * weekdayRate;
        } else if (day == 5) {
            dayPay = dailyHours.get(day) * satRate;
        } else {
            dayPay = dailyHours.get(day) * sunRate;
        }

        return (float) (Math.round(dayPay * 100.0) / 100.0); //rounds decimal

    }

    public float getWeekSalary() {
        //method calculates the total weekly salary by adding all the days using getDailyPay()
        float sum = 0;
        for (int i = 0; i < dailyHours.size(); i++) {
            sum += getDailyPay(i);
        }

        return sum;


    }


}