package com.textbookanswers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.textbookanswers.Answer;
import com.textbookanswers.R;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private ArrayList<Answer> answers;
    private OnAnswerClick onAnswerClick;

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.answer_item,viewGroup,false);
        return new AnswerViewHolder(v,onAnswerClick);
    }

    public AnswerAdapter(ArrayList<Answer> answers, OnAnswerClick onAnswerClick){

        this.answers = answers;
        this.onAnswerClick = onAnswerClick;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder answerViewHolder, int i) {

        Answer answer = answers.get(i);
        answerViewHolder.answer.setText(answer.answer);
        answerViewHolder.upvotes.setText(Integer.toString(answer.upvotes));
        answerViewHolder.downvotes.setText(Integer.toString(answer.downvotes));

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView answer,upvotes,downvotes;

        public OnAnswerClick onAnswerClick;

        public AnswerViewHolder(@NonNull View itemView,OnAnswerClick onAnswerClick) {

            super(itemView);
            answer = itemView.findViewById(R.id.answer_text);
            upvotes = itemView.findViewById(R.id.upvotes);
            downvotes = itemView.findViewById(R.id.downvotes);
            this.onAnswerClick = onAnswerClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onAnswerClick.onAnswerClick(getAdapterPosition());

        }
    }

    public interface OnAnswerClick{
        void onAnswerClick(int position);
    }

}
