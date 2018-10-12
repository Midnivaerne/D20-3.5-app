package com.aurora.d20_35_app.utils;

import com.aurora.d20_35_app.activities.MainActivity;

import lombok.NonNull;


public class DatabaseCreator {
    private static void createDatabase(@NonNull String databaseName,@NonNull String path) {
        DbInitializer createDB = new DbInitializer(MainActivity.getContext(), path , databaseName + ".db");
        createDB.getWritableDatabase();
        createDB.close();
    }


    public static void createUserDatabase(@NonNull String path) {
        createDatabase("userDB", path);
    }

}

