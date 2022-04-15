package com.textbookanswers;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Section {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int ownerId;
    public int number;

    public Section(int ownerId, int number) {
        this.ownerId = ownerId;
        this.number = number;
    }
}
