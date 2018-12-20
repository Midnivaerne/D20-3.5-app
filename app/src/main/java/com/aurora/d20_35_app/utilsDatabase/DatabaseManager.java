package com.aurora.d20_35_app.utilsDatabase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.aurora.d20_35_app.enums.DatabaseUsage;
import com.aurora.d20_35_app.enums.ItemType;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.models.Races;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lombok.NonNull;

import static com.aurora.d20_35_app.utils.ExternalStorageHandler.getPublicExternalStorageBaseDir;

public class DatabaseManager {

    private static String externalPathSeparator = "/Android/data/com.aurora.d20_3.5_app/";
    public static String path = getPublicExternalStorageBaseDir() + externalPathSeparator + "Data/";

    private static ProgressDialog progressDialog;
    private static final int MAX_PROGRESS = 100;
    private static final int NUMBER_OF_PROGRESS_INCREASES = 10;

    public static void initialDatabasesResolver(BindingActivity activity) {
        startProgressBar(activity);
        initialPathSetup();
        increaseProgressBar();
        SharedPreferences sharedpreferences = activity.getApplicationContext().getSharedPreferences("AppPref", 0);
        if (!sharedpreferences.getBoolean("firstTimeOpened", false)) {
            onFirstTime(sharedpreferences, activity);
        } else {
            onAnotherTime(sharedpreferences, activity);
        }
    }

    private static void onFirstTime(SharedPreferences sharedpreferences, BindingActivity activity) {
        Log.i("Database", "First opening");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("firstTimeOpened", true);
        editor.putString("language", "en");
        editor.apply();
        copyFile("test.xml", activity); //todo test - refactor or delete
        increaseProgressBar();
        chooseDatabaseLoadingType(DatabaseHolder.getDatabaseHolder(activity.getApplicationContext()), DatabaseUsage.Startup, "en");
    }

    private static void onAnotherTime(SharedPreferences sharedpreferences, BindingActivity activity) {
        Log.i("Database", "Another opening");
        increaseProgressBar();
        chooseDatabaseLoadingType(DatabaseHolder.getDatabaseHolder(activity.getApplicationContext()), DatabaseUsage.ReloadFromDatabase, sharedpreferences.getString("language", "en")); // todo refactor/delete
    }

    private static void startProgressBar(@NonNull BindingActivity activity) {
        if (progressDialog != null) {
            progressDialog = activity.showLoading();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(MAX_PROGRESS);
            progressDialog.setTitle("Loading Database");
            progressDialog.setMessage("Loading...");
        }
    }

    private static void increaseProgressBar() {
        if (progressDialog != null) {
            int increaseBy;
            if (progressDialog.getProgress() == MAX_PROGRESS) {
                return;
            } else if (progressDialog.getProgress() < MAX_PROGRESS && (progressDialog.getProgress() > (MAX_PROGRESS - (MAX_PROGRESS / NUMBER_OF_PROGRESS_INCREASES)))) {
                increaseBy = MAX_PROGRESS - progressDialog.getProgress();
            } else {
                increaseBy = MAX_PROGRESS / NUMBER_OF_PROGRESS_INCREASES;
            }
            progressDialog.incrementProgressBy(increaseBy);
        }
    }

