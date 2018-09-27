package com.aurora.d20_35_app.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {


    public void initialDBsetup() {
        if (howManyExternal() < 1) {
            DatabaseCreator.createUserDatabase();
        }
    }

    public static int howMany() {
        System.out.println(new File("jdbc:sqlite:src\\D20-3.5-app\\app\\Databases"));
        int databasesCount = new File("jdbc:sqlite:src\\D20-3.5-app\\app\\Databases").listFiles().length;
        System.out.println(databasesCount);
        return databasesCount;
    }

    public  static int howManyExternal() {
        String externalPath = getPublicExternalStorageBaseDir();
        String externalPathSeparator = "\\d20appStorage\\";
        int databasesCount = new File(externalPath + externalPathSeparator).listFiles().length;
        System.out.println(databasesCount);
        return databasesCount;
    }

}

class DbInitializer extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    @Getter
    @Setter
    private static String sql_create_entries = "";

    @Getter
    @Setter
    private static String database_name = "";

    @Getter
    @Setter
    private static String sourceDirectory = "";

    public DbInitializer(Context context) {
        super(context, database_name, null, DATABASE_VERSION);
    }

    public DbInitializer(Context context, String sourceDirectory) {
        super(context, sourceDirectory + database_name, null, DATABASE_VERSION);
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
