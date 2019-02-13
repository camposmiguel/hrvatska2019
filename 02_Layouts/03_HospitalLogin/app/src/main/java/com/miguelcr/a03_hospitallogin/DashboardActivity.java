package com.miguelcr.a03_hospitallogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    TextView tvInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvInformation = findViewById(R.id.textViewInformation);

        // 1. Get the set of extra data
        Bundle extras = getIntent().getExtras();

        // 2. Get from extras all the information
        String name = extras.getString("userName");
        String lastName = extras.getString("userLastName");
        String password = extras.getString("userPassword");

        // 3. Set the information into the TextView
        tvInformation.setText(
                "I'm a magician. Your name is: " + name
                        + " and your password is... " + password
        );
    }
}
