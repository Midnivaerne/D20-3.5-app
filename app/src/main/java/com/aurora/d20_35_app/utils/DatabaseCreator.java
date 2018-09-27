package com.aurora.d20_35_app.utils;

import java.io.IOException;
import java.io.InputStream;

import com.aurora.d20_35_app.activities.MainActivity;


public class DatabaseCreator {
    private static void createDatabase(String databaseName) {
        DbInitializer createDB = new DbInitializer(MainActivity.getContext());
        createDB.setDatabase_name(databaseName + ".db");
        createDB.setSql_create_entries(getCreationSQL());
        createDB.getWritableDatabase();
        createDB.close();
    }

    private static String getCreationSQL() {
        try {
            InputStream is = MainActivity.getContext().getAssets().open("Sample.db.sql");
            return is.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createUserDatabase() {
        createDatabase("userDB");
    }

}

