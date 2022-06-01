package com.textbookanswers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insertBook(Book book);

    @Query("SELECT * FROM Book")
    List<Book> getBooks();

    @Query("DELETE FROM Book WHERE title = :book")
    void deleteBook(String book);

    @Query("DELETE FROM Book")
    void nuke();
}
