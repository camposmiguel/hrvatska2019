package com.miguelcr.a01_duckhunt;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvNick, tvTimer, tvDucks;
    ImageView duck;
    Random rand;
    int counter = 0;
    boolean gameIsOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Hide the toolbar
        getSupportActionBar().hide();

        tvNick = findViewById(R.id.textViewNick);
        tvTimer = findViewById(R.id.textViewTimer);
        tvDucks = findViewById(R.id.textViewDucks);
        duck = findViewById(R.id.imageViewDuck);

        // Extras information
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString("nickName");

        // Set the nick into the TextView
        tvNick.setText(nick);

        // create the Random object
        rand = new Random();

        // start countdown timer
        startCountDown();

    }

    private void startCountDown() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                tvTimer.setText("Game over!");
                gameIsOver = true;
                showGameOverDialog();
            }
        }.start();

    }

    private void showGameOverDialog() {
        // Dialog configuration
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("You'have hunted " + counter + "ducks."
                + "Do you want to restart the game?");

        builder.setTitle("Game over");

        builder.setCancelable(false);

        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Restart the game
                counter = 0;
                tvDucks.setText("0");
                gameIsOver = false;
                startCountDown();
                randomDuckPosition();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Close the dialog and the GameActivity
                dialog.dismiss();
                finish();
            }
        });

        // Create and Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void duckHunted(View view) {
        if(gameIsOver) {
            Toast.makeText(this, "Game is over",
                    Toast.LENGTH_SHORT).show();
        } else {
            randomDuckPosition();

            // 4. Increase the counter
            counter++;

            // 5. Refresh the counter in the TextView
            tvDucks.setText(String.valueOf(counter));
        }

    }

    private void randomDuckPosition() {
        // 1. Get the screen size where we're running the app
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        int maxX = screenWidth - duck.getWidth();
        int maxY = screenHeight - duck.getHeight();

        // 2. Generate a random X,Y position
        int randomX = rand.nextInt((maxX - 0) + 1) + 0;
        int randomY = rand.nextInt((maxY - 0) + 1) + 0;

        // 3. Move the duck to the new Random position
        duck.setX(randomX);
        duck.setY(randomY);
    }

}
