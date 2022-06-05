package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();

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
        startActivity(new Intent(this,SignIn.class));
    }
}