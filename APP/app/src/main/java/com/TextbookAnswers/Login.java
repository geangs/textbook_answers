package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();

        Repository.init();

        try {
            new ApiPost("/Book").execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void entrar(View view){

        String name = ((EditText)findViewById(R.id.login_name)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();

        if(Repository.validateUser(new User(name,password)))
            startActivity(new Intent(this, MainActivity.class));
        else{
            Toast.makeText(getApplicationContext(),
                    "Usuário ou senha incorretos",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrar(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }



}