package com.cs407.morningrecovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SetAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        Spinner spinnerHours = findViewById(R.id.spinner_hours);
        Spinner spinnerMinutes = findViewById(R.id.spinner_minutes);
        Spinner spinnerAmPm = findViewById(R.id.spinner_am_pm);


        // Populate the hours spinner (1-12 for standard time format).
        String[] hours = new String[12];
        for (int i = 0; i < hours.length; i++) {
            hours[i] = Integer.toString(i + 1);
        }
        ArrayAdapter<String> adapterHours = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHours.setAdapter(adapterHours);

        // Populate the minutes spinner (0-59).
        String[] minutes = new String[60];
        for (int i = 0; i < minutes.length; i++) {
            minutes[i] = String.format("%02d", i);
        }
        ArrayAdapter<String> adapterMinutes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minutes);
        adapterMinutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinutes.setAdapter(adapterMinutes);

        // Populate the AM/PM spinner.
        String[] amPmOptions = {"AM", "PM"};
        ArrayAdapter<String> adapterAmPm = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, amPmOptions);
        adapterAmPm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmPm.setAdapter(adapterAmPm);

        // Find the "CANCEL" button by its ID
        Button cancelButton = findViewById(R.id.btn_cancel);

        // Add an OnClickListener to the "CANCEL" button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to return to MainActivity
                Intent intent = new Intent(SetAlarmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button quizType_btn = findViewById(R.id.qType_btn);
        quizType_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAlarmActivity.this,)
            }
        });

    }
}
