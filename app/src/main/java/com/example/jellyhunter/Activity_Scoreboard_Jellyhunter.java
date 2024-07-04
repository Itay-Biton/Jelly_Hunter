package com.example.jellyhunter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.jellyhunter.interfaces.CoordinatesCallBack;
import com.example.jellyhunter.utilities.SoundManager;

public class Activity_Scoreboard_Jellyhunter extends AppCompatActivity {

    private AppCompatImageButton jellyhunter_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_jellyhunter);

        SoundManager.bubble_transition();
        initFragments();
        findViews();
    }

    private void initFragments() {
        Fragment_Map fragmentMap = new Fragment_Map();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.jellyhunter_LAY_map, fragmentMap)
                .commit();
        CoordinatesCallBack coordinatesCallBack = new CoordinatesCallBack() {
            @Override
            public void goToCoords(double lat, double lng) {
                fragmentMap.goToCoords(lat, lng);
            }
        };
        Fragment_Scoreboard fragmentScoreboard = new Fragment_Scoreboard(coordinatesCallBack);
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

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.stop();
    }
}