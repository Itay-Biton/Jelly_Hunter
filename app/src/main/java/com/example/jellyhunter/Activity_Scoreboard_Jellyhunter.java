package com.example.jellyhunter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.jellyhunter.interfaces.LocationCallback;

public class Activity_Scoreboard_Jellyhunter extends AppCompatActivity {

    private AppCompatImageButton jellyhunter_BTN_back;

    private MediaPlayer[] soundEffects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_jellyhunter);

        soundEffects = new MediaPlayer[] {
                MediaPlayer.create(this, R.raw.bubble_transition)
        };
        soundEffects[0].start();
        initFragments();
        findViews();
    }

    private void initFragments() {
        Fragment_Map fragmentMap = new Fragment_Map();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.jellyhunter_LAY_map, fragmentMap)
                .commit();
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void goToCoords(double lat, double lng) {
                fragmentMap.goToCoords(lat, lng);
            }
        };
        Fragment_Scoreboard fragmentScoreboard = new Fragment_Scoreboard(locationCallback);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.jellyhunter_LAY_scoreboard, fragmentScoreboard)
                .commit();
    }
    private void findViews() {
        jellyhunter_BTN_back = findViewById(R.id.jellyhunter_BTN_back);

        jellyhunter_BTN_back.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Activity_Main_Menu_Jellyhunter.class);
            startActivity(i);
            finish();
        });
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