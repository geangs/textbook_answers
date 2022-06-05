package com.textbookanswers;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.textbookanswers.databinding.ActivitySearchResultBinding;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity implements BookAdapter.OnBookClick {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    BookAdapter adapter;
    ArrayList<Book> books;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        extras = getIntent().getExtras();
        books = Repository.searchBooks(extras.getString("term"));

        recyclerView = findViewById(R.id.book_result);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BookAdapter(books,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBookClick(int position) {
        Intent intent = new Intent(this, ChaptersActivity.class);
        intent.putExtra("book_id",books.get(position).id);
        startActivity(intent);
    }

}