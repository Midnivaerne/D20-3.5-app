package com.aurora.d20_35_app.utils;

import android.util.Log;

import static com.aurora.d20_35_app.utils.DatabaseManager.initialDatabaseSetup;

public class BackgroundUserDBInitializer implements Runnable {

    private Thread t;
    private final String threadName;

    public BackgroundUserDBInitializer(String threadName) {
        this.threadName = threadName;
        Log.i("Thread", "creating:" + threadName);
    }

    @Override
    public void run() {
        initialDatabaseSetup();
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
