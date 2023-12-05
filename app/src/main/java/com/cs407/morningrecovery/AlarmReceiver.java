package com.cs407.morningrecovery;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.media.RingtoneManager;
import android.net.Uri;
import android.media.Ringtone;

public class AlarmReceiver extends BroadcastReceiver {
    private static Ringtone ringtone;
    public static boolean alarmRing = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        // Alarm sound get trigger.
        playAlarmSound(context);

        // Alarm Message
        Toast.makeText(context, "Alarm Ringing!", Toast.LENGTH_SHORT).show();
        // Check the switch state
        boolean isQuizEnabled = getQuizEnabledState(context);
        boolean isQuoteEnabled= getQuoteEnabledState(context);
        // Launch either QuoteActivity or QuizActivity based on the switch state
        if (isQuizEnabled && !isQuoteEnabled) {
            Intent quizIntent = new Intent(context, QuizActivity.class);
            quizIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(quizIntent);
        } else if(!isQuizEnabled && isQuoteEnabled) {
            Intent quoteIntent = new Intent(context, QuoteActivity.class);
            quoteIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(quoteIntent);
        } else if (!isQuizEnabled && !isQuoteEnabled) {
            //TODO: need to add action
        }
    }

    // Helper method to stop the alarm sound
    public static void stopAlarmSound() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
    }

    private void playAlarmSound(Context context) {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }

    // Helper method to get the quiz switch state from SharedPreferences
    private boolean getQuizEnabledState(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Setting.PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(Setting.KEY_QUIZ_ENABLED, true); // Default to true if not found
    }
    private boolean getQuoteEnabledState(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Setting.PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(Setting.KEY_QUOTE_ENABLED, true); // Default to true if not found
    }
}

