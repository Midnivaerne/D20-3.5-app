package com.aurora.d20_35_app.utils;

import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NonNull;

import static com.aurora.d20_35_app.activities.MainActivity.path;

public class DatabaseManager {

    /**
     * An array of sample (dummy) items.
     */
    public static List<ADatabase> databasesList = new ArrayList<ADatabase>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, ADatabase> databasesMap = new HashMap<String, ADatabase>();

    public static void initialDatabaseSetup() {
        Log.i("Database directory:", "checking if directory exist...");
        if (!new File(path).exists()) {
            Log.i("Database directory:", "directory doesn't exist, creating...");
            new File(String.valueOf(path)).mkdirs();
            Log.i("Database directory:", "created directory:" + path);
        } else {
            Log.i("Database directory:", "directory already exist.");
        }

        Log.i("Database file:", "checking if file exist...");
        if (!new File(path + "userDB.db").exists()) {
            Log.i("Database file:", "file doesn't exist, creating...");
            DatabaseCreator.createUserDatabase(path);
            Log.i("Database file:", "created file: userDB.db");
        } else {
            Log.i("Database file:", "file already exist.");
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
            addItem(loadDatabaseDetails(i+1, nameShortened));
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

    public static class ADatabase {
        public final String id;
        public final String content;
        public final String details;

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