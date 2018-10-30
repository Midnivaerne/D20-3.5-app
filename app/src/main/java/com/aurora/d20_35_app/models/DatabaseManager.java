package com.aurora.d20_35_app.models;

import android.Manifest;
import android.content.Context;
import android.util.Log;

import com.aurora.d20_35_app.utils.Enums;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
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

    public static List<ADatabase> databasesList = new ArrayList<ADatabase>();
    public static Map<String, ADatabase> databasesMap = new HashMap<String, ADatabase>();

    public static void initialDatabaseSetup(Context context, String databaseName) {
        Log.i("Database directory:", "checking if directory: " + path + " exist...");
        if (!new File(path).exists()) {
            Log.i("Database directory:", "directory " + path + " doesn't exist, creating...");
            new File(String.valueOf(path)).mkdirs();
            Log.i("Database directory:", "created directory:" + path);
        } else {
            Log.i("Database directory:", "directory " + path + " already exist.");
        }

        Log.i("Database file:", "checking if file: " + databaseName + " exist...");
        if (!new File(path + databaseName).exists()) {
            Log.i("Database file:", "file: " + databaseName + " doesn't exist, creating...");
            DatabaseCreator.createSpecificDatabase(context, path, databaseName);
            Log.i("Database file:", "created file: " + databaseName);
        } else {
            Log.i("Database file:", "file: " + databaseName + " already exist.");
        }
    }

    public static int howMany(@NonNull Enums.DataLocation where, @NonNull String path) {
        showFiles(path);
        int databasesCount = countFilesInDir(path);
        Log.i("Database file:", where.toString() + "databases count:" + databasesCount);
        return databasesCount;
    }


    public static void showFiles(@NonNull String dir) {
        if (new File(dir).listFiles() != null) {
            File[] files = new File(dir).listFiles(fileExtensionFilter("db"));
            Log.i("Directory", "path:" + dir);
            Log.i("Files", "Size: " + files.length);
            for (int i = 0; i < files.length; i++) {
                Log.i("Files", "FileName:" + files[i].getName());
            }
        } else {
            Log.i("Files", "No files:");
        }

    }

    public static FilenameFilter fileExtensionFilter(final String extension) {
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf('.') > 0) {
                    int lastIndex = name.lastIndexOf('.');
                    String str = name.substring(lastIndex);
                    if (str.equals("." + extension)) {
                        return true;
                    }
                }
                return false;
            }
        };
        return fileNameFilter;
    }

    public static int countFilesInDir(@NonNull String path) {
        int count = 0;
        File file = new File(path);
        if (file.listFiles() != null) {
            count = file.listFiles(fileExtensionFilter("db")).length;
        }
        return count;
    }

    public static void loadDatabasesList() {
        databasesList.clear();
        databasesMap.clear();
        for (int i = 0; i < countFilesInDir(path); i++) {
            File file = new File(path);
            String name = file.listFiles(fileExtensionFilter("db"))[i].getName();
            String nameShortened = name.split("\\.")[0];
            addItem(loadDatabaseDetails(i + 1, nameShortened));
        }
    }

    private static void addItem(ADatabase item) {
        databasesList.add(item);
        databasesMap.put(item.id, item);
    }

    private static ADatabase loadDatabaseDetails(int position, String name) {
        return new ADatabase(String.valueOf(position), name, makeDetails(name));
    }

    private static String makeDetails(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about: ").append(name);
        builder.append("\n" + name + " contains ...");
        return builder.toString();
    }

    @Data
    public static class ADatabase {
        private final String id;
        private final String content;
        private final String details;

        public ADatabase(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }

}