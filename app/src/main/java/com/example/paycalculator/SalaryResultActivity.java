package com.example.paycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Objects;

public class SalaryResultActivity extends AppCompatActivity {

    Intent mainActivityIntent;
    WeeklySalary wk1Salary;
    WeeklySalary wk2Salary;

    TextView[] dailyPay_Display;

    TextView wk1_Salary_Total_Display;
    TextView wk2_Salary_Total_Display;

    TextView total_salary_Display;


    private void setDayResults() {
        /*
        The following try catch statement is used to catch any null point exceptions which can occur when an
        empty rate is entered in the settingsActivity.

        This was causing a lot of issues so I incorporated
        an if-else statement in WeeklySalary to check if empty strings were being passed.

        I also used requireNonNull because Android Studio kept highlighting it as a warning (Code was working
        fine without the change but oh well)

         */
        try {
            wk1Salary.setWeekdayRate(Objects.requireNonNull(mainActivityIntent.getStringExtra("weekdayRate")));
            wk1Salary.setSatRate(Objects.requireNonNull(mainActivityIntent.getStringExtra("saturdayRate")));
            wk1Salary.setSunRate(Objects.requireNonNull(mainActivityIntent.getStringExtra("sundayRate")));

            wk2Salary.setWeekdayRate(Objects.requireNonNull(mainActivityIntent.getStringExtra("weekdayRate")));
            wk2Salary.setSatRate(Objects.requireNonNull(mainActivityIntent.getStringExtra("saturdayRate")));
            wk2Salary.setSunRate(Objects.requireNonNull(mainActivityIntent.getStringExtra("sundayRate")));


        } catch (NullPointerException e) {
            System.out.println("exception called");
        }

        /*
        The for loop below iterates 7 times. For each iteration, a string from MainActivity's intent
        is stored to each weeklySalary object. Each string is accessed using the for loops i
        iterator by using it with String.format method. The naming convention used was (w1d1 - w1d7 and w2d1 - w2d7)

         */
        for (int i = 0; i < 7; ++i) {

            wk1Salary.setDailyHours((Objects.requireNonNull(mainActivityIntent.getStringExtra(String.format("w1d%d", (i + 1))))));
            wk2Salary.setDailyHours((Objects.requireNonNull(mainActivityIntent.getStringExtra(String.format("w2d%d", (i + 1))))));
        }

        /*
        The following for loop goes through recievedHours (Textview List) and sets each day in each week with
        its correct salary calculation using WeeklySalary class's getDaily method.

        -x is used as the index to access TextView in recievedHours
        -i is used for the 2 weeks
        -d is used for the 7 days
*/
        for (int x = 0, i = 0; i < 2; i++) {

            if (x < 7) {
                for (int d = 0; d < 7; ++d) {
                    dailyPay_Display[x++].setText(String.format("$ %s",String.valueOf(wk1Salary.getDailyPay(d))));
                }
            } else {
                for (int d = 0; d < 7; ++d) {

                    dailyPay_Display[x++].setText(String.format("$ %s", String.valueOf(wk2Salary.getDailyPay(d))));
                }
            }

        }

        /*
        The following code displays week 1 and 2 total pay. It also adds them together to give the total
        fortnight salary than displays it.
         */
        wk1_Salary_Total_Display.setText(String.format("$ %s",String.valueOf(wk1Salary.getWeekSalary())));
        wk2_Salary_Total_Display.setText(String.format("$ %s",String.valueOf(wk2Salary.getWeekSalary())));

        total_salary_Display.setText(String.format("$ %s",String.valueOf(wk1Salary.getWeekSalary() + wk2Salary.getWeekSalary())));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_result);

        mainActivityIntent = getIntent();

        wk1Salary = new WeeklySalary();
        wk2Salary = new WeeklySalary();

        dailyPay_Display = new TextView[]{

                (TextView) findViewById(R.id.wk1_monday_result),
                (TextView) findViewById(R.id.wk1_tuesday_result),
                (TextView) findViewById(R.id.wk1_wednesday_result),
                (TextView) findViewById(R.id.wk1_thursday_result),
                (TextView) findViewById(R.id.wk1_friday_result),
                (TextView) findViewById(R.id.wk1_saturday_result),
                (TextView) findViewById(R.id.wk1_sunday_result),

                (TextView) findViewById(R.id.wk2_monday_result),
                (TextView) findViewById(R.id.wk2_tuesday_result),
                (TextView) findViewById(R.id.wk2_wednesday_result),
                (TextView) findViewById(R.id.wk2_thursday_result),
                (TextView) findViewById(R.id.wk2_friday_result),
                (TextView) findViewById(R.id.wk2_saturday_result),
                (TextView) findViewById(R.id.wk2_sunday_result),
        };

        wk1_Salary_Total_Display = (TextView) findViewById(R.id.wk1_result);
        wk2_Salary_Total_Display = (TextView) findViewById(R.id.wk2_result);

        total_salary_Display = (TextView) findViewById(R.id.total_salary_calculation);

        setDayResults();
    }
}