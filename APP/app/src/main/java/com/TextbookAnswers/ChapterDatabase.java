package com.textbookanswers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Chapter.class,version = Properties.databaseVersion)
public abstract class ChapterDatabase extends RoomDatabase {

    private static ChapterDatabase instance;
    public abstract ChapterDao chapterDao();

    public static synchronized ChapterDatabase getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext()
                    , ChapterDatabase.class,"chapter_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
