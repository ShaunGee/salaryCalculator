package com.example.paycalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Intent SalaryResultIntent;
    Intent rateSettingsIntent;

    private int SETTINGS_REQUEST;

    String setWeekdayRate;
    String setSaturdayRate;
    String setSundayRate;

    EditText[] inputDailyHours;


    public void calculatePay(View view) {
        /*
        The following nested for loop iterates 14 times and inserts each user input into Salary result Intent.
        q is used as an index to access each value in enteredDailyHours list (0-13).
        w is the week (1-2).
        d is the days (1-7)

        StartActivity calls SalaryResultActivity
         */

        for (int q = 0, w = 1; w < 3; ++w) {
            for (int d = 1; d < 8; ++d) {
                SalaryResultIntent.putExtra(String.format("w%dd%d", w, d), inputDailyHours[q++].getText().toString());
            }
        }

        //adds the rates to SalaryResultIntent for processing in SalaryResultActivity
        SalaryResultIntent.putExtra("weekdayRate", setWeekdayRate);
        SalaryResultIntent.putExtra("saturdayRate", setSaturdayRate);
        SalaryResultIntent.putExtra("sundayRate", setSundayRate);

        startActivity(SalaryResultIntent);
    }


    public void settingsBtn(View view) {
        //This is activated when settings Btn is pressed. It starts SettingsActivty and passes the
        //SETTINGS_REQUEST key
        startActivityForResult(rateSettingsIntent, SETTINGS_REQUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SETTINGS_REQUEST = 9;

        //Set the intents. One for the results Activity and the other for rate settings activity
        SalaryResultIntent = new Intent(this, SalaryResultActivity.class);
        rateSettingsIntent = new Intent(this, SettingsActivity.class);

        //This following assigns the each individual EditText date from GUI to an EditText List
        //that will be used to get user input
        inputDailyHours = new EditText[]{
                (EditText) findViewById(R.id.wk1_monday),
                (EditText) findViewById(R.id.wk1_tuesday),
                (EditText) findViewById(R.id.wk1_wednesday),
                (EditText) findViewById(R.id.wk1_thursday),
                (EditText) findViewById(R.id.wk1_friday),
                (EditText) findViewById(R.id.wk1_saturday),
                (EditText) findViewById(R.id.wk1_sunday),

                (EditText) findViewById(R.id.wk2_monday),
                (EditText) findViewById(R.id.wk2_tuesday),
                (EditText) findViewById(R.id.wk2_wednesday),
                (EditText) findViewById(R.id.wk2_thursday),
                (EditText) findViewById(R.id.wk2_friday),
                (EditText) findViewById(R.id.wk2_saturday),
                (EditText) findViewById(R.id.wk2_sunday),
        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //attaches new rates from settings activity to SalaryResultIntent.
        if (requestCode == SETTINGS_REQUEST && resultCode == RESULT_OK) {

            //assigns the newly set rates to variables
            setWeekdayRate = data.getStringExtra("settingsWeekdayRate");
            setSaturdayRate = data.getStringExtra("settingsSaturdayRate");
            setSundayRate = data.getStringExtra("settingsSundayRate");

        }


    }

    /*
    The following two methods were used to save the rates values whenever the apps is reset.
    I figured that I didn't really need to implement all the usual lifecycle methods
    due to the simplicity of this app.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //adds the current rates to onSaveInstance

        outState.putString("outState_weekday_rate", setWeekdayRate);
        outState.putString("outState_saturday_rate", setSaturdayRate);
        outState.putString("outState_sunday_rate", setSundayRate);

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //re-sets the current rates from onSaveInstance straight after onCreate method is called
        setWeekdayRate = savedInstanceState.getString("outState_weekday_rate");
        setSaturdayRate = savedInstanceState.getString("outState_saturday_rate");
        setSundayRate = savedInstanceState.getString("outState_sunday_rate");

    }
}