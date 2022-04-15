package com.textbookanswers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private ArrayList<Section> sections;
    private OnSectionClick onSectionClick;
    private int ownerId;

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_item,viewGroup,false);
        return new SectionViewHolder(v,onSectionClick);
    }

    public SectionAdapter(ArrayList<Section> sections, OnSectionClick onSectionClick){

        this.sections = sections;
        this.onSectionClick = onSectionClick;

    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder exerciseViewHolder, int i) {

        Section section = sections.get(i);
        exerciseViewHolder.sectionNumber.setText(Integer.toString(section.number+1));


    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView sectionNumber;

        public OnSectionClick onSectionClick;

        public SectionViewHolder(@NonNull View itemView, OnSectionClick onSectionClick) {

            super(itemView);
            sectionNumber = itemView.findViewById(R.id.subchapter_title);
            this.onSectionClick = onSectionClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onSectionClick.onSectionClick(getAdapterPosition());

        }
    }

    public interface OnSectionClick{
        void onSectionClick(int position);
    }

}