package com.cs407.morningrecovery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;



import java.util.ArrayList;
import java.util.List;

// 기존에 이미 추가된 임포트 문들은 그대로 유지하면 됩니다.


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.add_btn);

        ListView listView = findViewById(R.id.alarm_list_view); // 리스트 뷰 ID 확인
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        List<Alarm> alarms = dbHelper.getAllAlarms(); // 데이터베이스에서 알람 목록 로드

        // 간단한 문자열 리스트로 변환
        List<String> alarmStrings = new ArrayList<>();
        for (Alarm alarm : alarms) {
            alarmStrings.add(alarm.getHour() + ":" + alarm.getMinute() + " " + alarm.getAmPm() + " - " + alarm.getQuizType());
        }

        // 어댑터 설정
       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alarmStrings);
        //listView.setAdapter(adapter); // 리스트 뷰에 어댑터 연결

        AlarmListAdapter adapter;
        adapter = new AlarmListAdapter(this, alarmStrings);
        listView.setAdapter(adapter);

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
    }
    private void deleteAlarm(int alarmId) {
        AlarmDbHelper dbHelper = new AlarmDbHelper(this);
        dbHelper.deleteAlarm(alarmId);
        // 여기에서 AlarmManager를 사용하여 알람을 취소할 수도 있습니다.
    }

//    private void checkAndRequestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
//                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
//                startActivity(intent);
//            }
//        }
//    }

}

