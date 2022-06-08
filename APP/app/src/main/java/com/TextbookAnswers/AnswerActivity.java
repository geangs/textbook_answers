package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity implements AnswerAdapter.OnAnswerClick{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Answer> answers;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_answer);

        extras = getIntent().getExtras();

        ((TextView)findViewById(R.id.answer_question)).setText(extras.getString("question"));

        answers = Repository.getAnswers(extras.getInt("exercise_id"));

        recyclerView = findViewById(R.id.answer_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AnswerAdapter(answers,this,extras.getInt("exercise_id"));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAnswerClick(int position) {

    }

    public void addAnswer(View view){
        Intent intent = new Intent(this,AddAnswer.class);
        intent.putExtra("exercise_id",extras.getInt("exercise_id"));
        intent.putExtra("question",extras.getString("question"));
        startActivity(intent);
    }
}