package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAnswer extends AppCompatActivity {

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);
        this.getSupportActionBar().hide();

        extras = getIntent().getExtras();

        ((TextView)findViewById(R.id.add_answer_question)).setText(extras.getString("question"));

    }

    public void enviar(View view) {

        if(Repository.addAnswer(((EditText)findViewById(R.id.solution_text)).getText().toString(),extras.getString("exercise_id"))) {

            Toast.makeText(getApplicationContext(),
                    "Resposta enviada",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AnswerActivity.class);
            intent.putExtra("exercise_id", extras.getInt("exercise_id"));

            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 750);


        }
    }
}