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

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import lombok.Getter;

public class ActivityViewModel<A extends BindingActivity> extends ViewModel {

    @Getter
    protected A activity;

    private ProgressDialog mProgressDialog;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    protected boolean mTwoPane;

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
        Log.i("Menu ", " Clicked menu item: " + item);
        if (this.onOptionsItemSelectedStart()) {
            this.getActivity().startNewActivityWithId(item.getItemId());
        } else {
            Toast.makeText(getActivity(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
        }
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

    public ProgressDialog showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getActivity());
        return mProgressDialog;
    }

    public void showBackButton() {
        // Show the Up button in the action bar.
        ActionBar actionBar = getActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void checkTwoPane(int id) {
        if (getActivity().findViewById(id) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    protected boolean onOptionsItemSelectedStart() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }
}
