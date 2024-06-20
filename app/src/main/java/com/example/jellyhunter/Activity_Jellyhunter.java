package com.example.jellyhunter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Objects;

public class Activity_Jellyhunter extends AppCompatActivity {
    private int startLives = 3;
    private final int DELAY = 500;

    private AppCompatTextView jellyhunter_TXT_score;
    private AppCompatImageView[] jellyhunter_IMG_hearts;
    private AppCompatImageButton jellyhunter_BTN_left;
    private AppCompatImageButton jellyhunter_BTN_right;
    private AppCompatImageView[][] grid;
    private GameManager gameManager;
    private int rows;
    private int cols;
    private Vibrator vib;
    private MediaPlayer[] soundEffects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jellyhunter);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        soundEffects = new MediaPlayer[] {
                MediaPlayer.create(this, R.raw.spongebob_walk1),
                MediaPlayer.create(this, R.raw.spongebob_walk2),
                MediaPlayer.create(this, R.raw.spongebob_ouch),
                MediaPlayer.create(this, R.raw.try_again)
        };

        findViews();
        this.rows = grid.length;
        this.cols = grid[0].length;
        gameManager = new GameManager(startLives, rows, cols, new Hero(new int[]{rows-1,cols/2}, R.drawable.img_spongebob));
        emptyGridWithHero();

        handler.postDelayed(runnable, DELAY);
        jellyhunter_BTN_left.setOnClickListener(v -> move("left"));
        jellyhunter_BTN_right.setOnClickListener(v -> move("right"));
    }
    private void emptyGridWithHero() {
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                grid[r][c].setImageResource(0);
            }
        }
        Hero h = gameManager.getHero();
        grid[h.getY()][h.getX()].setImageResource(h.getImage());
    }
    private void move(String dir) {
        if (Objects.equals(dir, "left"))
            soundEffects[0].start();
        else
            soundEffects[1].start();

        int[][] from_to = gameManager.move(dir);
        updateHeroMovement(from_to[0], from_to[1]);
    }
    private void updateHeroMovement(int[] from, int[] to) {
        grid[from[0]][from[1]].setImageResource(0);
        grid[to[0]][to[1]].setImageResource(gameManager.getHero().getImage());
    }
    private void updateUI(int[][] state) {
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                grid[r][c].setImageResource(state[r][c]);
            }
        }
    }
    private void updateStats(int lives, int score) {
        if (lives == 0) {
            soundEffects[3].start();
            vib.vibrate(600);
            Toast.makeText(this, "Ho No! Try again!\nScore: "+score, Toast.LENGTH_SHORT).show();
            restartGame();
            return;
        }
        for (int i=0; i<lives; i++) {
            jellyhunter_IMG_hearts[i].setImageResource(R.drawable.img_net);
        }
        for (int i=lives; i<jellyhunter_IMG_hearts.length; i++) {
            jellyhunter_IMG_hearts[i].setImageResource(0);
        }
        jellyhunter_TXT_score.setText(""+score);
    }
    private void restartGame() {
        gameManager.restartGame(startLives, new int[]{rows-1,cols/2});
        emptyGridWithHero();
        updateStats(gameManager.getLives(), gameManager.getScore());
    }
    private void findViews() {
        jellyhunter_TXT_score = findViewById(R.id.jellyhunter_TXT_score);
        jellyhunter_BTN_left = findViewById(R.id.jellyhunter_BTN_left);
        jellyhunter_BTN_right = findViewById(R.id.jellyhunter_BTN_right);

        jellyhunter_IMG_hearts = new AppCompatImageView[] {
                findViewById(R.id.jellyhunter_IMG_heart1),
                findViewById(R.id.jellyhunter_IMG_heart2),
                findViewById(R.id.jellyhunter_IMG_heart3),
        };
        grid = new AppCompatImageView[][] {
                {
                        findViewById(R.id.jellyhunter_LAY_c0r0),
                        findViewById(R.id.jellyhunter_LAY_c1r0),
                        findViewById(R.id.jellyhunter_LAY_c2r0),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r1),
                        findViewById(R.id.jellyhunter_LAY_c1r1),
                        findViewById(R.id.jellyhunter_LAY_c2r1),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r2),
                        findViewById(R.id.jellyhunter_LAY_c1r2),
                        findViewById(R.id.jellyhunter_LAY_c2r2),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r3),
                        findViewById(R.id.jellyhunter_LAY_c1r3),
                        findViewById(R.id.jellyhunter_LAY_c2r3),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r4),
                        findViewById(R.id.jellyhunter_LAY_c1r4),
                        findViewById(R.id.jellyhunter_LAY_c2r4),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r5),
                        findViewById(R.id.jellyhunter_LAY_c1r5),
                        findViewById(R.id.jellyhunter_LAY_c2r5),
                },
        };
    }

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (gameManager.isHit()) {
                soundEffects[2].start();
                vib.vibrate(200);
            }
            updateUI(gameManager.updateGame());
            updateStats(gameManager.getLives(), gameManager.getScore());

            handler.postDelayed(runnable, DELAY);
        }
    };

}