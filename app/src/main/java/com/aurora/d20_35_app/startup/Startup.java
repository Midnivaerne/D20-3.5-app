package com.aurora.d20_35_app.startup;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.aurora.d20_35_app.views.D2035appActivity;

import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

@Singleton
public class Startup extends Application implements HasActivityInjector {

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
        Log.i("Startup ", " End of startup");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return new D2035appActivity();
    }
}