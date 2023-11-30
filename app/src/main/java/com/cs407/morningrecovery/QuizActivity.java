package com.cs407.morningrecovery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class QuizActivity extends AppCompatActivity {


    private EditText editTextAnswer;
    private Button buttonSubmit;
    private TextView textViewProblem;


    private HashMap<String, String> easy = new HashMap<>();
    private HashMap<String, String> medium = new HashMap<>();
    private HashMap<String, String> hard = new HashMap<>();

    QuizLevelSelectActivity getLevel = new QuizLevelSelectActivity();
    CustomQuizActivity getCustom = new CustomQuizActivity();
    int quizLevel = getLevel.level;
    HashMap<String, String> custom = getCustom.customQuestion;
    String problem;

    // Quiz creator. called by createQuiz() method
    public void createEasy(){
        easy.put("25 + 7" , "32");
        easy.put("7 * 9" , "63");
        easy.put("20 - 7" , "13");
        easy.put("40 / 5" , "8");
    }

    public void createMedium(){
        medium.put("18 + 88", "106");
        medium.put("2 * 29", "58");
        medium.put("500 - 320", "180");
        medium.put("25 * 8", "200");
    }

    public void createHard(){
        hard.put("240 + 318", "558");
        hard.put("12 * 36", "432");
        hard.put("289 - 173", "116");
        hard.put("86 * 45", "3870");
    }

    // create quiz and set the problem
    // 1 : easy, 2 : medium, 3 : hard, 4 : custom
    public void createQuiz(int quizLevel){
        if(quizLevel == 1){
            createEasy();
            for (String key : easy.keySet()) {
                //("Key: " + key + ", Value: " + easy.get(key));
                // quiz print out
                problem = key;
                textViewProblem.setText(key + " = ?");
            }
        }else if(quizLevel == 2){
            createMedium();
            for (String key : medium.keySet()) {
                //("Key: " + key + ", Value: " + easy.get(key));
                // quiz print out
                problem = key;
                textViewProblem.setText(key + " = ?");
            }
        }else if(quizLevel == 3){
            createHard();
            for (String key : hard.keySet()) {
                //("Key: " + key + ", Value: " + easy.get(key));
                // quiz print out
                problem = key;
                textViewProblem.setText(key + " = ?");
            }
        }else{
            for (String key : custom.keySet()) {
                //("Key: " + key + ", Value: " + easy.get(key));
                // quiz print out
                problem = key;
                textViewProblem.setText(key + " = ?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewProblem = findViewById(R.id.textViewQuestion);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuiz(quizLevel);
                try {
                    // check the answer
                    int answer = 0;
                    if(quizLevel == 1){
                        answer = Integer.parseInt(easy.get(problem));
                    }else if(quizLevel == 2){
                        answer = Integer.parseInt(medium.get(problem));
                    }else if(quizLevel == 3){
                        answer = Integer.parseInt(hard.get(problem));
                    }else{
                        answer = Integer.parseInt(custom.get(problem));
                    }
                    int userAnswer = Integer.parseInt(editTextAnswer.getText().toString());
                    if (userAnswer == answer) {
                        Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_LONG).show();
                        // TODO : move to quote page
                    } else {
                        Toast.makeText(QuizActivity.this, "Incorrect, try again!", Toast.LENGTH_LONG).show();
                        // do nothing?
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(QuizActivity.this, "Please enter a number.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
