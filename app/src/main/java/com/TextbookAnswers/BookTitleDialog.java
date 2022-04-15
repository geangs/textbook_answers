package com.textbookanswers;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class BookTitleDialog extends AppCompatActivity {

    EditText editText;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_title_dialog);
        editText = findViewById(R.id.book_title_editText);
        extras = getIntent().getExtras();

        BookDatabase bookDatabase = BookDatabase.getInstance(this);
        final BookDao bookDao = bookDatabase.bookDao();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId & EditorInfo.IME_MASK_ACTION) != 0) {

                    bookDao.insertBook(new Book(editText.getText().toString()));
                    back();

                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    public void back(){

        startActivity(new Intent(this,MainActivity.class));
    }
}
