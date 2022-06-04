package com.textbookanswers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private ArrayList<Chapter> chapters;
    private OnChapterClick onChapterClick;

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chapter_item,viewGroup,false);
        return new ChapterViewHolder(v,onChapterClick);
    }

    public ChapterAdapter(ArrayList<Chapter> chapters,OnChapterClick onChapterClick){

        this.chapters = chapters;
        this.onChapterClick = onChapterClick;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder chapterViewHolder, int i) {

        Chapter chapter = chapters.get(i);
        chapterViewHolder.chapterTitle.setText(chapter.name);
        chapterViewHolder.chapterNum.setText(Integer.toString(chapter.number));


    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView chapterTitle;
        public TextView chapterNum;

        public OnChapterClick onChapterClick;

        public ChapterViewHolder(@NonNull View itemView, OnChapterClick onBookClick) {

            super(itemView);
            chapterTitle = itemView.findViewById(R.id.chapter_title);
            chapterNum = itemView.findViewById(R.id.chapter_number);
            this.onChapterClick = onBookClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onChapterClick.onChapterClick(getAdapterPosition());

        }
    }

    public interface OnChapterClick{
        void onChapterClick(int position);
    }

}
