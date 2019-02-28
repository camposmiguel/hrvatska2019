package com.miguelcr.a01_loginscreen.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcr.a01_loginscreen.MiniTwitterService;
import com.miguelcr.a01_loginscreen.R;
import com.miguelcr.a01_loginscreen.model.RequestLogin;
import com.miguelcr.a01_loginscreen.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView linkSignup;
    EditText etEmail, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        linkSignup = findViewById(R.id.textViewSignUp);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        events();
    }

    private void events() {
        linkSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
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

        // Create an object with the user information
        RequestLogin loginInfo = new RequestLogin(email, password);

        // Call to the service, including the login info
        Call<ResponseLogin> callLogin = service.doLogin(loginInfo);

        // Wait the response
        callLogin.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()) {
                    Intent i = new Intent(
                            MainActivity.this,
                            DashboardActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Houston, we have problems!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
