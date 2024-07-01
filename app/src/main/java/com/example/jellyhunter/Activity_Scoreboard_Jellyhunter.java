package com.example.jellyhunter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.jellyhunter.utilities.MSP;

public class Activity_Scoreboard_Jellyhunter extends AppCompatActivity {

    private AppCompatImageButton jellyhunter_BTN_back;
    private AppCompatTextView jellyhunter_TXT_jelly_score0;
    private AppCompatTextView jellyhunter_TXT_jelly_score1;
    private AppCompatTextView jellyhunter_TXT_jelly_score2;
    private AppCompatTextView jellyhunter_TXT_jelly_score3;
    private AppCompatTextView jellyhunter_TXT_jelly_score4;
    private AppCompatTextView jellyhunter_TXT_meters_score0;
    private AppCompatTextView jellyhunter_TXT_meters_score1;
    private AppCompatTextView jellyhunter_TXT_meters_score2;
    private AppCompatTextView jellyhunter_TXT_meters_score3;
    private AppCompatTextView jellyhunter_TXT_meters_score4;

    private MediaPlayer[] soundEffects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_jellyhunter);
        soundEffects = new MediaPlayer[] {
                MediaPlayer.create(this, R.raw.bubble_transition)
        };
        soundEffects[0].start();

        findViews();
        setScore();
    }

    private void setScore() {
        MSP msp = MSP.getInstance();

        jellyhunter_TXT_jelly_score0.setText(""+msp.readInt("JELLY0",1));
        jellyhunter_TXT_jelly_score1.setText(""+msp.readInt("JELLY1",1));
        jellyhunter_TXT_jelly_score2.setText(""+msp.readInt("JELLY2",1));
        jellyhunter_TXT_jelly_score3.setText(""+msp.readInt("JELLY3",1));
        jellyhunter_TXT_jelly_score4.setText(""+msp.readInt("JELLY4",1));

        jellyhunter_TXT_meters_score0.setText(""+msp.readInt("JELLY0",0));
        jellyhunter_TXT_meters_score1.setText(""+msp.readInt("JELLY1",0));
        jellyhunter_TXT_meters_score2.setText(""+msp.readInt("JELLY2",0));
        jellyhunter_TXT_meters_score3.setText(""+msp.readInt("JELLY3",0));
        jellyhunter_TXT_meters_score4.setText(""+msp.readInt("JELLY4",0));
    }

    private void findViews() {
        jellyhunter_TXT_jelly_score0 = findViewById(R.id.jellyhunter_TXT_jelly_score0);
        jellyhunter_TXT_jelly_score1 = findViewById(R.id.jellyhunter_TXT_jelly_score1);
        jellyhunter_TXT_jelly_score2 = findViewById(R.id.jellyhunter_TXT_jelly_score2);
        jellyhunter_TXT_jelly_score3 = findViewById(R.id.jellyhunter_TXT_jelly_score3);
        jellyhunter_TXT_jelly_score4 = findViewById(R.id.jellyhunter_TXT_jelly_score4);

        jellyhunter_TXT_meters_score0 = findViewById(R.id.jellyhunter_TXT_meters_score0);
        jellyhunter_TXT_meters_score1 = findViewById(R.id.jellyhunter_TXT_meters_score1);
        jellyhunter_TXT_meters_score2 = findViewById(R.id.jellyhunter_TXT_meters_score2);
        jellyhunter_TXT_meters_score3 = findViewById(R.id.jellyhunter_TXT_meters_score3);
        jellyhunter_TXT_meters_score4 = findViewById(R.id.jellyhunter_TXT_meters_score4);

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