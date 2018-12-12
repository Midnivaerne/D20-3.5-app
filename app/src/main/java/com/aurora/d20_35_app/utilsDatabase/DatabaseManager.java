package com.aurora.d20_35_app.utilsDatabase;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.aurora.d20_35_app.enums.DatabaseUsage;
import com.aurora.d20_35_app.enums.ItemType;
import com.aurora.d20_35_app.models.Races;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lombok.NonNull;

import static com.aurora.d20_35_app.utils.ExternalStorageHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {

    private static String externalPathSeparator = "/Android/data/com.aurora.d20_3.5_app/";
    public static String path = getPublicExternalStorageBaseDir() + externalPathSeparator + "Data/";

    public static void initialDatabasesResolver(Activity activity) {
        initialPathSetup();
        SharedPreferences sharedpreferences = activity.getApplicationContext().getSharedPreferences("AppPref", 0);
        if (!sharedpreferences.getBoolean("firstTimeOpened", false)) {
            Log.i("Database", "First opening");
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("firstTimeOpened", true);
            editor.apply();
            copyFile("test.xml", activity); //todo test - refactor or delete
            populateAsync(DatabaseHolder.getDatabaseHolder(activity.getApplicationContext()), DatabaseUsage.Startup);
        } else {
            Log.i("Database", "Another opening");
            populateAsync(DatabaseHolder.getDatabaseHolder(activity.getApplicationContext()), DatabaseUsage.ReloadFromDatabase); // todo refactor/delete
        }
    }

    private static void copyFile(String fileName, Activity activity) {
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

    private static void initialPathSetup() {
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

    private static void populateAsync(@NonNull final DatabaseHolder databaseHolder, DatabaseUsage usage) {
        PopulateDbAsync task = new PopulateDbAsync(databaseHolder, usage);
        task.execute();
    }

    private static void closeDatabase(DatabaseHolder databaseHolder) {
        databaseHolder.close();
        DatabaseHolder.destroyInstance();
    }

    private static void loadRulesFromFileToHolderAndDatabase(DatabaseHolder databaseHolder, String filename, boolean reload, ItemType itemType) {
        if (reload) {
            itemType.deleteAll(databaseHolder);
        }
        loadDataFromFileToHolder(databaseHolder, filename);
        loadDataFromHolderToDatabase(databaseHolder, reload, itemType);
    }

    private static void loadDataFromDatabaseToHolder(DatabaseHolder databaseHolder, boolean reload, ItemType itemType) {
        if (reload) {
            itemType.deleteAllFromHolder(databaseHolder);
        }
        //todo check first if rules present?
        itemType.fromDatabaseToHolder(databaseHolder);
    }

    private static void loadDataFromHolderToDatabase(DatabaseHolder databaseHolder, boolean reload, ItemType itemType) {
        if (reload) {
            itemType.deleteAllFromDatabase(databaseHolder);
        }
        //todo check first if rules present?
        itemType.fromHolderToDatabase(databaseHolder);
    }

    @SuppressWarnings("unchecked")
    private static void generateDataLists(DatabaseHolder databaseHolder) {
        List<String> tmp = new ArrayList<>();
        for (ItemType type : ItemType.values()) {
            tmp.addAll(type.getDAO(databaseHolder).getSources());//this is unsafe/unchecked
        }
        /*
        tmp.addAll(databaseHolder.armourDAO().getSources());
        tmp.addAll(databaseHolder.classesDAO().getSources());
        tmp.addAll(databaseHolder.equipmentDAO().getSources());
        tmp.addAll(databaseHolder.featsDAO().getSources());
        tmp.addAll(databaseHolder.heroDAO().getSources());
        tmp.addAll(databaseHolder.monstersDAO().getSources());
        tmp.addAll(databaseHolder.racesDAO().getSources());
        tmp.addAll(databaseHolder.raceTemplatesDAO().getSources());
        tmp.addAll(databaseHolder.skillsDAO().getSources());
        tmp.addAll(databaseHolder.spellsDAO().getSources());
        tmp.addAll(databaseHolder.weaponsDAO().getSources());
        tmp.addAll(databaseHolder.translationsDAO().getSources());
        */
        databaseHolder.getDatabasesList().clear();
        databaseHolder.getDatabasesList().addAll(new ArrayList<>(new HashSet<>(tmp)));
    }

    private static void loadDataFromFileToHolder(DatabaseHolder databaseHolder, String filename) {
        try {
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
        private final DatabaseUsage usage;

        PopulateDbAsync(DatabaseHolder databaseHolder, DatabaseUsage usage) {
            this.databaseHolder = databaseHolder;
            this.usage = usage;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //String filename = "standardRulesData.xml";
            String filename = "test.xml"; //todo refactor
            ItemType itemType = ItemType.Races; //todo refactor
            switch (usage) {
                case Startup:
                    loadRulesFromFileToHolderAndDatabase(databaseHolder, filename, false, itemType);
                    generateDataLists(databaseHolder);
                    break;
                case ReloadFromFile:
                    loadRulesFromFileToHolderAndDatabase(databaseHolder, filename, true, itemType);
                    generateDataLists(databaseHolder);
                    break;
                case ReloadFromDatabase:
                    loadDataFromDatabaseToHolder(databaseHolder, true, itemType);
                    generateDataLists(databaseHolder);
                    break;
                case ReloadFromHolder:
                    loadDataFromHolderToDatabase(databaseHolder, true, itemType);
                    generateDataLists(databaseHolder);
                    break;
                case Another:
                    generateDataLists(databaseHolder);
            }
            return null;
        }
    }

    public static class ConnectDbAsync extends AsyncTask<Void, Void, Void> {

        private final DatabaseHolder databaseHolder;

        public ConnectDbAsync(DatabaseHolder databaseHolder) {
            this.databaseHolder = databaseHolder;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            ItemType itemType = ItemType.Races; //todo delete
            System.out.println("AllFromDb " + itemType.getAllFromDatabase(databaseHolder)); //todo delete
            System.out.println("DaoRaceFromName " + (itemType.getDAO(databaseHolder)).getItemWithName("Test_race_name1")); //todo delete

            System.out.println("FirstFromList " + (itemType.getDatabaseList(databaseHolder).get(0))); //todo delete
            System.out.println("Id\"1\" FromMap " + (itemType.getDatabaseMap(databaseHolder).get("1"))); //todo delete

            System.out.println("DaoNames " + itemType.getDAO(databaseHolder).getNames()); //todo delete
            System.out.println("FirstNameFromList " + itemType.getDatabaseList(databaseHolder).get(0).getName()); //todo delete
            System.out.println("Id\"1\" NameFromMap " + itemType.getDatabaseMap(databaseHolder).get("1").getName()); //todo delete

            System.out.println("FirstDescrFromList(ToRaces) " + ((Races) itemType.getDatabaseList(databaseHolder).get(0)).getRaceDescription()); //todo delete
            System.out.println("Id\"1\" DescrFromMap(ToRaces) " + ((Races) itemType.getDatabaseMap(databaseHolder).get("1")).getRaceDescription()); //todo delete

            // todo delete
            System.out.println("why?");
            System.out.println("Races " + (itemType.getDAO(databaseHolder)).getItems());
            System.out.println("ItemRaces " + (itemType.getDAO(databaseHolder)).getItemsAsItem());
            //*/

            closeDatabase(databaseHolder); //todo need to close(?)
            return null;
        }
    }

}