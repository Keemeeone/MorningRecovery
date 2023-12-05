package com.cs407.morningrecovery;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.media.RingtoneManager;
import android.net.Uri;
import android.media.Ringtone;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Alarm sound get trigger.
        playAlarmSound(context);

        // Alarm Message
        Toast.makeText(context, "Alarm Ringing!", Toast.LENGTH_SHORT).show();
        Intent mainIntent = new Intent(context, QuizActivity.class);

        // Set the flag to start a new task (if not already running)
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainIntent);

    }


    private void playAlarmSound(Context context) {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }
}

