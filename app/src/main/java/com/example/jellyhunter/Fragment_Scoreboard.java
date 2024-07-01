package com.example.jellyhunter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.example.jellyhunter.interfaces.LocationCallback;
import com.example.jellyhunter.utilities.MSP;
import com.example.jellyhunter.utilities.UserStats;

public class Fragment_Scoreboard extends Fragment {
    private final LocationCallback locationCallback;
    private LinearLayoutCompat jellyhunter_LAY_score0;
    private LinearLayoutCompat jellyhunter_LAY_score1;
    private LinearLayoutCompat jellyhunter_LAY_score2;
    private LinearLayoutCompat jellyhunter_LAY_score3;
    private LinearLayoutCompat jellyhunter_LAY_score4;
    private LinearLayoutCompat jellyhunter_LAY_score5;
    private LinearLayoutCompat jellyhunter_LAY_score6;
    private LinearLayoutCompat jellyhunter_LAY_score7;
    private LinearLayoutCompat jellyhunter_LAY_score8;
    private LinearLayoutCompat jellyhunter_LAY_score9;

    private AppCompatTextView jellyhunter_TXT_jelly_score0;
    private AppCompatTextView jellyhunter_TXT_jelly_score1;
    private AppCompatTextView jellyhunter_TXT_jelly_score2;
    private AppCompatTextView jellyhunter_TXT_jelly_score3;
    private AppCompatTextView jellyhunter_TXT_jelly_score4;
    private AppCompatTextView jellyhunter_TXT_jelly_score5;
    private AppCompatTextView jellyhunter_TXT_jelly_score6;
    private AppCompatTextView jellyhunter_TXT_jelly_score7;
    private AppCompatTextView jellyhunter_TXT_jelly_score8;
    private AppCompatTextView jellyhunter_TXT_jelly_score9;

    private AppCompatTextView jellyhunter_TXT_meters_score0;
    private AppCompatTextView jellyhunter_TXT_meters_score1;
    private AppCompatTextView jellyhunter_TXT_meters_score2;
    private AppCompatTextView jellyhunter_TXT_meters_score3;
    private AppCompatTextView jellyhunter_TXT_meters_score4;
    private AppCompatTextView jellyhunter_TXT_meters_score5;
    private AppCompatTextView jellyhunter_TXT_meters_score6;
    private AppCompatTextView jellyhunter_TXT_meters_score7;
    private AppCompatTextView jellyhunter_TXT_meters_score8;
    private AppCompatTextView jellyhunter_TXT_meters_score9;

    public Fragment_Scoreboard(LocationCallback locationCallback) {
        this.locationCallback = locationCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        findViews(view);
        setListeners();
        setScore();

        return view;
    }

