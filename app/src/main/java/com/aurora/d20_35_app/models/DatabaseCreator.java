package com.aurora.d20_35_app.models;

import android.content.Context;

import lombok.NonNull;


public class DatabaseCreator {
    private static void createDatabase(@NonNull Context context, @NonNull String databaseName, @NonNull String path) {
        DbInitializer createDB = new DbInitializer(context, path, databaseName + ".db");
        createDB.getWritableDatabase();
        createDB.close();
    }


    private static void createUserDatabase(@NonNull Context context, @NonNull String path) {
        createDatabase(context, "userDB", path);
    }

    private static void createRulesDatabase(@NonNull Context context, @NonNull String path) {
        createDatabase(context, "standardRules", path);
    }

    public static void createSpecificDatabase(@NonNull Context context, @NonNull String path, String databaseName) {
        switch (databaseName) {
            case "userDB":
                createUserDatabase(context, path);
                break;
            case "standardRules":
                createRulesDatabase(context, path);
                break;
            default:
                break;
        }
    }

}

