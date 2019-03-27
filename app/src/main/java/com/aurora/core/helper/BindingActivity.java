package com.aurora.core.helper;

import lombok.Getter;
import lombok.NonNull;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.aurora.core.R;
import com.aurora.core.utils.NetworkUtils;

public abstract class BindingActivity<T extends ViewDataBinding, V extends ActivityViewModel> extends AppCompatActivity implements
    BindingFragment.Callback {

  @Getter
  private T exViewDataBinding;
  @Getter
  private V exActivityViewModel;
  @Getter
  private Boolean isSavedInstanceStateNull;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    isSavedInstanceStateNull = savedInstanceState == null;
    setApplicationTheme();
    super.onCreate(savedInstanceState);
    bind();
  }

  public abstract V onCreate();

  protected void setApplicationTheme() {
    int themeId = this.getApplicationContext().getSharedPreferences("AppPref", 0).getInt("AppThemeId", R.style.AppTheme);
    setTheme(themeId);
  }

  protected abstract void setTranslatedTexts();

  public void bind() {
    exViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.exActivityViewModel = exActivityViewModel == null ? onCreate() : exActivityViewModel;
    exViewDataBinding.setVariable(getBindingVariable(), exActivityViewModel);
    exViewDataBinding.executePendingBindings();
  }

  public void resetViewModel() {
    exActivityViewModel = null;
    exActivityViewModel = onCreate();
    getExViewDataBinding().setVariable(getBindingVariable(), exActivityViewModel);
  }

  @Override
  protected void onStart() {
    super.onStart();
    exActivityViewModel.onStart();
    setTranslatedTexts();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    exActivityViewModel.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  protected void onResume() {
    super.onResume();
    exActivityViewModel.onResume();
  }

  @Override
  public void onBackPressed() {
    if (!exActivityViewModel.onBackPressed()) {
      super.onBackPressed();
    }
  }

  @Override
  protected void onStop() {
    exActivityViewModel.onStop();
    super.onStop();
  }

  @Override
  protected void onPause() {
    exActivityViewModel.onPause();
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    exActivityViewModel.onDestroy();
  }

  @Override
  public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    exActivityViewModel.onPostCreate(savedInstanceState);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    exActivityViewModel.onOptionsItemSelected(item);
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    exActivityViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    exActivityViewModel.onConfigurationChanged(newConfig);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    exActivityViewModel.onRestoreInstanceState(savedInstanceState);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    exActivityViewModel.onSaveInstanceState(outState);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    exActivityViewModel.onCreateOptionsMenu(menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    exActivityViewModel.onPrepareOptionsMenu(menu);
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    exActivityViewModel.onWindowFocusChanged(hasFocus);
  }

  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if (imm != null) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      }
    }
  }

  public boolean isNetworkConnected() {
    return NetworkUtils.isNetworkConnected(getApplicationContext());
  }

  public void hideLoading() {
    if (exActivityViewModel != null) {
      exActivityViewModel.hideLoading();
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
  }

  public ProgressBar showLoading() {
    if (exActivityViewModel != null) {
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
          WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
      return exActivityViewModel.showLoading();
    }
    return null;
  }

  public void startNewActivityWithId(int destinationID) {
    if (destinationID == R.id.action_exit) {
      Log.i("Content ", " Exiting");
      finish();
      moveTaskToBack(true);
    } else {
      Class<?> destination = chooseNewActivity(destinationID);
      if (destination != null) {
        Intent intentRules = new Intent(this, destination);
        this.startActivity(intentRules);
      } else {
        Log.i("Content ", " Empty destination");
      }
    }
  }

  protected Class<?> chooseNewActivity(int destinationID) {
    return null;
  }

  /**
   * Override for set exViewDataBinding variable.
   *
   * @return variable id
   */
  @IdRes
  public abstract int getBindingVariable();

  /**
   * Override for set layout resource.
   *
   * @return layout resource id
   */
  @LayoutRes
  public abstract int getLayoutId();
}
