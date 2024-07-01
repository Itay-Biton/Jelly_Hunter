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

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String readString(String key, String def) {
        return prefs.getString(key, def);
    }

}
