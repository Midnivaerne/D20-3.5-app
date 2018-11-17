package com.aurora.d20_35_app.helper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aurora.d20_35_app.utils.CommonUtils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import lombok.Getter;

public class ActivityViewModel<A extends AppCompatActivity> extends BaseObservable {

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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

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

    public void showBackButton(){
        // Show the Up button in the action bar.
        ActionBar actionBar = getActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
