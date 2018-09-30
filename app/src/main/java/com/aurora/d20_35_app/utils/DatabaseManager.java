package com.aurora.d20_35_app.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {

    public static void initialDatabaseSetup() {
        String externalPath = getPublicExternalStorageBaseDir(); //TODO find proper external dir!
        String externalPathSeparator = "/Android/d20appStorage/";
        String path = externalPath + externalPathSeparator + "Data/";

        Log.i("Database directory:", "checking if directory exist...");
        if (!new File(path).exists()) {
            Log.i("Database directory:", "directory doesn't exist, creating...");
            new File(path).mkdir();
            Log.i("Database directory:", "created directory:" + path);
        } else {
            Log.i("Database directory:", "directory already exist.");
        }

        Log.i("Database file:", "checking if file exist...");
        if (!new File(path + "userDB.db").exists()) {
            Log.i("Database file:", "file doesn't exist, creating...");
            DatabaseCreator.createUserDatabase();
            Log.i("Database file:", "created file: userDB.db");
        } else {
            Log.i("Database file:", "file already exist.");
        }

        //howMany(External, path) < 1
        //DatabaseManager.howMany(Internal, MainActivity.getContext().getApplicationInfo().dataDir);
        //DatabaseManager.howMany(Internal, MainActivity.getContext().getFilesDir().getPath());
        //DatabaseManager.howMany(Internal, "jdbc:sqlite:src\\D20-3.5-app\\app\\Databases");
    }

    public static int howMany(Enums.DataLocation where, String path) {
        showFiles(path);
        int databasesCount = countFilesInDir(path);
        System.out.println(where.toString() + " Databases count:" + databasesCount);
        return databasesCount;
    }


    public static void showFiles(String dir) {
        if (new File(dir).listFiles() != null) {
            File[] files = new File(dir).listFiles();
            Log.i("Dir", "path:" + dir);
            Log.i("Files", "Size: " + files.length);
            for (int i = 0; i < files.length; i++) {
                Log.i("Files", "FileName:" + files[i].getName());
            }
        } else {
            Log.i("Files", "No files:");
        }

    }

    public static int countFilesInDir(String path) {
        int count = 0;
        File file = new File(path);
        if (file.listFiles() != null) {
            count = file.listFiles().length;
        }
        return count;
    }

}

class DbInitializer extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    @Getter
    @Setter
    private String sql_create_entries;

    @Getter
    @Setter
    private String database_name;

    @Getter
    @Setter
    private String sourceDirectory;

    public DbInitializer(Context context, String database_name) {
        super(context, database_name, null, DATABASE_VERSION);
        this.database_name = database_name;
    }

    public DbInitializer(Context context, String sourceDirectory, String database_name) {
        super(context, sourceDirectory + database_name, null, DATABASE_VERSION);
        this.database_name = database_name;
    }

    public void onCreate(SQLiteDatabase db) {
        if (!database_name.equals("") && !sql_create_entries.equals("")) {
            db.execSQL(sql_create_entries);
        } else {
            System.out.println("Empty database_name or sql_create_entries");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}