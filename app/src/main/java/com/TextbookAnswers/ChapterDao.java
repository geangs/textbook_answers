package com.textbookanswers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChapterDao {

    @Insert
    void insertChapter(Chapter chapter);

    @Query("SELECT * FROM Chapter WHERE ownerId = :bookId")
    List<Chapter> getChapters(int bookId);

    @Query("DELETE FROM Chapter")
    void nuke();
}
