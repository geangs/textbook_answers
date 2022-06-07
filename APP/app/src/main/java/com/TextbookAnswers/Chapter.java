package com.textbookanswers;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Chapter {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int bookId;
    public int number;
    public String name;

    public Chapter(int bookId, int number) {
        this.bookId = bookId;
        this.number = number;
    }

    @Ignore
    public Chapter(int bookId, int number, String name) {
        this.bookId = bookId;
        this.number = number;
        this.name = name;
    }

    @Ignore
    public Chapter(int id,int bookId, int number, String name) {
        this.id = id;
        this.bookId = bookId;
        this.number = number;
        this.name = name;
    }
}
