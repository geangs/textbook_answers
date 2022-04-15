package com.textbookanswers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> books;
    private OnBookClick onBookClick;

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item,viewGroup,false);
        return new BookViewHolder(v,onBookClick);
    }

    public BookAdapter(ArrayList<Book> books, OnBookClick onBookClick){

        this.books = books;
        this.onBookClick = onBookClick;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {

        Book book = books.get(i);
        bookViewHolder.bookTitle.setText(book.title);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView bookTitle;
        public OnBookClick onBookClick;

        public BookViewHolder(@NonNull View itemView,OnBookClick onBookClick) {

            super(itemView);
            bookTitle = itemView.findViewById(R.id.book_title);
            this.onBookClick = onBookClick;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onBookClick.onBookClick(getAdapterPosition());

        }
    }

    public interface OnBookClick{
        void onBookClick(int position);
    }

}
