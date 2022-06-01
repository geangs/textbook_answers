package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class SectionActivity extends AppCompatActivity implements SectionAdapter.OnSectionClick {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    SectionDatabase sectionDatabase;
    SectionDao sectionDao;
    ArrayList<Section> sections;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        setTitle("Select section");

        extras = getIntent().getExtras();

        sectionDatabase = SectionDatabase.getInstance(this);
        sectionDao = sectionDatabase.sectionDao();
        sections = new ArrayList<>();
        //sections.addAll(sectionDao.getSubchapters(extras.getInt("owner_id")));



        recyclerView = findViewById(R.id.subchapter_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SectionAdapter(sections,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 5; i++) {
            sections.add(new Section(0,i));
        }
        adapter.notifyDataSetChanged();

//        for (int i = 0; i < 6; i++) {
//            sectionDao.insertSubchapter(new Section(extras.getInt("owner_id"), sectionDao.getSubchapters(extras.getInt("owner_id")).size()));
//            sections.clear();
//            sections.addAll(sectionDao.getSubchapters(extras.getInt("owner_id")));
//            adapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void onSectionClick(int position) {

        Intent intent = new Intent(this,ExercisesActivity.class);
        intent.putExtra("owner_id",sections.get(position).id);
        startActivity(intent);
    }

}
