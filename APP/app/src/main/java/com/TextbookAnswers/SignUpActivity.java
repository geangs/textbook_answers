package com.textbookanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getSupportActionBar().hide();
    }

    public void cadastrar(View view){

        String name = ((EditText)findViewById(R.id.register_name)).getText().toString();
        String password = ((EditText)findViewById(R.id.register_password)).getText().toString();
        String password2 = ((EditText)findViewById(R.id.register_password2)).getText().toString();
        String email = ((EditText)findViewById(R.id.register_email)).getText().toString();

        if(!password.equals(password2))
            Toast.makeText(getApplicationContext(),
                    "Senhas não são iguais",
                    Toast.LENGTH_SHORT).show();
        else if(password.length() < 8)
            Toast.makeText(getApplicationContext(),
                    "A senha deve ter pelo menos de 8 caracteres",
                    Toast.LENGTH_SHORT).show();
        else if(Repository.validateUser(new User(name,email,password))){
            Toast.makeText(getApplicationContext(),
                    "Cadastro bem sucedido",
                    Toast.LENGTH_SHORT).show();

            SignUpActivity context = this;
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(context,MainActivity.class));
                }
            }, 750);


        }
        else
            Toast.makeText(getApplicationContext(),
                    "Usuário já existe",
                    Toast.LENGTH_SHORT).show();
    }
}

