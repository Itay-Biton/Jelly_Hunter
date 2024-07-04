package com.example.jellyhunter.utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.jellyhunter.R;

public class SoundManager {
    static MediaPlayer[] mediaPlayer;
    static MediaPlayer[] bgPlayer;

    static void init(Context context) {
        mediaPlayer = new MediaPlayer[] {
                MediaPlayer.create(context, R.raw.spongebob_walk1),
                MediaPlayer.create(context, R.raw.spongebob_walk2),
                MediaPlayer.create(context, R.raw.spongebob_ouch),
                MediaPlayer.create(context, R.raw.ding),
                MediaPlayer.create(context, R.raw.try_again),
                MediaPlayer.create(context, R.raw.im_ready),
                MediaPlayer.create(context, R.raw.bubble_transition)
        };
        bgPlayer = new MediaPlayer[] {
                MediaPlayer.create(context, R.raw.bg_music_game)
        };
        bgPlayer[0].setLooping(true);

    }

    public static void walk1() {
        mediaPlayer[0].start();
    }
    public static void walk2() {
        mediaPlayer[1].start();
    }
    public static void ouch() {
        stopEffects();
        mediaPlayer[2].start();
    }
    public static void ding() {
        stopEffects();
        mediaPlayer[3].start();
    }
    public static void try_again() {
        stopEffects();
        mediaPlayer[4].start();
    }
    public static void im_ready() {
        stopEffects();
        mediaPlayer[5].start();
    }
    public static void bubble_transition() {
        stopEffects();
        mediaPlayer[6].start();
    }

    public static void gameMusic() {
        stopMusic();
        bgPlayer[0].start();
    }

    private static void stopEffects() {
        for (MediaPlayer soundEffect : mediaPlayer)
            if (soundEffect.isPlaying()) {
                soundEffect.pause();
                soundEffect.seekTo(0);
            }
    }
    private static void stopMusic() {
        for (MediaPlayer music : bgPlayer)
            if (music.isPlaying()) {
                music.pause();
                music.seekTo(0);
            }
    }
    public static void stop() {
        stopEffects();
        stopMusic();
    }
}
