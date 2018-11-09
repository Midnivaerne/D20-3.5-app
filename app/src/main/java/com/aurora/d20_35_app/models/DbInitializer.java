package com.aurora.d20_35_app.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class DbInitializer extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    @Getter
    @Setter
    private String database_name;
    private Context context;
    @Setter
    private InputStream is;

    public DbInitializer(Context context, @NonNull String database_name) {
        super(context, database_name, null, DATABASE_VERSION);
        this.database_name = database_name;
        this.context = context;
    }

    public DbInitializer(Context context, @NonNull String sourceDirectory, @NonNull String database_name) {
        super(context, sourceDirectory + database_name, null, DATABASE_VERSION);
        this.database_name = database_name;
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        if (!database_name.equals("")) {
            Log.i("Database file:", "creating tables for: " + database_name);
            try {
                StringBuilder buf = new StringBuilder();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String str;

                while ((str = in.readLine()) != null) {
                    buf.append(str);
                }

                in.close();
                db.execSQL(buf.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Empty database_name");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
