package com.cs407.morningrecovery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.add_btn);

        ListView listView = findViewById(R.id.alarm_list_view);
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        List<Alarm> alarms = dbHelper.getAllAlarms();

        List<String> alarmStrings = new ArrayList<>();
        for (Alarm alarm : alarms) {
            alarmStrings.add(alarm.getHour() + ":" + alarm.getMinute() + " " + alarm.getAmPm() + " - " + alarm.getQuizType());
        }

        AlarmListAdapter adapter;
        adapter = new AlarmListAdapter(this, alarmStrings);
        listView.setAdapter(adapter);

        Button setButton = findViewById(R.id.setting_btn);
        Button test = findViewById(R.id.edit_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start SetAlarmActivity
                Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
                startActivity(intent);

                AlarmDbHelper dbHelper = new AlarmDbHelper(MainActivity.this);
                List<Alarm> updatedAlarms = dbHelper.getAllAlarms();

                List<String> updatedAlarmStrings = new ArrayList<>();
                for (Alarm alarm : updatedAlarms) {
                    updatedAlarmStrings.add(alarm.getHour() + ":" + alarm.getMinute() + " " + alarm.getAmPm() + " - " + alarm.getQuizType());
                }

                adapter.updateData(updatedAlarmStrings);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Alarm selectedAlarm = alarms.get(position);
                deleteAlarm(selectedAlarm.getId());
                return true;
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
    private void deleteAlarm(int alarmId) {
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        dbHelper.deleteAlarm(alarmId);
        // 대균 TODO: AlarmManager를 사용하여 알람을 취소
    }


}

