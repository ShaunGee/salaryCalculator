package com.example.paycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    EditText weekdayRateValue;
    EditText saturdayRateValue;
    EditText sundayRateValue;

    Intent settingsDoneBtnIntent;



    public void settingsDoneBtn(View view) {
        //attaches input to intent to pass back to main activity

            settingsDoneBtnIntent.putExtra("settingsWeekdayRate", weekdayRateValue.getText().toString());
            settingsDoneBtnIntent.putExtra("settingsSaturdayRate", saturdayRateValue.getText().toString());
            settingsDoneBtnIntent.putExtra("settingsSundayRate", sundayRateValue.getText().toString());

            setResult(RESULT_OK, settingsDoneBtnIntent);
            finish();

    }


    /*
    onCreate method does the following:
    - creates a new intent and points it to MainActiity
    - assigns rates (weekday,sat,sun) to respective variables from EditText GUI
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsDoneBtnIntent = new Intent(this, MainActivity.class);

        weekdayRateValue = (EditText) findViewById(R.id.settingsWeekdayRate);
        saturdayRateValue = (EditText) findViewById(R.id.settingsSaturdayRate);
        sundayRateValue = (EditText) findViewById(R.id.settingsSundayRate);


    }
}
