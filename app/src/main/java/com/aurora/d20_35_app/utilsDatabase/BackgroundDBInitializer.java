package com.aurora.d20_35_app.utilsDatabase;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

public class BackgroundDBInitializer implements Runnable {

    @Getter
    private Thread t;
    private final String threadName;
    private final String databaseName;
    @Setter
    private Context context;
    @Setter
    private String path;

    public BackgroundDBInitializer(String threadName) {
        this.threadName = threadName;
        this.databaseName = threadName.replace("_handler", "");
        Log.i("Thread", "creating:" + threadName);
    }

    @Override
    public void run() {
        try {
            if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
                if (threadName.endsWith("_handler")) {
                    Log.i("Database file:", "checking if file: " + databaseName + ".db exist...");
                    if (!new File(path + databaseName + ".db").exists()) {
                        Log.i("Database file:", "file: " + databaseName + ".db doesn't exist, creating...");
                        DatabaseCreator.createSpecificDatabase(context, path, databaseName);
                        Log.i("Database file:", "created file: " + databaseName + ".db");
                    } else {
                        Log.i("Database file:", "file: " + databaseName + ".db already exist.");
                    }
                }
            } else {
                Log.i("Thread", "Can't initialize database with:" + threadName + ", permissions not granted");
            }
        } finally {
            Log.i("Thread", "exiting:" + threadName);
        }
    }

    public void start() {
        Log.i("Thread", "starting:" + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
        Log.i("Thread", "started:" + threadName);
    }

}
