package com.miguelcr.a03_hospitallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etLastName, etEmail, etCity, etPass, etPassRepeat;
    Button btnSignUp;
    CheckBox checkBoxTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Loading the layout
        setContentView(R.layout.activity_main);

        // Get a reference for each View component
        etName = findViewById(R.id.editTextName);
        etLastName = findViewById(R.id.editTextLastName);
        etEmail = findViewById(R.id.editTextEmail);
        etCity = findViewById(R.id.editTextCity);
        etPass = findViewById(R.id.editTextPassword);
        etPassRepeat = findViewById(R.id.editTextPasswordConfirm);
        btnSignUp = findViewById(R.id.button);
        checkBoxTerms = findViewById(R.id.checkboxTerms);

        // Disable by default the Button
        btnSignUp.setClickable(false);
    }

    public void signUp(View view) {
        Toast.makeText(
                this,
                "Click on signup",
                Toast.LENGTH_SHORT).show();

        // Get the values from the view components
        String name = etName.getText().toString();
        String lastName = etLastName.getText().toString();
        String city = etCity.getText().toString();
        String password = etPass.getText().toString();
        String passwordRepeat = etPassRepeat.getText().toString();
        String email = etEmail.getText().toString();

        if(name.isEmpty()) {
            etName.setError("Name is required");
        } else if(lastName.isEmpty()) {
            etLastName.setError("Last name is required");
        } else if(email.isEmpty()) {
            etEmail.setError("Email is required");
        } else if(password.isEmpty()) {
            etPass.setError("Password is required");
        } else if(passwordRepeat.isEmpty()) {
            etPassRepeat.setError("You must to confirm password");
        } else if(!password.equals(passwordRepeat)) {
            etPassRepeat.setError("The passwords are differents");
        } else if(city.isEmpty()) {
            etCity.setError("City is required");
        } else {

            // Open in the device a new screen (Activity)
            Intent i = new Intent(this, DashboardActivity.class);

            // Send information to the DashboardActivity
            i.putExtra("userName", name);
            i.putExtra("userLastName", lastName);
            i.putExtra("userPassword", password);

            // Launch the Activity
            startActivity(i);
        }

    }

    public void changeTerms(View view) {

        /*
                    VIEW
                     |
          ----------------------------------
          |          |            |        |
        TEXTVIEW    EDITTEXT    CHECKBOX  BUTTON

         */

        CheckBox c = (CheckBox) view;

        if(c.isChecked()) {
            btnSignUp.setClickable(true);
        } else {
            btnSignUp.setClickable(false);
        }

    }
}