    private static void closeProgressBar() {
        if (progressDialog != null) {
            if (progressDialog.getProgress() != MAX_PROGRESS) {
                progressDialog.incrementProgressBy(MAX_PROGRESS - progressDialog.getProgress());
            }
            progressDialog.setMessage("Finished");
            progressDialog.cancel();
            progressDialog = null;
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

    public static void clearWholeDatabaseAndAllHolders(DatabaseHolder databaseHolder) {
        for (ItemType type : ItemType.values()) {
            type.deleteAll(databaseHolder);
        }
    }

    public static void clearWholeDatabase(DatabaseHolder databaseHolder) {
        for (ItemType type : ItemType.values()) {
            type.deleteAllFromDatabase(databaseHolder);
        }
    }

    public static void clearAllHolders(DatabaseHolder databaseHolder) {
        for (ItemType type : ItemType.values()) {
            type.deleteAllFromHolder(databaseHolder);
        }
    }

    public static void closeDatabase(DatabaseHolder databaseHolder) {
        databaseHolder.close();
        DatabaseHolder.destroyInstance();
    }

    private static void loadRulesFromFileToHolderAndDatabase(DatabaseHolder databaseHolder, String filename, boolean reload, ItemType itemType) {
        if (reload) {
            if (itemType == null) {
                for (ItemType it : ItemType.values()) {
                    it.deleteAll(databaseHolder);
                }
            } else {
                itemType.deleteAll(databaseHolder);
            }
        }
        increaseProgressBar();
        loadDataFromFileToHolder(databaseHolder, filename);
        loadDataFromHolderToDatabase(databaseHolder, reload, itemType);
    }

    private static void loadDataFromDatabaseToHolder(DatabaseHolder databaseHolder, boolean reload, ItemType itemType) {
        //todo check first if rules present?
        if (reload) {
            if (itemType != null) {
                itemType.deleteAllFromHolder(databaseHolder);
            } else {
                for (ItemType it : ItemType.values()) {
                    it.deleteAllFromHolder(databaseHolder);
                }
            }
        }
        increaseProgressBar();
        if (itemType != null) {
            itemType.fromDatabaseToHolder(databaseHolder);
        } else {
            for (ItemType it : ItemType.values()) {
                it.fromDatabaseToHolder(databaseHolder);
            }
        }
        increaseProgressBar();
    }

    private static void loadDataFromHolderToDatabase(DatabaseHolder databaseHolder, boolean reload, ItemType itemType) {
        //todo check first if rules present?
        if (reload) {
            if (itemType != null) {
                itemType.deleteAllFromDatabase(databaseHolder);
            } else {
                for (ItemType it : ItemType.values()) {
                    it.deleteAllFromDatabase(databaseHolder);
                }
            }
        }
        increaseProgressBar();
        if (itemType != null) {
            itemType.fromHolderToDatabase(databaseHolder);
        } else {
            for (ItemType it : ItemType.values()) {
                it.fromHolderToDatabase(databaseHolder);
            }
        }
        increaseProgressBar();
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
        increaseProgressBar();
    }

    public static void writeDataToFile(DatabaseHolder databaseHolder) {
        String filename = "testFile.xml";
        File outputFile = new File(path + File.separator + filename);
        XmlSerializer xmlSerializer = Xml.newSerializer(); //todo continue
        //out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
        //out.writeObject(myPersonObject);

    }

    private static void chooseDatabaseLoadingType(DatabaseHolder databaseHolder, DatabaseUsage usage, String language) {
        //String filename = "standardRulesData.xml";
        String filenames[] = {"test.xml", "translations_for_app"}; //todo refactor
        switch (usage) {
            case Startup:
                for (String filename : filenames) {
                    loadRulesFromFileToHolderAndDatabase(databaseHolder, filename, false, null);
                }
                break;
            case ReloadFromFile:
                for (String filename : filenames) {
                    loadRulesFromFileToHolderAndDatabase(databaseHolder, filename, true, null);
                }
                break;
            case ReloadFromDatabase:
                loadDataFromDatabaseToHolder(databaseHolder, true, null);
                break;
            case ReloadFromHolder:
                loadDataFromHolderToDatabase(databaseHolder, true, null);
                break;
            case Another:
                break;
        }
        TranslationsHolder.loadAllTranslationsForLanguage(databaseHolder, language);
    }

    private static void populateAsync(@NonNull final DatabaseHolder databaseHolder, DatabaseUsage usage, String language) {
        PopulateDbAsync task = new PopulateDbAsync(databaseHolder, usage, language);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DatabaseHolder databaseHolder;
        private final DatabaseUsage usage;
        private final String language;

        PopulateDbAsync(DatabaseHolder databaseHolder, DatabaseUsage usage, String language) {
            this.databaseHolder = databaseHolder;
            this.usage = usage;
            this.language = language;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            chooseDatabaseLoadingType(databaseHolder, usage, language);
            return null;
        }

        @Override
        protected void onPostExecute(final Void vo) {
            super.onPostExecute(vo);
            increaseProgressBar();
            closeProgressBar();
        }
    }

    private static void chooseDatabaseActions(DatabaseHolder databaseHolder) {
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
    }

    public static class ConnectDbAsync extends AsyncTask<Void, Void, Void> {

        private final DatabaseHolder databaseHolder;

        public ConnectDbAsync(DatabaseHolder databaseHolder) {
            this.databaseHolder = databaseHolder;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            chooseDatabaseActions(databaseHolder);
            return null;
        }
    }

}