    private void setScore() {
        MSP msp = MSP.getInstance();

        jellyhunter_TXT_jelly_score0.setText(""+new UserStats(msp.readString("STATS0","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score1.setText(""+new UserStats(msp.readString("STATS1","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score2.setText(""+new UserStats(msp.readString("STATS2","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score3.setText(""+new UserStats(msp.readString("STATS3","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score4.setText(""+new UserStats(msp.readString("STATS4","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score5.setText(""+new UserStats(msp.readString("STATS5","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score6.setText(""+new UserStats(msp.readString("STATS6","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score7.setText(""+new UserStats(msp.readString("STATS7","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score8.setText(""+new UserStats(msp.readString("STATS8","0/0/0/0")).getJelly_score());
        jellyhunter_TXT_jelly_score9.setText(""+new UserStats(msp.readString("STATS9","0/0/0/0")).getJelly_score());

        jellyhunter_TXT_meters_score0.setText(""+new UserStats(msp.readString("STATS0","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score1.setText(""+new UserStats(msp.readString("STATS1","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score2.setText(""+new UserStats(msp.readString("STATS2","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score3.setText(""+new UserStats(msp.readString("STATS3","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score4.setText(""+new UserStats(msp.readString("STATS4","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score5.setText(""+new UserStats(msp.readString("STATS5","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score6.setText(""+new UserStats(msp.readString("STATS6","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score7.setText(""+new UserStats(msp.readString("STATS7","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score8.setText(""+new UserStats(msp.readString("STATS8","0/0/0/0")).getMeter_score());
        jellyhunter_TXT_meters_score9.setText(""+new UserStats(msp.readString("STATS9","0/0/0/0")).getMeter_score());
    }

    private void findViews(View view) {
        jellyhunter_TXT_jelly_score0 = view.findViewById(R.id.jellyhunter_TXT_jelly_score0);
        jellyhunter_TXT_jelly_score1 = view.findViewById(R.id.jellyhunter_TXT_jelly_score1);
        jellyhunter_TXT_jelly_score2 = view.findViewById(R.id.jellyhunter_TXT_jelly_score2);
        jellyhunter_TXT_jelly_score3 = view.findViewById(R.id.jellyhunter_TXT_jelly_score3);
        jellyhunter_TXT_jelly_score4 = view.findViewById(R.id.jellyhunter_TXT_jelly_score4);
        jellyhunter_TXT_jelly_score5 = view.findViewById(R.id.jellyhunter_TXT_jelly_score5);
        jellyhunter_TXT_jelly_score6 = view.findViewById(R.id.jellyhunter_TXT_jelly_score6);
        jellyhunter_TXT_jelly_score7 = view.findViewById(R.id.jellyhunter_TXT_jelly_score7);
        jellyhunter_TXT_jelly_score8 = view.findViewById(R.id.jellyhunter_TXT_jelly_score8);
        jellyhunter_TXT_jelly_score9 = view.findViewById(R.id.jellyhunter_TXT_jelly_score9);

        jellyhunter_TXT_meters_score0 = view.findViewById(R.id.jellyhunter_TXT_meters_score0);
        jellyhunter_TXT_meters_score1 = view.findViewById(R.id.jellyhunter_TXT_meters_score1);
        jellyhunter_TXT_meters_score2 = view.findViewById(R.id.jellyhunter_TXT_meters_score2);
        jellyhunter_TXT_meters_score3 = view.findViewById(R.id.jellyhunter_TXT_meters_score3);
        jellyhunter_TXT_meters_score4 = view.findViewById(R.id.jellyhunter_TXT_meters_score4);
        jellyhunter_TXT_meters_score5 = view.findViewById(R.id.jellyhunter_TXT_meters_score5);
        jellyhunter_TXT_meters_score6 = view.findViewById(R.id.jellyhunter_TXT_meters_score6);
        jellyhunter_TXT_meters_score7 = view.findViewById(R.id.jellyhunter_TXT_meters_score7);
        jellyhunter_TXT_meters_score8 = view.findViewById(R.id.jellyhunter_TXT_meters_score8);
        jellyhunter_TXT_meters_score9 = view.findViewById(R.id.jellyhunter_TXT_meters_score9);

        jellyhunter_LAY_score0 = view.findViewById(R.id.jellyhunter_LAY_score0);
        jellyhunter_LAY_score1 = view.findViewById(R.id.jellyhunter_LAY_score1);
        jellyhunter_LAY_score2 = view.findViewById(R.id.jellyhunter_LAY_score2);
        jellyhunter_LAY_score3 = view.findViewById(R.id.jellyhunter_LAY_score3);
        jellyhunter_LAY_score4 = view.findViewById(R.id.jellyhunter_LAY_score4);
        jellyhunter_LAY_score5 = view.findViewById(R.id.jellyhunter_LAY_score5);
        jellyhunter_LAY_score6 = view.findViewById(R.id.jellyhunter_LAY_score6);
        jellyhunter_LAY_score7 = view.findViewById(R.id.jellyhunter_LAY_score7);
        jellyhunter_LAY_score8 = view.findViewById(R.id.jellyhunter_LAY_score8);
        jellyhunter_LAY_score9 = view.findViewById(R.id.jellyhunter_LAY_score9);

    }

    private void setListeners() {
        MSP msp = MSP.getInstance();
        jellyhunter_LAY_score0.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS0","0/0/0/0"))));
        jellyhunter_LAY_score1.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS1","0/0/0/0"))));
        jellyhunter_LAY_score2.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS2","0/0/0/0"))));
        jellyhunter_LAY_score3.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS3","0/0/0/0"))));
        jellyhunter_LAY_score4.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS4","0/0/0/0"))));
        jellyhunter_LAY_score5.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS5","0/0/0/0"))));
        jellyhunter_LAY_score6.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS6","0/0/0/0"))));
        jellyhunter_LAY_score7.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS7","0/0/0/0"))));
        jellyhunter_LAY_score8.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS8","0/0/0/0"))));
        jellyhunter_LAY_score9.setOnClickListener(v -> goLocation(new UserStats(msp.readString("STATS9","0/0/0/0"))));
    }
    private void goLocation(UserStats userStats) {
        double lat = userStats.getLat();
        double lng = userStats.getLng();

        locationCallback.goToCoords(lat, lng);
    }
}
