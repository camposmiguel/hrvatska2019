package com.miguelcr.a02_tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etPlayer1, etPlayer2;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPlayer1 = findViewById(R.id.editTextPlayer1);
        etPlayer2 = findViewById(R.id.editTextPlayer2);
        btnStart = findViewById(R.id.buttonStartGame);

        // Click event
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String p1Name = etPlayer1.getText().toString();
        String p2Name = etPlayer2.getText().toString();

        if(p1Name.isEmpty() || p2Name.isEmpty()) {
            Toast.makeText(
                    this,
                    "Write the player names please",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Start the game
            Intent i = new Intent(this, GameActivity.class);

            // Send the information to the GameActivity
            i.putExtra("P1", p1Name);
            i.putExtra("P2", p2Name);

            startActivity(i);
        }
    }
}
