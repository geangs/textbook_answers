package com.textbookanswers;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String author;

    public Book(String title) {
        this.title = title;
    }

    @Ignore
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Ignore
    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
