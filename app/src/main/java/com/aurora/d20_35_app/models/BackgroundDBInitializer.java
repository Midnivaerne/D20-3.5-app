package com.aurora.d20_35_app.models;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import lombok.Setter;

import static com.aurora.d20_35_app.models.DatabaseManager.initialDatabaseSetup;

public class BackgroundDBInitializer implements Runnable {

    private Thread t;
    private final String threadName;
    @Setter
    private Context context;

    public BackgroundDBInitializer(String threadName) {
        this.threadName = threadName;
        Log.i("Thread", "creating:" + threadName);
    }

    @Override
    public void run() {
        if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            if (threadName.endsWith("_handler")) {
                initialDatabaseSetup(context, threadName.replace("_handler", ""));
            }
        } else {
            Log.i("Thread", "Can't initialize database with:" + threadName + ", permissions not granted");
        }

    }

    public void start() {
        Log.i("Thread", "starting:" + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
        Log.i("Thread", "exiting:" + threadName);
    }

}
