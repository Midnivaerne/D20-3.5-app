package com.aurora.d20_35_app.helper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import com.aurora.d20_35_app.R;
import lombok.Getter;

public class ActivityViewModel<A extends BindingActivity> extends ViewModel {

    @Getter
    protected A activity;

    private ConstraintLayout mConstraintLayout;
    private ProgressBar mProgressBar;

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
        if (mProgressBar != null && mProgressBar.isShown()) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
        if (mConstraintLayout != null && mConstraintLayout.isShown()) {
            mConstraintLayout.setVisibility(View.INVISIBLE);
        }
    }

    public ProgressBar showLoading() {
        hideLoading();
        //mProgressBar.setContentView(R.layout.progress_bar); ?
        mConstraintLayout = (ConstraintLayout) getActivity().findViewById(R.id.loading_layout);
        if (mConstraintLayout != null) {
            mConstraintLayout.setBackground(new ColorDrawable(Color.TRANSPARENT));
            mConstraintLayout.setVisibility(View.VISIBLE);
        }
        mProgressBar = getActivity().findViewById(R.id.pb_loading);
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setIndeterminate(true);
            //mProgressBar.setCancelable(false);
            //mProgressBar.setCanceledOnTouchOutside(false);
            //mProgressBar.setTitle("Loading Database");
            //mProgressBar.setMessage("Loading...");
        }
        return mProgressBar;
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
