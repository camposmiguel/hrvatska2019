package com.miguelcr.a01_loginscreen.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miguelcr.a01_loginscreen.MiniTwitterService;
import com.miguelcr.a01_loginscreen.R;
import com.miguelcr.a01_loginscreen.model.RequestSignup;
import com.miguelcr.a01_loginscreen.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail, etPassword, etUsername;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etUsername = findViewById(R.id.editTextUsername);
        btnSignup = findViewById(R.id.buttonSignup);

        events();
    }

    private void events() {
        btnSignup.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.buttonSignup) {
            doSignup();
        }
    }

    private void doSignup() {
        // Connect to the server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://minitwitter.com:3001/apiv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Call to the login method in the server
        MiniTwitterService service = retrofit.create(MiniTwitterService.class);

        // Get the information written by user
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String username = etUsername.getText().toString();

        // Create an object with the user information
        RequestSignup signupInfo = new RequestSignup(username, email, password, "UDEMYANDROID");

        // Call to the service, including the login info
        Call<ResponseLogin> callLogin = service.doSignup(signupInfo);

        // Wait the response
        callLogin.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()) {
                    Intent i = new Intent(
                            SignUpActivity.this,
                            DashboardActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(SignUpActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Houston, we have problems!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
