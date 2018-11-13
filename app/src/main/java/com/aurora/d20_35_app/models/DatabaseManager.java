package com.aurora.d20_35_app.models;

import android.Manifest;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.aurora.d20_35_app.utils.Enums;
import com.aurora.d20_35_app.utils.PermissionHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
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

    public static final String USERDB = "userDB";
    public static final String RULESDB = "rulesDB";

    public static List<ADatabase> databasesList = new ArrayList<ADatabase>();
    public static Map<String, ADatabase> databasesMap = new HashMap<String, ADatabase>();

    public static void initialDatabasesResolver(Activity activity) {
        PermissionHandler.reloadPermissions(activity);
        initialPathSetup();
        dbThreadSetup(activity, Enums.DatabaseHandlers.userDB.toString());
        dbThreadSetup(activity, Enums.DatabaseHandlers.rulesDB.toString());
        insertStandardRulesFromSQL(activity);//todo check if rules already present
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

    private static void dbThreadSetup(Activity activity, String name) {
        BackgroundDBInitializer backgroundInitializer = new BackgroundDBInitializer(name);
        backgroundInitializer.setContext(activity);
        backgroundInitializer.setPath(path);
        backgroundInitializer.start();

        try {
            backgroundInitializer.getT().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void insertStandardRulesFromSQL(Activity activity) {
        try {
            Log.i("Database file:", "inserting standard rules");

            Log.i("Database file:", "opening file: " + path + RULESDB + ".db");
            SQLiteDatabase database = SQLiteDatabase.openDatabase(path + RULESDB + ".db", null, SQLiteDatabase.OPEN_READWRITE);

            Log.i("Database file:", "opening inputStream and executing SQL: StandardRules.sql");
            InputStream inputStream = activity.getAssets().open("Database/StandardRules.sql");
            database.execSQL(sqlFileToString(inputStream));

            Log.i("Database file:", "closing inputStream, transaction and database ...");
            inputStream.close();
            database.endTransaction();
            database.close();
            Log.i("Database file:", "inputStream, transaction and database closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadDatabasesList() {
        Log.i("Database file:", "Loading databases list");
        databasesList.clear();
        databasesMap.clear();
        for (int i = 0; i < countFilesInDir(path); i++) {
            File file = new File(path);
            String name = file.listFiles(fileExtensionFilter("db"))[i].getName();
            String nameShortened = name.split("\\.")[0];
            addItem(loadDatabaseDetails(i + 1, nameShortened));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void hideRulesDatabase() {
        for (Object obj : databasesList) {
            if (obj.toString().equals(RULESDB)) {
                int index = databasesList.indexOf(obj);
                ADatabase item = databasesList.get(index);
                removeItem(item);
                break;
            }
        }
    }

    private static void addItem(ADatabase item) {
        databasesList.add(item);
        databasesMap.put(item.id, item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void removeItem(ADatabase item) {
        databasesList.remove(item);
        databasesMap.remove(item.id, item);
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

    public static String sqlFileToString(InputStream is) {
        StringBuilder buf = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
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