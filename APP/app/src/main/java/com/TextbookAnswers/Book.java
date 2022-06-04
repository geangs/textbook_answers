package com.textbookanswers;

import androidx.room.Entity;
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

}
