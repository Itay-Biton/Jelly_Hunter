package com.example.jellyhunter.gameUtils;

import androidx.annotation.NonNull;

public class UserStats implements Comparable{
    private int jelly_score;
    private int meter_score;
    private double lat;
    private double lng;
    private final String delimiter = "/";

    public UserStats(int jelly_score, int meter_score, double lat, double lng) {
        this.jelly_score = jelly_score;
        this.meter_score = meter_score;
        this.lat = lat;
        this.lng = lng;
    }

    public UserStats(String allStats) {
        String[] composed = allStats.split(delimiter);
        this.jelly_score = Integer.parseInt(composed[0]);
        this.meter_score = Integer.parseInt(composed[1]);
        this.lat = Double.parseDouble(composed[2]);
        this.lng = Double.parseDouble(composed[3]);
    }

    public int getJelly_score() {
        return jelly_score;
    }
    public int getMeter_score() {
        return meter_score;
    }
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }

    @NonNull
    @Override
    public String toString() {
        return jelly_score +delimiter+ meter_score +delimiter+ lat +delimiter+ lng;
    }

    @Override
    public int compareTo(Object otherUserStats) {
        int tmp = ((UserStats) otherUserStats).getJelly_score() - jelly_score;
        if (tmp != 0)
            return tmp;
        return ((UserStats) otherUserStats).getMeter_score() - meter_score;
    }
}
