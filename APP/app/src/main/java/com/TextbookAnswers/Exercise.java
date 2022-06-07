package com.textbookanswers;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int chapterId;
    public int number;
    public String answer;
    public String question;

    @Ignore
    public Exercise(int chapterId, int number) {
        this.chapterId = chapterId;
        this.number = number;
    }

    public Exercise(int chapterId, int number, String answer) {
        this.chapterId = chapterId;
        this.number = number;
        this.answer = answer;
    }
}
