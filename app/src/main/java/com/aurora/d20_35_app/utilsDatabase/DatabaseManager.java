package com.aurora.d20_35_app.utilsDatabase;

import android.Manifest;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.aurora.d20_35_app.utils.PermissionHandler;

import java.io.File;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {

    public static final String LOG_TAG_EXTERNAL_STORAGE = "EXTERNAL_STORAGE";
    public static final int REQUEST_CODE_PERMISSION_ALL = 1;
    @Getter
    @Setter
    private static int readExternalStoragePermission = -1;// Read external storage permission cache.
    @Getter
    @Setter
    private static int writeExternalStoragePermission = -1;// Write external storage permission cache.
    @Getter
    private static String[] permissionTypeString = {"Read", "Write"};
    @Getter
    private static String[] permissionTypeStringManifest = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static String externalPathSeparator = "/Android/data/com.aurora.d20_3.5_app/";
    public static String path = getPublicExternalStorageBaseDir() + externalPathSeparator + "Data/";

    public static void initialDatabasesResolver(Activity activity) {
        PermissionHandler.reloadPermissions(activity);
        initialPathSetup();
        populateAsync(DatabaseHolder.getDatabaseHolder(activity.getApplicationContext()));
    }

    public static void initialPathSetup() {
        Log.i("Database directory:", "checking if directory: " + path + " exist...");
        if (!new File(path).exists()) {
            Log.i("Database directory:", "directory " + path + " doesn't exist, creating...");
            Boolean fileCreated = new File(String.valueOf(path)).mkdirs();
            if (fileCreated) {
                Log.i("Database directory:", "created directory:" + path);
            } else {
                Log.e("Database directory:", "failed to create directory:" + path);
            }
        } else {
            Log.i("Database directory:", "directory " + path + " already exist.");
        }
    }

    public static void populateAsync(@NonNull final DatabaseHolder databaseHolder) {
        PopulateDbAsync task = new PopulateDbAsync(databaseHolder);
        task.execute();
    }

    public static void closeDatabase(DatabaseHolder databaseHolder) {
        databaseHolder.close();
        DatabaseHolder.destroyInstance();
    }

    public static void loadStandardRules(DatabaseHolder databaseHolder) {
        //todo check first if rules present?
    }

    private static void generateDataLists(DatabaseHolder databaseHolder) {
        List<String> databasesList = databaseHolder.getDatabasesList();
        databasesList.addAll(databaseHolder.armourDAO().getSources());
        databasesList.addAll(databaseHolder.classesDAO().getSources());
        databasesList.addAll(databaseHolder.equipmentDAO().getSources());
        databasesList.addAll(databaseHolder.featsDAO().getSources());
        databasesList.addAll(databaseHolder.heroDAO().getSources());
        databasesList.addAll(databaseHolder.monstersDAO().getSources());
        databasesList.addAll(databaseHolder.racesDAO().getSources());
        databasesList.addAll(databaseHolder.raceTemplatesDAO().getSources());
        databasesList.addAll(databaseHolder.skillsDAO().getSources());
        databasesList.addAll(databaseHolder.spellsDAO().getSources());
        databasesList.addAll(databaseHolder.weaponsDAO().getSources());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DatabaseHolder databaseHolder;

        PopulateDbAsync(DatabaseHolder databaseHolder) {
            this.databaseHolder = databaseHolder;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            loadStandardRules(databaseHolder);
            generateDataLists(databaseHolder);
            return null;
        }
    }

}