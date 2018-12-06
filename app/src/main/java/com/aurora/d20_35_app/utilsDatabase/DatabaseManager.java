package com.aurora.d20_35_app.utilsDatabase;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lombok.NonNull;

import static com.aurora.d20_35_app.utils.ExternalStorageHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {

    public static String externalPathSeparator = "/Android/data/com.aurora.d20_3.5_app/";
    public static String path = getPublicExternalStorageBaseDir() + externalPathSeparator + "Data/";

    public static void initialDatabasesResolver(Activity activity) {
        initialPathSetup();
        copyFile("test.xml", activity); //todo test - refactor or delete
        populateAsync(DatabaseHolder.getDatabaseHolder(activity.getApplicationContext()));
    }

    public static void copyFile(String fileName, Activity activity) {
        try {
            InputStream in = activity.getAssets().open(fileName);
            OutputStream out = new FileOutputStream(new File(path, fileName));
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        loadDataFromFile(databaseHolder); //todo test to delete
        databaseHolder.racesDAO().insertAll(databaseHolder.RACES_LIST); //todo test to delete
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
        databasesList.addAll(databaseHolder.translationsDAO().getSources());
    }

    private static void loadDataFromFile(DatabaseHolder databaseHolder) {
        try {
            //String filename = "standardRulesData.xml";
            String filename = "test.xml";
            File inputFile = new File(path + /*File.separator +*/ filename);
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlHandler xmlHandler = new XmlHandler(databaseHolder);
            saxParser.parse(inputFile, xmlHandler);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static void writeDataToFile(DatabaseHolder databaseHolder) {
        String filename = "testFile.xml";
        File outputFile = new File(path + File.separator + filename);
        XmlSerializer xmlSerializer = Xml.newSerializer(); //todo continue
        //out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
        //out.writeObject(myPersonObject);

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