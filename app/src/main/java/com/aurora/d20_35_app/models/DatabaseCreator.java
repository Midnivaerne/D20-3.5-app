package com.aurora.d20_35_app.models;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.aurora.d20_35_app.utils.Enums;
import com.aurora.d20_35_app.utils.PermissionHandler;

import java.io.IOException;

import lombok.NonNull;

import static com.aurora.d20_35_app.models.DatabaseManager.RULESDB;
import static com.aurora.d20_35_app.models.DatabaseManager.USERDB;


public class DatabaseCreator {
    private static void createDatabase(@NonNull Context context, @NonNull String databaseName, @NonNull String path) {
        DbInitializer createDB = new DbInitializer(context, path, databaseName + ".db");
        try {
            createDB.setIs(context.getAssets().open(chooseSQL(databaseName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createDB.getWritableDatabase();
        createDB.close();
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


    private static void createUserDatabase(@NonNull Context context, @NonNull String path) {
        createDatabase(context, USERDB, path);
    }

    private static void createRulesDatabase(@NonNull Context context, @NonNull String path) {
        createDatabase(context, RULESDB, path);
    }

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

    public static void initialDatabasesResolver(Activity activity) {
        PermissionHandler.reloadPermissions(activity);
        generateUserDB(activity);
        RulesManager.generateStandardRules(activity);
    }

    private static void generateUserDB(Activity activity) {
        BackgroundDBInitializer backgroundDBInitializer = new BackgroundDBInitializer(Enums.DatabaseHandlers.userDB.toString());
        backgroundDBInitializer.setContext(activity);
        backgroundDBInitializer.start();
    }

}

