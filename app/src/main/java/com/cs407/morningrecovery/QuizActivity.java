package com.cs407.morningrecovery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private EditText editTextAnswer;
    private Button buttonSubmit;
    private TextView textViewProblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewProblem = findViewById(R.id.textViewProblem);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        textViewProblem.setText("432 + 145 = ?");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int answer = Integer.parseInt(editTextAnswer.getText().toString());
                    if (answer == 577) {
                        Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(QuizActivity.this, "Incorrect, try again!", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(QuizActivity.this, "Please enter a number.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
