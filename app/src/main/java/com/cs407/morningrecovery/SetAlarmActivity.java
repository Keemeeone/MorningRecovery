package com.cs407.morningrecovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import java.util.Calendar;
import android.content.Context;
import android.provider.Settings;


public class SetAlarmActivity extends AppCompatActivity {

    Spinner spinnerHours;
    Spinner spinnerMinutes;
    Spinner spinnerAmPm;
    Spinner spinnerQuizType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        // Initialize the spinners
        spinnerHours = findViewById(R.id.spinner_hours);
        spinnerMinutes = findViewById(R.id.spinner_minutes);
        spinnerAmPm = findViewById(R.id.spinner_am_pm);
        spinnerQuizType = findViewById(R.id.spinner_quiz_type);

        // Populate the spinners with options
        populateSpinners();

        // Set up the buttons
        setUpButtons();
    }

    private void populateSpinners() {
        // Populate the quiz type spinner with options.
        String[] quizTypes = {"Math"}; // Add other quiz types if necessary
        ArrayAdapter<String> adapterQuizType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quizTypes);
        adapterQuizType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuizType.setAdapter(adapterQuizType);

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
    }

    private void setUpButtons() {
        // Set up the 'Cancel' button
        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });

        // Set up the 'Save' button
        Button saveButton = findViewById(R.id.btn_save); // Replace with your actual 'Save' button ID
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlarm();
            }
        });
    }

    private void saveAlarm() {
        // Retrieve alarm details from the spinners
        int hour = Integer.parseInt(spinnerHours.getSelectedItem().toString());
        int minute = Integer.parseInt(spinnerMinutes.getSelectedItem().toString());
        String amPm = spinnerAmPm.getSelectedItem().toString();
        String quizType = spinnerQuizType.getSelectedItem().toString();

        // Convert 12-hour time to 24-hour time if necessary
        if ("PM".equals(amPm) && hour != 12) {
            hour += 12;
        } else if ("AM".equals(amPm) && hour == 12) {
            hour = 0;
        }

        // Save the alarm details to the database
        // Note: You will need to implement the insertAlarm method in your AlarmDbHelper class
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        long alarmId = dbHelper.insertAlarm(new Alarm(0, hour, minute, amPm, quizType)); // Assuming the constructor matches the parameters

        if (alarmId != -1) {
            // Alarm was saved successfully
            // Now schedule the alarm using the AlarmManager
            // Note: You need to implement the scheduleAlarm method that schedules the alarm
            scheduleAlarm(alarmId, hour, minute);
        } else {
            // Handle the error
        }

        // Return to the main activity
        finish();
    }

    private void scheduleAlarm(long alarmId, int hour, int minute) {

        try {
            if (canSetExactAlarms()) {
                // 기존 알람 설정 로직
            } else {
                // 권한 요청
                requestExactAlarmPermission();
            }
        } catch (SecurityException e) {
            // SecurityException 처리
            requestExactAlarmPermission();
        }

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("ALARM_ID", alarmId);
//
//        // Android 12 (API 레벨 31) 이상을 대상으로 할 때 필요한 플래그
//        int flags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_IMMUTABLE : 0;
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0, intent, flags);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmPendingIntent);
//        } else {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmPendingIntent);
//        }


        // Notify the user that the alarm was set
        Toast.makeText(this, "Alarm set for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
    }

    private boolean canSetExactAlarms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            return alarmManager != null && alarmManager.canScheduleExactAlarms();
        }
        return true; // Android 12 이하 버전에서는 권한 확인 불필요
    }

    private void cancelAlarm(int alarmId) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmId, intent, 0);

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    private void requestExactAlarmPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
            startActivity(intent);
        }
    }


}
