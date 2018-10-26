package com.aurora.d20_35_app.models;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aurora.d20_35_app.viewModels.D2035appVM;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

import static com.aurora.d20_35_app.models.DatabaseManager.REQUEST_CODE_PERMISSION_ALL;

public class DatabaseReader {

    private SQLiteDatabase database;
    private DbInitializer openHelper;
    private String databaseName;
    private static DatabaseReader instance;
    Boolean fromExternalSource = true;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     * @param sourceDirectory
     */
    private DatabaseReader(Context context, String sourceDirectory, String databaseName) {
        if (sourceDirectory == null) {
            this.openHelper = new DbInitializer(context, databaseName);
        } else {
            this.openHelper = new DbInitializer(context, sourceDirectory, databaseName);
        }
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context         the Context
     * @param sourceDirectory optional external directory
     * @return the instance of DatabaseAccess
     */
    public static DatabaseReader getInstance(Context context, String sourceDirectory, String databaseName) {
        if (instance == null) {
            instance = new DatabaseReader(context, sourceDirectory, databaseName);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM quotes", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    /** TODO this
    private void showQuotes() {
        DatabaseReader databaseReader;
        if (fromExternalSource) {
            // Check the external database file. External database must be available for the first time deployment.
            String externalDirectory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/database";
            File dbFile = new File(externalDirectory, databaseName);
            if (!dbFile.exists()) {
                return;
            }
            // If external database is available, deploy it
            databaseReader = DatabaseReader.getInstance(D2035appVM.getActivity(), externalDirectory,databaseName);
        } else {
            // From assets
            databaseReader = DatabaseReader.getInstance(D2035appVM.getActivity(), null,databaseName);
        }

        databaseReader.open();
        List<String> quotes = databaseReader.getQuotes();
        databaseReader.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(D2035appVM.getActivity(), android.R.layout.simple_list_item_1, quotes); //probably example to delete?
        //this.listView.setAdapter(adapter); //TODO no idea
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION_ALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showQuotes();
            } else {
                Toast.makeText(D2035appVM.getActivity(), "Until you grant the permission, we cannot display the quotes", Toast.LENGTH_SHORT).show();
            }
        }

    }
    **/

}
