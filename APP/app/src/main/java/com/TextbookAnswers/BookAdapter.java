package com.textbookanswers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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
        bookViewHolder.bookAuthor.setText(book.author);
        new DownloadImageTask(bookViewHolder.bookCover).execute(book.coverUrl);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView bookTitle;
        public TextView bookAuthor;
        public ImageView bookCover;

        public OnBookClick onBookClick;

        public BookViewHolder(@NonNull View itemView,OnBookClick onBookClick) {

            super(itemView);
            bookTitle = itemView.findViewById(R.id.fjdsiao);
            bookAuthor = itemView.findViewById(R.id.autor);
            bookCover = itemView.findViewById(R.id.book_cover);

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

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
