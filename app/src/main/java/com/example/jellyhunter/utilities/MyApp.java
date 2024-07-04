package com.example.jellyhunter.utilities;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MSP.init(this);
        SoundManager.init(this);
    }
}
