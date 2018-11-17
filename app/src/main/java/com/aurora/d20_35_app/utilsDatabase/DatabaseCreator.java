package com.aurora.d20_35_app.utilsDatabase;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import androidx.room.Room;
import lombok.NonNull;

import static com.aurora.d20_35_app.utilsDatabase.DatabaseHolder.MIGRATION_1_2;
import static com.aurora.d20_35_app.utilsDatabase.DatabaseManager.RULESDB;
import static com.aurora.d20_35_app.utilsDatabase.DatabaseManager.USERDB;


public class DatabaseCreator {

    public static void createSpecificDatabase(@NonNull Context context, @NonNull String path, String databaseName) {
        switch (databaseName) {
            case USERDB:
                createUserDatabase(context, path);
                break;
            case RULESDB:
                createRulesDatabase(context, path);
                break;
            default:
                break;
        }
    }

    private static void createUserDatabase(@NonNull Context context, @NonNull String path) {
        createDatabase(context, USERDB, path);
    }

    private static void createRulesDatabase(@NonNull Context context, @NonNull String path) {
        createDatabase(context, RULESDB, path);
    }

    private static void createDatabase(@NonNull Context context, @NonNull String databaseName, @NonNull String path) {
        DbInitializer createDB = new DbInitializer(context, path, databaseName + ".db");
        try {
            InputStream sql = context.getAssets().open(chooseSQL(databaseName));
            createDB.setIs(sql);
            createDB.getWritableDatabase();

            sql.close();
            createDB.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatabaseHolder databaseHolder = Room.databaseBuilder(context, DatabaseHolder.class, databaseName + ".db").addMigrations(MIGRATION_1_2).build();
        System.out.println(databaseHolder.racesDAO().getRaces());
        databaseHolder.close();
    }

    private static String chooseSQL(String databaseName) {
        if (databaseName.equals(RULESDB) || databaseName.equals(RULESDB + ".db")) {
            Log.i("Database file:", "creating from: Rules.db.sql");
            return "Database/Rules.db.sql";
        } else {
            Log.i("Database file:", "creating from: Sample.db.sql");
            return "Database/Sample.db.sql";
        }
    }

}

