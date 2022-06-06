package com.textbookanswers;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int ownerId;
    public int number;
    public String answer;
    public String question;

    @Ignore
    public Exercise(int ownerId, int number) {
        this.ownerId = ownerId;
        this.number = number;
    }

    public Exercise(int ownerId, int number,String answer) {
        this.ownerId = ownerId;
        this.number = number;
        this.answer = answer;
    }
}
