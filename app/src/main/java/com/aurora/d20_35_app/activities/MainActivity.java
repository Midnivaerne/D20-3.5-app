package com.aurora.d20_35_app.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.Startup;
import com.aurora.d20_35_app.utils.BackgroundUserDBInitializer;

import java.util.Arrays;

import lombok.NonNull;

import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG_EXTERNAL_STORAGE = "EXTERNAL_STORAGE";
    public static final int REQUEST_CODE_PERMISSION_ALL = 1;
    public static int readExternalStoragePermission = -1;// Read external storage permission cache.
    public static int writeExternalStoragePermission = -1;// Write external storage permission cache.
    private static String[] permissionTypeString = {"Read", "Write"};
    private static String[] permissionTypeStringManifest = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static String externalPathSeparator = "/Android/data/com.aurora.d20_3.5_app/";

    private static MainActivity instance;
    public static String path = getPublicExternalStorageBaseDir() + externalPathSeparator + "Data/";


    public MainActivity() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reloadPermissions();

        if (Startup.sharedpreferences.getBoolean("firstTimeOpened", true)) {
            Log.i("Permissions ", " Getting initial permissions");
            getPermissions();
            Startup.editor.putBoolean("firstTimeOpened", false).apply();
        } else {
            Log.i("Permissions ", " Already asked for initial permissions");
        }

        Button DM_button = findViewById(R.id.DM_button);
        DM_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPermissions();
                if (writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
                    new BackgroundUserDBInitializer("userDB_handler").start();
                    Intent intent_DM = new Intent(MainActivity.this, DM_Activity.class);
                    MainActivity.this.startActivity(intent_DM);
                    Log.i("Content ", " Main layout to DM ");
                } else {
                    Toast.makeText(getApplicationContext(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button PC_button = findViewById(R.id.PC_button);
        PC_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPermissions();
                if (writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
                    new BackgroundUserDBInitializer("userDB_handler").start();
                    Intent intent_PC = new Intent(MainActivity.this, PC_Activity.class);
                    MainActivity.this.startActivity(intent_PC);
                    Log.i("Content ", " Main layout to PC ");
                } else {
                    Toast.makeText(getApplicationContext(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        reloadPermissions();

        if (writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
            new BackgroundUserDBInitializer("userDB_handler").start();
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            switch (id) {
                case R.id.action_rules:
                    Intent intent_rules = new Intent(MainActivity.this, RulesExplanationActivity.class);
                    MainActivity.this.startActivity(intent_rules);
                    Log.i("Content ", " Main layout to RulesExplanation ");
                    break;
                case R.id.action_content:
                    Intent intent_content = new Intent(MainActivity.this, ContentActivity.class);
                    MainActivity.this.startActivity(intent_content);
                    Log.i("Content ", " Main layout to Content ");
                    break;
                case R.id.action_database:
                    Intent intent_database = new Intent(MainActivity.this, DatabasesListActivity.class);
                    MainActivity.this.startActivity(intent_database);
                    Log.i("Content ", " Main layout to Database/RulesSetList ");
                    break;
                case R.id.action_settings:
                    Intent intent_settings = new Intent(MainActivity.this, Settings_Activity.class);
                    MainActivity.this.startActivity(intent_settings);
                    Log.i("Content ", " Main layout to Settings ");
                    break;
                case R.id.action_help:
                    Intent intent_help = new Intent(MainActivity.this, Help_Activity.class);
                    MainActivity.this.startActivity(intent_help);
                    Log.i("Content ", " Main layout to Help ");
                    break;
                case R.id.action_exit:
                    Log.i("Content ", " Main layout to Exit ");
                    finish();
                    moveTaskToBack(true);
                    break;
            }
            return super.onOptionsItemSelected(item);
        } else {
            Toast.makeText(getApplicationContext(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void reloadPermissions() {
        Log.i("Permissions ", " Reloading permissions...");
        readExternalStoragePermission = ContextCompat.checkSelfPermission(MainActivity.this, permissionTypeStringManifest[0]);
        Log.i("Permissions ", " Loaded " + permissionTypeString[0] + " = " + readExternalStoragePermission);
        writeExternalStoragePermission = ContextCompat.checkSelfPermission(MainActivity.this, permissionTypeStringManifest[1]);
        Log.i("Permissions ", " Loaded " + permissionTypeString[1] + " = " + writeExternalStoragePermission);

    }

    public void getPermissions() {
        reloadPermissions();
        if (!hasPermissions(this, permissionTypeStringManifest)) {
            ActivityCompat.requestPermissions(this, permissionTypeStringManifest, REQUEST_CODE_PERMISSION_ALL);
            Log.i("Permissions ", " Asked for " + Arrays.toString(permissionTypeString) + " External Storage permissions");
        } else {
            Log.i("Permissions ", " " + Arrays.toString(permissionTypeString) + " External Storage permissions already granted");
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    // This method is invoked after user click buttons in permission grant popup dialog.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length > 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "You grant " + Arrays.toString(permissionTypeString).toLowerCase() + " external storage permission", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "You denied " + Arrays.toString(permissionTypeString).toLowerCase() + " external storage permission.", Toast.LENGTH_LONG).show();
            }
            Log.i("Permissions ", "Finished asking for " + Arrays.toString(permissionTypeString) + " permissions");
            reloadPermissions();
        } else {
            Log.i("Permissions ", " No permission specified");
        }
    }

}
