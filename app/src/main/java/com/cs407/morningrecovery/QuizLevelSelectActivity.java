package com.cs407.morningrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizLevelSelectActivity extends Activity {
    public int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizlevelselect);

        // CUSTOM 버튼에 대한 참조를 가져옵니다.
        Button customButton = findViewById(R.id.custombutton);
        Button cancelButton = findViewById(R.id.quizselectCancelbutton);

        // CUSTOM 버튼에 클릭 리스너를 설정합니다.
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent를 사용하여 CustomQuizActivity를 시작합니다.
                Intent intent = new Intent(QuizLevelSelectActivity.this, CustomQuizActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizLevelSelectActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });

        Button easyButton = findViewById(R.id.easybutton);
        Button mediumButton = findViewById(R.id.mediumbutton);
        Button hardButton = findViewById(R.id.hardbutton);
        Button cusmButton = findViewById(R.id.custombutton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 1 when Easy button is clicked
                level = 1;
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 2 when Medium button is clicked
                level = 2;
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 3 when Hard button is clicked
                level = 3;
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 3 when Hard button is clicked
                level = 4;
            }
        });


    }
}
