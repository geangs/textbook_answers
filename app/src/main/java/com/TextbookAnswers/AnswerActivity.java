package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        setTitle("answer");

        Bundle extras = getIntent().getExtras();

        ((TextView)findViewById(R.id.exerciseNumberText)).setText("Question ".concat(Integer.toString(extras.getInt("position"))));
        ((TextView)findViewById(R.id.answerText)).setText("Answer: ".concat(extras.getString("answer")));
    }
}