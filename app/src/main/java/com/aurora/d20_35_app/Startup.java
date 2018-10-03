package com.aurora.d20_35_app;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.aurora.d20_35_app.utils.BackgroundUserDBInitializer;

public class Startup extends Application {

    public static SharedPreferences sharedpreferences;
    public static Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Startup ", " Initializing startup");

        sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = sharedpreferences.edit();
        editor.putBoolean("firstTimeOpened", true);
        editor.apply();

        new BackgroundUserDBInitializer("userDB_handler").start();

        Log.i("Startup ", " End of startup");
    }
}
