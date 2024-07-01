package com.example.jellyhunter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

public class Activity_Main_Menu_Jellyhunter extends AppCompatActivity {

    private AppCompatImageButton jellyhunter_BTN_launch;
    private AppCompatImageButton jellyhunter_BTN_scoreboard;
    private AppCompatImageButton jellyhunter_BTN_slow;
    private AppCompatImageButton jellyhunter_BTN_fast;
    private AppCompatImageButton jellyhunter_BTN_tilt;
    private AppCompatImageButton jellyhunter_BTN_button_control;
    private AppCompatImageButton jellyhunter_BTN_sensor_control;

    private MediaPlayer[] soundEffects;

    private int controlsOptions = 0; // 0 buttons, 1 sensors
    private int speedOptions = 0; // 0 slow, 1 fast, 2 tilt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_jellyhunter);
        soundEffects = new MediaPlayer[] {
                MediaPlayer.create(this, R.raw.bubble_transition)
        };
        soundEffects[0].start();

        findViews();

        changeSpeed(speedOptions);
        changeControls(controlsOptions);
    }
    private void findViews() {

        jellyhunter_BTN_launch = findViewById(R.id.jellyhunter_BTN_launch);
        jellyhunter_BTN_scoreboard = findViewById(R.id.jellyhunter_BTN_scoreboard);
        jellyhunter_BTN_slow = findViewById(R.id.jellyhunter_BTN_slow);
        jellyhunter_BTN_fast = findViewById(R.id.jellyhunter_BTN_fast);
        jellyhunter_BTN_tilt = findViewById(R.id.jellyhunter_BTN_tilt);
        jellyhunter_BTN_button_control = findViewById(R.id.jellyhunter_BTN_button_control);
        jellyhunter_BTN_sensor_control = findViewById(R.id.jellyhunter_BTN_sensor_control);

        jellyhunter_BTN_launch.setOnClickListener(v -> launchGame());
        jellyhunter_BTN_scoreboard.setOnClickListener(v -> launchScoreboard());
        jellyhunter_BTN_slow.setOnClickListener(v -> changeSpeed(0));
        jellyhunter_BTN_fast.setOnClickListener(v -> changeSpeed(1));
        jellyhunter_BTN_tilt.setOnClickListener(v -> changeSpeed(2));
        jellyhunter_BTN_button_control.setOnClickListener(v -> changeControls(0));
        jellyhunter_BTN_sensor_control.setOnClickListener(v -> changeControls(1));
    }

    private void launchGame() {
        Intent i = new Intent(getApplicationContext(), Activity_Jellyhunter.class);
        Bundle bundle = new Bundle();
        bundle.putInt("SPEED", speedOptions);
        bundle.putInt("CONTROLS", controlsOptions);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }

    private void launchScoreboard() {
        Intent i = new Intent(getApplicationContext(), Activity_Scoreboard_Jellyhunter.class);
        startActivity(i);
        finish();
    }

    private void changeSpeed(int option) {
        switch (option) {
            case 0:
                jellyhunter_BTN_slow.setAlpha(0.5f);
                jellyhunter_BTN_fast.setAlpha(1f);
                jellyhunter_BTN_tilt.setAlpha(1f);
                break;
            case 1:
                jellyhunter_BTN_slow.setAlpha(1f);
                jellyhunter_BTN_fast.setAlpha(0.5f);
                jellyhunter_BTN_tilt.setAlpha(1f);
                break;
            case 2:
                jellyhunter_BTN_slow.setAlpha(1f);
                jellyhunter_BTN_fast.setAlpha(1f);
                jellyhunter_BTN_tilt.setAlpha(0.5f);
                break;
            default:
                return;
        }
        speedOptions = option;
    }

    private void changeControls(int option) {
        switch (option) {
            case 0:
                jellyhunter_BTN_button_control.setAlpha(0.5f);
                jellyhunter_BTN_sensor_control.setAlpha(1f);
                break;
            case 1:
                jellyhunter_BTN_button_control.setAlpha(1f);
                jellyhunter_BTN_sensor_control.setAlpha(0.5f);
                break;
            default:
                return;
        }
        controlsOptions = option;
    }



    private void stopSounds() {
        for (MediaPlayer soundEffect : soundEffects)
            soundEffect.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSounds();
    }
}
