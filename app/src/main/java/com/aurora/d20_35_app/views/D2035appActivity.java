package com.aurora.d20_35_app.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityD2035appBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.utilsDatabase.DatabaseManager;
import com.aurora.d20_35_app.viewModels.D2035appVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.core.content.ContextCompat;
import lombok.Getter;

public class D2035appActivity extends BindingActivity<ActivityD2035appBinding, D2035appVM> {

    @Getter
    private static String[] permissionTypeStringManifest = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String KEY_STATUS = "STATUS";
    private static final int REQUEST_CODE_PERMISSION_ALL = 1;

    @Override
    public D2035appVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        checkPermissions();
        return new D2035appVM(this, getIntent().getStringExtra(KEY_STATUS));
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_d2035app;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startNewActivityFromMain(int destinationID) {
        if (destinationID == R.id.action_exit) {
            Log.i("Content ", " Exiting");
            finish();
            moveTaskToBack(true);
        } else {
            Intent intent_rules = new Intent(this, chooseNewActivity(destinationID));
            this.startActivity(intent_rules);
        }

    }

    private Class<?> chooseNewActivity(int destinationID) {
        switch (destinationID) {
            case R.id.PC_button:
                return PCActivity.class;
            case R.id.DM_button:
                return DMActivity.class;
            case R.id.action_rules:
                return RulesActivity.class;
            case R.id.action_content:
                return null; //todo ContentActivity.class
            case R.id.action_database:
                return DatabasesListActivity.class;
            case R.id.action_settings:
                return SettingsActivity.class;
            case R.id.action_help:
                return HelpActivity.class;
        }
        return null;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        getMissingPermissions(missingPermissions);

        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions.toArray(new String[missingPermissions.size()]);
            Log.i("Permissions ", " Asked for " + Arrays.toString(permissions) + " permissions");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, REQUEST_CODE_PERMISSION_ALL);
            } else {
                Toast.makeText(this, "Required API level 23, exiting", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            final int[] grantResults = new int[permissionTypeStringManifest.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_PERMISSION_ALL, permissionTypeStringManifest, grantResults);
        }
    }

    private void getMissingPermissions(List<String> missingPermissions) {

        // check all required dynamic permissions
        Log.i("Permissions ", " Getting permissions");
        for (final String permission : permissionTypeStringManifest) {
            final int result = ContextCompat.checkSelfPermission(this.getApplicationContext(), permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_ALL:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        Log.i("Permissions ", " " + permissions[index] + " not granted, exiting");
                        Toast.makeText(this, "Required permission '" + permissions[index] + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }

                break;
        }
        final List<String> stillMissingPermissions = new ArrayList<String>();
        getMissingPermissions(stillMissingPermissions);
        if (stillMissingPermissions.isEmpty()) {
            Log.i("Permissions ", " All permissions granted");
            DatabaseManager.initialDatabasesResolver(this);
        } else {
            Log.i("Permissions ", " Permissions not granted yet, still deciding");
        }
    }

}
