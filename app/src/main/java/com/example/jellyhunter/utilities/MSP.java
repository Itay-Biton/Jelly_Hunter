package com.example.jellyhunter.utilities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class MSP {
    private static MSP MSP;
    private final SharedPreferences prefs;

    private MSP(Context context) {
        prefs = context.getSharedPreferences("MyPreference", MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (MSP == null) {
            MSP = new MSP(context);
        }
    }

    public static MSP getInstance() {
        return MSP;
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int readInt(String key, int def) {
        return prefs.getInt(key, def);
    }

}
