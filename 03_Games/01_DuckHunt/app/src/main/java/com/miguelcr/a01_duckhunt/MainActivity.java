package com.miguelcr.a01_duckhunt;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNick;
    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNick =  findViewById(R.id.editTextNickname);
        btnStartGame =  findViewById(R.id.buttonStart);

        // Load the font type
        Typeface type = Typeface.createFromAsset(getAssets(),"pixel.ttf");

        // Apply the font type in the EditText
        etNick.setTypeface(type);
        btnStartGame.setTypeface(type);

        // Click event
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1. Get the nickname from the EditText
                String nick = etNick.getText().toString();

                if(nick.isEmpty()) {
                    etNick.setError("Nickname is required");
                } else {
                    Intent i = new Intent(
                            MainActivity.this,
                            GameActivity.class
                    );

                    // 2. putExtra nickname into the Intent object
                    i.putExtra("nickName", nick);

                    startActivity(i);
                }
            }
        });
    }
}
