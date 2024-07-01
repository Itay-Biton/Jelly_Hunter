package com.example.jellyhunter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.jellyhunter.interfaces.SensorCallback;
import com.example.jellyhunter.utilities.GameManager;
import com.example.jellyhunter.utilities.Hero;
import com.example.jellyhunter.utilities.MSP;
import com.example.jellyhunter.utilities.MoveManager;
import com.example.jellyhunter.utilities.UserStats;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Activity_Jellyhunter extends AppCompatActivity {
    private final int startLives = 3;
    private final int DELAY = 1000;
    private int gameSpeed = DELAY;

    private AppCompatTextView jellyhunter_TXT_jelly_score;
    private AppCompatTextView jellyhunter_TXT_meters_score;
    private AppCompatImageView[] jellyhunter_IMG_hearts;
    private AppCompatImageButton jellyhunter_BTN_back;
    private AppCompatImageButton jellyhunter_BTN_left;
    private AppCompatImageButton jellyhunter_BTN_right;
    private AppCompatImageView[][] grid;
    private GameManager gameManager;
    private int rows;
    private int cols;
    private Vibrator vib;
    private MoveManager moveManager;
    private int controlsOptions = 0; // 0 buttons, 1 sensors
    private int speedOptions = 0; // 0 slow, 1 fast, 2 tilt
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
                MediaPlayer.create(this, R.raw.ding),
                MediaPlayer.create(this, R.raw.try_again),
                MediaPlayer.create(this, R.raw.im_ready)
        };
        soundEffects[5].start();
        findViews();

        initMoveManager();
        moveManager.start();
        getControls();
        setControls();

        this.rows = grid.length;
        this.cols = grid[0].length;
        gameManager = new GameManager(startLives, rows, cols, new Hero(new int[]{rows-1,cols/2}, R.drawable.img_spongebob));
        emptyGridWithHero();
    }

    private void getControls() {
        Intent prev = getIntent();
        Bundle temp = prev.getExtras();
        if (temp == null)
            return;
        controlsOptions = temp.getInt("CONTROLS");
        speedOptions = temp.getInt("SPEED");
    }


    private void setControls() {
        if (controlsOptions == 0) {
            jellyhunter_BTN_left.setOnClickListener(v -> move("left"));
            jellyhunter_BTN_right.setOnClickListener(v -> move("right"));
            moveManager.stopX();
        } else if (controlsOptions == 1) {
            jellyhunter_BTN_left.setOnClickListener(null);
            jellyhunter_BTN_right.setOnClickListener(null);
            moveManager.startX();
        }

        if (speedOptions == 0) {
            gameSpeed = DELAY;
            moveManager.stopY();
        } else if (speedOptions == 1) {
            gameSpeed = DELAY / 2;
            moveManager.stopY();
        } else if (speedOptions == 2) {
            gameSpeed = DELAY;
            moveManager.startY();
        }
    }

    private void stopControls() {
        jellyhunter_BTN_left.setOnClickListener(null);
        jellyhunter_BTN_right.setOnClickListener(null);
        moveManager.stop();
    }


    private void initMoveManager() {
        moveManager = new MoveManager(this,
                new SensorCallback() {
                    @Override
                    public void Move(String dir) {
                        move(dir);
                    }

                    @Override
                    public void ChangeSpeed(int delta) {
                        if (gameSpeed + delta > 300 && gameSpeed + delta < 2000)
                            gameSpeed += delta;
                    }
                });
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
    private void updateStats(int lives, int jelly_score, int meter_score) {
        if (lives == 0) {
            soundEffects[4].start();
            vib.vibrate(600);
            Toast.makeText(this, "Ho No! Try again!\nScore: "+jelly_score, Toast.LENGTH_SHORT).show();
            updateScoreboard();
            restartGame();
            return;
        }
        for (int i=0; i<lives; i++) {
            jellyhunter_IMG_hearts[i].setImageResource(R.drawable.img_net);
        }
        for (int i=lives; i<jellyhunter_IMG_hearts.length; i++) {
            jellyhunter_IMG_hearts[i].setImageResource(0);
        }
        jellyhunter_TXT_jelly_score.setText(""+jelly_score);
        jellyhunter_TXT_meters_score.setText(""+meter_score);
    }
    private void restartGame() {
        gameManager.restartGame(startLives, new int[]{rows-1,cols/2});
        emptyGridWithHero();
        updateStats(gameManager.getLives(), gameManager.getJellyScore(), gameManager.getMeterScore());
    }
    private void findViews() {
        jellyhunter_TXT_jelly_score = findViewById(R.id.jellyhunter_TXT_jelly_score);
        jellyhunter_TXT_meters_score = findViewById(R.id.jellyhunter_TXT_meters_score);
        jellyhunter_BTN_left = findViewById(R.id.jellyhunter_BTN_left);
        jellyhunter_BTN_right = findViewById(R.id.jellyhunter_BTN_right);
        jellyhunter_BTN_back = findViewById(R.id.jellyhunter_BTN_back);

        jellyhunter_BTN_back.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Activity_Main_Menu_Jellyhunter.class);
            startActivity(i);
            finish();
        });

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
                        findViewById(R.id.jellyhunter_LAY_c3r0),
                        findViewById(R.id.jellyhunter_LAY_c4r0),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r1),
                        findViewById(R.id.jellyhunter_LAY_c1r1),
                        findViewById(R.id.jellyhunter_LAY_c2r1),
                        findViewById(R.id.jellyhunter_LAY_c3r1),
                        findViewById(R.id.jellyhunter_LAY_c4r1),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r2),
                        findViewById(R.id.jellyhunter_LAY_c1r2),
                        findViewById(R.id.jellyhunter_LAY_c2r2),
                        findViewById(R.id.jellyhunter_LAY_c3r2),
                        findViewById(R.id.jellyhunter_LAY_c4r2),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r3),
                        findViewById(R.id.jellyhunter_LAY_c1r3),
                        findViewById(R.id.jellyhunter_LAY_c2r3),
                        findViewById(R.id.jellyhunter_LAY_c3r3),
                        findViewById(R.id.jellyhunter_LAY_c4r3),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r4),
                        findViewById(R.id.jellyhunter_LAY_c1r4),
                        findViewById(R.id.jellyhunter_LAY_c2r4),
                        findViewById(R.id.jellyhunter_LAY_c3r4),
                        findViewById(R.id.jellyhunter_LAY_c4r4),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r5),
                        findViewById(R.id.jellyhunter_LAY_c1r5),
                        findViewById(R.id.jellyhunter_LAY_c2r5),
                        findViewById(R.id.jellyhunter_LAY_c3r5),
                        findViewById(R.id.jellyhunter_LAY_c4r5),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r6),
                        findViewById(R.id.jellyhunter_LAY_c1r6),
                        findViewById(R.id.jellyhunter_LAY_c2r6),
                        findViewById(R.id.jellyhunter_LAY_c3r6),
                        findViewById(R.id.jellyhunter_LAY_c4r6),
                },
                {
                        findViewById(R.id.jellyhunter_LAY_c0r7),
                        findViewById(R.id.jellyhunter_LAY_c1r7),
                        findViewById(R.id.jellyhunter_LAY_c2r7),
                        findViewById(R.id.jellyhunter_LAY_c3r7),
                        findViewById(R.id.jellyhunter_LAY_c4r7),
                },
        };
    }

    private void stopSounds() {
        for (MediaPlayer soundEffect : soundEffects)
            soundEffect.stop();
    }

    public void updateScoreboard() {
        MSP msp = MSP.getInstance();
        UserStats[] stats = new UserStats[11];
        UserStats current = new UserStats(
                gameManager.getJellyScore(),
                gameManager.getMeterScore(),
                0,
                0
        );

        for (int i=0; i<10; i++)
            stats[i] = new UserStats(msp.readString("STATS"+i,"0/0/0/0"));
        stats[10] = current;
        Arrays.sort(stats);

        for (int i=0; i<5; i++)
            msp.saveString("STATS"+i, stats[i].toString());
    }

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int hitState = gameManager.isHit();
            if (hitState == 1) { // hit
                soundEffects[2].start();
                vib.vibrate(100);
            }
            else if (hitState == 2) { // jelly
                soundEffects[3].start();
                vib.vibrate(100);
            }
            updateUI(gameManager.updateGame());
            updateStats(gameManager.getLives(), gameManager.getJellyScore(), gameManager.getMeterScore());

            handler.postDelayed(runnable, gameSpeed);
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        stopControls();
        stopSounds();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, gameSpeed);
        moveManager.start();
        setControls();
    }
}