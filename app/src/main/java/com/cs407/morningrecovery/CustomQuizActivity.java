package com.cs407.morningrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import java.util.HashMap;

public class CustomQuizActivity extends Activity {

    private EditText questionEditText;
    private EditText answerEditText;
    // it will be used in quizActivity
    public HashMap<String, String> customQuestion = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customquiz);

        Button cancelButton = findViewById(R.id.cancelbutton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomQuizActivity.this, QuizLevelSelectActivity.class);
                startActivity(intent);
            }
        });


        questionEditText = findViewById(R.id.questionText);
        answerEditText = findViewById(R.id.answerText);
        Button saveButton = findViewById(R.id.button);
        // get question from user
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from EditText views
                String question = questionEditText.getText().toString();
                String answer = answerEditText.getText().toString();
                customQuestion.put(question, answer);
            }
        });
    }
}
