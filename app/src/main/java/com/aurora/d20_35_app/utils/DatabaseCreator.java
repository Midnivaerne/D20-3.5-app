package com.aurora.d20_35_app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aurora.d20_35_app.activities.MainActivity;


public class DatabaseCreator {
    private static void createDatabase(String databaseName) {
        DbInitializer createDB = new DbInitializer(MainActivity.getContext(), databaseName + ".db");
        createDB.setSql_create_entries(getCreationSQL());
        createDB.getWritableDatabase();
        createDB.close();
    }

    private static String getCreationSQL() {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = MainActivity.getContext().getAssets().open("Database/Sample.db.sql");
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
            return buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createUserDatabase() {
        createDatabase("userDB");
    }

}

