package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        setTitle("Select exercise");

        extras = getIntent().getExtras();

        exercises = new ArrayList<>();

        exerciseDatabase = ExerciseDatabase.getInstance(this);
        exerciseDao = exerciseDatabase.exerciseDao();
        //exercises.addAll(exerciseDao.getExercises(extras.getInt("owner_id")));



        recyclerView = findViewById(R.id.exercises_recyler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,5);
        adapter = new ExerciseAdapter(exercises,this,exerciseDao,extras.getInt("owner_id"));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 20; i++) {
            exercises.add(new Exercise(0,i,"Resposta"+i));
        }
        adapter.notifyDataSetChanged();

//        for (int i = 0; i < 30; i++) {
//            addExercise(null);
//        }
    }

    @Override
    public void onExerciseClick(int position) {
        Intent intent = new Intent(this,AnswerActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("answer",exercises.get(position).answer);
        startActivity(intent);
    }

    public void addExercise(View view){
        exerciseDao.insertExercise(new Exercise(extras.getInt("owner_id"),exerciseDao.getExercises(extras.getInt("owner_id")).size()
                ,"Resposta ".concat(Integer.toString(exerciseDao.getExercises(extras.getInt("owner_id")).size()))));
        refreshList();
    }

    public void refreshList(){

        exercises.clear();
        exercises.addAll(exerciseDao.getExercises(extras.getInt("owner_id")));
        adapter.notifyDataSetChanged();
    }

}
