package com.textbookanswers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Book.class,version = Properties.databaseVersion)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase instance;
    public abstract BookDao bookDao();

    public static synchronized BookDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
            , BookDatabase.class,"book_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

}
