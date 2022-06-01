package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ChaptersActivity extends AppCompatActivity implements ChapterAdapter.OnChapterClick {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ChapterDatabase chapterDatabase;
    ChapterDao chapterDao;
    ArrayList<Chapter> chapters;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        setTitle("Select chapter");

        extras = getIntent().getExtras();

        chapterDatabase = ChapterDatabase.getInstance(this);
        chapterDao = chapterDatabase.chapterDao();
        chapters = new ArrayList<>();
        //chapters.addAll(chapterDao.getChapters(extras.getInt("book_id")));




        recyclerView = findViewById(R.id.chapters_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ChapterAdapter(chapters,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 5; i++) {
            chapters.add(new Chapter(0,i));
        }
        adapter.notifyDataSetChanged();

//        for (int i = 0; i < 6; i++) {
//            chapterDao.insertChapter(new Chapter(extras.getInt("book_id"),chapters.size()));
//            chapters.clear();
//            chapters.addAll(chapterDao.getChapters(extras.getInt("book_id")));
//            adapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void onChapterClick(int position) {

        Intent intent = new Intent(this, SectionActivity.class);
        intent.putExtra("owner_id",chapters.get(position).id);
        startActivity(intent);

    }
}
