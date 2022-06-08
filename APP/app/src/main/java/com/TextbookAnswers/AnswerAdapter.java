package com.textbookanswers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.textbookanswers.Answer;
import com.textbookanswers.R;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private ArrayList<Answer> answers;
    private OnAnswerClick onAnswerClick;
    public int exerciseId;

    public AnswerAdapter(ArrayList<Answer> answers, OnAnswerClick onAnswerClick,int exerciseId){

        this.answers = answers;
        this.onAnswerClick = onAnswerClick;
        this.exerciseId = exerciseId;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.answer_item,viewGroup,false);
        return new AnswerViewHolder(v,onAnswerClick,exerciseId);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder answerViewHolder, int i) {

        Answer answer = answers.get(i);
        answerViewHolder.answer.setText(answer.answer);
        answerViewHolder.upvotes.setText(Integer.toString(answer.upvotes));
        answerViewHolder.downvotes.setText(Integer.toString(answer.downvotes));
        answerViewHolder.position  = answerViewHolder.getAdapterPosition();
        answerViewHolder.user.setText(answer.user);

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView answer,upvotes,downvotes,user;
        public int position,exerciseId;
        public OnAnswerClick onAnswerClick;

        public AnswerViewHolder(@NonNull View itemView,OnAnswerClick onAnswerClick,int exerciseId) {

            super(itemView);
            answer = itemView.findViewById(R.id.answer_text);
            upvotes = itemView.findViewById(R.id.upvotes);
            downvotes = itemView.findViewById(R.id.downvotes);
            user = itemView.findViewById(R.id.answer_user);

            this.exerciseId = exerciseId;

            itemView.findViewById(R.id.upvote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Repository.upvote(Repository.getAnswer(exerciseId,position));
                    upvotes.setText(Integer.toString(Integer.parseInt(upvotes.getText().toString())+1));
                }
            });

            itemView.findViewById(R.id.downvote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Repository.downvote(Repository.getAnswer(exerciseId,position));
                    downvotes.setText(Integer.toString(Integer.parseInt(downvotes.getText().toString())+1));
                }
            });

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
