package com.textbookanswers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SectionDao {

    @Insert
    void insertSubchapter(Section section);

    @Query("SELECT * FROM Section WHERE ownerId = :ownerId")
    List<Section> getSubchapters(int ownerId);

}
