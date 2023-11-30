package com.cs407.morningrecovery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Setting extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_QUIZ_ENABLED = "quiz_enabled";
    private static final String KEY_QUOTE_ENABLED = "quote_enabled";

    private boolean isQuizEnabled;
    private boolean isQuoteEnabled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button cancelButton = findViewById(R.id.cancel_btn);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button saveButton = findViewById(R.id.save_btn);

        // Enable or disable quiz class
        SwitchMaterial quizSwitch = findViewById(R.id.quiz);

        // Enable or disable quote class
        SwitchMaterial quoteSwitch = findViewById(R.id.quote);

        // Load saved switch states from SharedPreferences
        loadSwitchStates();

        quizSwitch.setChecked(isQuizEnabled);
        quoteSwitch.setChecked(isQuoteEnabled);

        quizSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isQuizEnabled = isChecked;
                // You can perform additional actions here when the switch state changes
            }
        });

        quoteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isQuoteEnabled = isChecked;
                // You can perform additional actions here when the switch state changes
            }
        });

        // Initialize night mode switch
        SwitchMaterial switchBtn = findViewById(R.id.nightMode);

        // Check if action bar is available
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("LIGHT-NIGHT MODE SWITCH");
        }

        // Switch theme mode per user wishes
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Checking if the switch is turned on
                if (!isChecked) {
                    // Setting theme to night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    buttonView.setText("Night Mode");
                } else {
                    // Setting theme to light theme
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    buttonView.setText("Light Mode");
                }
            }
        });

        // Add onClickListener to the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save switch states to SharedPreferences
                saveSwitchStates();

                // Display a toast message
                Toast.makeText(Setting.this, "Settings saved!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveSwitchStates() {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_QUIZ_ENABLED, isQuizEnabled);
        editor.putBoolean(KEY_QUOTE_ENABLED, isQuoteEnabled);
        editor.apply();
    }

    private void loadSwitchStates() {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        isQuizEnabled = preferences.getBoolean(KEY_QUIZ_ENABLED, true); // Default to true if not found
        isQuoteEnabled = preferences.getBoolean(KEY_QUOTE_ENABLED, true); // Default to true if not found
    }
}
