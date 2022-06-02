package com.textbookanswers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Section.class,version = Properties.databaseVersion)
public abstract class SectionDatabase extends RoomDatabase {

    private static SectionDatabase instance;
    public abstract SectionDao sectionDao();

    public static synchronized SectionDatabase getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext()
                    , SectionDatabase.class,"section_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
