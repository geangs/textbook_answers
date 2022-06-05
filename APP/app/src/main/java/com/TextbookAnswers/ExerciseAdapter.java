package com.textbookanswers;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private ArrayList<Exercise> exercises;
    private OnExerciseClick onExerciseClick;

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercise_item,viewGroup,false);
        return new ExerciseViewHolder(v,onExerciseClick);
    }

    public ExerciseAdapter(ArrayList<Exercise> exercises, OnExerciseClick onExerciseClick){

        this.exercises = exercises;
        this.onExerciseClick = onExerciseClick;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder exerciseViewHolder, int i) {
        exerciseViewHolder.exerciseNumber.setText(Integer.toString(i+1));

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView exerciseNumber;

        public OnExerciseClick onExerciseClick;

        public ExerciseViewHolder(@NonNull View itemView, OnExerciseClick onExerciseClick) {

            super(itemView);
            exerciseNumber = itemView.findViewById(R.id.exercise_number);
            this.onExerciseClick = onExerciseClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onExerciseClick.onExerciseClick(getAdapterPosition());

        }
    }

    public interface OnExerciseClick{
        void onExerciseClick(int position);
    }

}

