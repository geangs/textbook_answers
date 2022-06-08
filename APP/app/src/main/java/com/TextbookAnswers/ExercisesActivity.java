package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity implements ExerciseAdapter.OnExerciseClick {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Exercise> exercises;

    Bundle extras;

    ExerciseDatabase exerciseDatabase;
    ExerciseDao exerciseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        this.getSupportActionBar().hide();

        extras = getIntent().getExtras();

        exerciseDatabase = ExerciseDatabase.getInstance(this);
        exerciseDao = exerciseDatabase.exerciseDao();

        //exercises.addAll(exerciseDao.getExercises(extras.getInt("owner_id")));

        exercises = Repository.getExercises(extras.getInt("chapter_id"));

        recyclerView = findViewById(R.id.exercises_recyler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,5);
        adapter = new ExerciseAdapter(exercises,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onExerciseClick(int position) {
        Intent intent = new Intent(this,AnswerActivity.class);
        intent.putExtra("exercise_id",exercises.get(position).id);
        intent.putExtra("question",exercises.get(position).question);
        startActivity(intent);
    }

}
