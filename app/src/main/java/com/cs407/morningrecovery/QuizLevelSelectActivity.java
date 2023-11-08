package com.cs407.morningrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizLevelSelectActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizlevelselect);

        // CUSTOM 버튼에 대한 참조를 가져옵니다.
        Button customButton = findViewById(R.id.custombutton);

        // CUSTOM 버튼에 클릭 리스너를 설정합니다.
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent를 사용하여 CustomQuizActivity를 시작합니다.
                Intent intent = new Intent(QuizLevelSelectActivity.this, CustomQuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
