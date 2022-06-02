package com.textbookanswers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert
    void insertExercise(Exercise exercise);

    @Query("SELECT * FROM Exercise WHERE ownerId = :chapterId")
    List<Exercise> getExercises(int chapterId);

    @Query("DELETE FROM Exercise")
    void nuke();
}
