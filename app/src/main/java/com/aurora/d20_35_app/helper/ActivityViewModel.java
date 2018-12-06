package com.aurora.d20_35_app.helper;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurora.d20_35_app.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;
import lombok.Getter;

public class ActivityViewModel<A extends AppCompatActivity> extends BaseObservable {

    private static final int REQUEST_CODE_PERMISSION_ALL = 1;
    @Getter
    private static String[] permissionTypeStringManifest = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Getter
    protected A activity;
    private ProgressDialog mProgressDialog;

    public ActivityViewModel(A activity) {
        this.activity = activity;
    }

    public void finish() {
        activity.finish();
    }

    /**
     * Activity lifecycle
     */

    public void onStart() {

    }

    public void onStop() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onResume() {

    }

    public boolean onBackPressed() {
        return false;
    }

    public void onDestroy() {
        //realm.close();
    }

    public void onPause() {

    }

    public void onPostCreate(Bundle savedInstanceState) {

    }

    public void onOptionsItemSelected(MenuItem item) {

    }

    public void onConfigurationChanged(Configuration newConfig) {

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onCreateOptionsMenu(Menu menu) {

    }

    public void onPrepareOptionsMenu(Menu menu) {

    }

    public void onWindowFocusChanged(boolean hasFocus) {

    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getActivity());
    }

    public void showBackButton() {
        // Show the Up button in the action bar.
        ActionBar actionBar = getActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        Log.i("Permissions ", " Getting permissions");
        for (final String permission : permissionTypeStringManifest) {
            final int result = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }

        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions.toArray(new String[missingPermissions.size()]);
            Log.i("Permissions ", " Asked for " + Arrays.toString(permissions) + " permissions");
            ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_CODE_PERMISSION_ALL);
        } else {
            final int[] grantResults = new int[permissionTypeStringManifest.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_PERMISSION_ALL, permissionTypeStringManifest, grantResults);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_ALL:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        Log.i("Permissions ", " " + permissions[index] + " not granted, exiting");
                        Toast.makeText(getActivity(), "Required permission '" + permissions[index] + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                Log.i("Permissions ", " All permissions granted");
                break;
        }
    }

}
