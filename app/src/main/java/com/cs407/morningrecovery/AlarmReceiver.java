package com.cs407.morningrecovery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// 기존에 이미 추가된 임포트 문들은 그대로 유지하면 됩니다.


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 알람이 울렸을 때 실행할 작업
        // 예: 토스트 메시지를 표시
        //Toast.makeText(context, "Alarm Ringing!", Toast.LENGTH_SHORT).show();
        // 추가적인 작업이 필요할 경우 여기에 구현합니다.
    }
}
