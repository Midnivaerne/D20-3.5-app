package com.aurora.d20_35_app.utils;

import android.util.Log;
import java.io.File;

import lombok.NonNull;

import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {

    public static String externalPathSeparator = "/Android/data/com.aurora.d20_3.5_app/";

    public static void initialDatabaseSetup() {
        String externalPath = getPublicExternalStorageBaseDir();
        String path = externalPath + externalPathSeparator + "Data/";

        Log.i("Database directory:", "checking if directory exist...");
        if (!new File(path).exists()) {
            Log.i("Database directory:", "directory doesn't exist, creating...");
            new File(String.valueOf(path)).mkdirs();
            Log.i("Database directory:", "created directory:" + path);
        } else {
            Log.i("Database directory:", "directory already exist.");
        }

        Log.i("Database file:", "checking if file exist...");
        if (!new File(path + "userDB.db").exists()) {
            Log.i("Database file:", "file doesn't exist, creating...");
            DatabaseCreator.createUserDatabase(path);
            Log.i("Database file:", "created file: userDB.db");
        } else {
            Log.i("Database file:", "file already exist.");
        }
    }

    public static int howMany(@NonNull Enums.DataLocation where, @NonNull String path) {
        showFiles(path);
        int databasesCount = countFilesInDir(path);
        System.out.println(where.toString() + " Databases count:" + databasesCount);
        return databasesCount;
    }


    public static void showFiles(@NonNull String dir) {
        if (new File(dir).listFiles() != null) {
            File[] files = new File(dir).listFiles();
            Log.i("Directory", "path:" + dir);
            Log.i("Files", "Size: " + files.length);
            for (int i = 0; i < files.length; i++) {
                Log.i("Files", "FileName:" + files[i].getName());
            }
        } else {
            Log.i("Files", "No files:");
        }

    }

    public static int countFilesInDir(@NonNull String path) {
        int count = 0;
        File file = new File(path);
        if (file.listFiles() != null) {
            count = file.listFiles().length;
        }
        return count;
    }

}