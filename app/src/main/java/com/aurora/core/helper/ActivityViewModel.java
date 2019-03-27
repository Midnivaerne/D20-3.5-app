package com.aurora.core.helper;

import lombok.Getter;

import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

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

import com.aurora.core.R;

public class ActivityViewModel<A extends BindingActivity> extends ViewModel {

  @Getter
  protected A activity;
  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
   */
  protected boolean vmTwoPane;
  private ConstraintLayout vmConstraintLayout;
  private ProgressBar vmProgressBar;

  public ActivityViewModel(A activity) {
    this.activity = activity;
  }

  public void finish() {
    activity.finish();
  }

  /*
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
    if (vmProgressBar != null && vmProgressBar.isShown()) {
      vmProgressBar.setVisibility(View.INVISIBLE);
    }
    if (vmConstraintLayout != null && vmConstraintLayout.isShown()) {
      vmConstraintLayout.setVisibility(View.INVISIBLE);
    }
  }

  public ProgressBar showLoading() {
    hideLoading();
    //vmProgressBar.setContentView(R.layout.progress_bar); ?
    vmConstraintLayout = (ConstraintLayout) getActivity().findViewById(R.id.loading_layout);
    if (vmConstraintLayout != null) {
      vmConstraintLayout.setBackground(new ColorDrawable(Color.TRANSPARENT));
      vmConstraintLayout.setVisibility(View.VISIBLE);
    }
    vmProgressBar = getActivity().findViewById(R.id.pb_loading);
    if (vmProgressBar != null) {
      vmProgressBar.setVisibility(View.VISIBLE);
      vmProgressBar.setIndeterminate(true);
      //vmProgressBar.setCancelable(false);
      //vmProgressBar.setCanceledOnTouchOutside(false);
      //vmProgressBar.setTitle("Loading Database");
      //vmProgressBar.setMessage("Loading...");
    }
    return vmProgressBar;
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
      vmTwoPane = true;
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
