package com.textbookanswers;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Chapter {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int ownerId;
    public int number;
    public String name;

    public Chapter(int ownerId, int number) {
        this.ownerId = ownerId;
        this.number = number;
    }

    @Ignore
    public Chapter(int ownerId, int number, String name) {
        this.ownerId = ownerId;
        this.number = number;
        this.name = name;
    }
}
