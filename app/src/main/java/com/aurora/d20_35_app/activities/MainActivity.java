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
import com.aurora.d20_35_app.utils.BackgroundUserDBInitializer;

import lombok.NonNull;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG_EXTERNAL_STORAGE = "EXTERNAL_STORAGE";
    public static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 1;
    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 1;

    private static MainActivity instance;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPermissions();
        new BackgroundUserDBInitializer("userDB_handler").start();

        Button DM_button = (Button) findViewById(R.id.DM_button);
        DM_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_DM = new Intent(MainActivity.this, DM_Activity.class);
                MainActivity.this.startActivity(intent_DM);
                Log.i("Content ", " Main layout to DM ");
            }
        });

        Button PC_button = (Button) findViewById(R.id.PC_button);
        PC_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_PC = new Intent(MainActivity.this, PC_Activity.class);
                MainActivity.this.startActivity(intent_PC);
                Log.i("Content ", " Main layout to PC ");
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_database:
                Intent intent_database = new Intent(MainActivity.this, RulessetListActivity.class);
                MainActivity.this.startActivity(intent_database);
                Log.i("Content ", " Main layout to Database ");
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
    }

    // This method is invoked after user click buttons in permission grant popup dialog.
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION) {
            int grantResultsLength = grantResults.length;
            if (grantResultsLength > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "You grant write external storage permission. Please click original button again to continue.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "You denied write external storage permission.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void getPermissions() {
        // Check whether this app has read external storage permission or not.
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        // If do not grant read external storage permission.
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            // Request user to grant read external storage permission.
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION);
        }

        // Check whether this app has write external storage permission or not.
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If do not grant write external storage permission.
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            // Request user to grant write external storage permission.
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
        }
    }

}
