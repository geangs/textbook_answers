package com.textbookanswers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Exercise.class,version = Properties.databaseVersion)
public abstract class ExerciseDatabase extends RoomDatabase {

    private static ExerciseDatabase instance;
    public abstract ExerciseDao exerciseDao();

    public static synchronized ExerciseDatabase getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext()
                    , ExerciseDatabase.class,"exercise_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
