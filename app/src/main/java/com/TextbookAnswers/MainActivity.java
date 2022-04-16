package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookAdapter.OnBookClick {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Book> books;

    BookDatabase bookDatabase;
    BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDatabase = BookDatabase.getInstance(this);
        bookDao = bookDatabase.bookDao();

        books = new ArrayList<>();

        //books.addAll(bookDao.getBooks());


        recyclerView = findViewById(R.id.books_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BookAdapter(books,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        books.add(new Book("Livro teste 1"));
        adapter.notifyDataSetChanged();

        //bookDao.insertBook(new Book("Livro teste 1"));

    }

    @Override
    public void onBookClick(int position) {

        Intent intent = new Intent(this, ChaptersActivity.class);
        intent.putExtra("book_id",position);
        startActivity(intent);
    }

}
