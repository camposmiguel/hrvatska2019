package com.miguelcr.a01_loginscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView linkSignup;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        linkSignup = findViewById(R.id.textViewSignUp);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);

        events();
    }

    private void events() {
        linkSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.textViewSignUp) {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        } else if(id == R.id.buttonLogin) {
            doLogin();
        }

    }

    private void doLogin() {

    }
}
