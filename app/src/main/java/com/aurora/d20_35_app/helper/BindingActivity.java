package com.aurora.d20_35_app.helper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.aurora.d20_35_app.utils.NetworkUtils;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.AndroidInjection;
import lombok.Getter;
import lombok.NonNull;

public abstract class BindingActivity<VDB extends ViewDataBinding, AVM extends ActivityViewModel> extends AppCompatActivity implements FragmentViewModel.Callback {

    @Getter
    private VDB mViewDataBinding;
    @Getter
    private AVM mActivityViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //performDependencyInjection();  TODO brakes Startup.java
        super.onCreate(savedInstanceState);
        bind();
    }

    public void bind() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mActivityViewModel = mActivityViewModel == null ? onCreate() : mActivityViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mActivityViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    public void resetViewModel() {
        mActivityViewModel = null;
        mActivityViewModel = onCreate();
        getMViewDataBinding().setVariable(getBindingVariable(), mActivityViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityViewModel.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mActivityViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityViewModel.onResume();
    }

    @Override
    public void onBackPressed() {
        if (!mActivityViewModel.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        mActivityViewModel.onStop();
        super.onStop();
    }

    @Override
    protected void onPause() {
        mActivityViewModel.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityViewModel.onDestroy();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActivityViewModel.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mActivityViewModel.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mActivityViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActivityViewModel.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mActivityViewModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mActivityViewModel.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mActivityViewModel.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mActivityViewModel.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mActivityViewModel.onWindowFocusChanged(hasFocus);
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
        if (mActivityViewModel != null) {
            mActivityViewModel.hideLoading();
        }
    }

    public void showLoading() {
        if (mActivityViewModel != null) {
            mActivityViewModel.showLoading();
        }
    }

    public abstract AVM onCreate();

    /**
     * Override for set mViewDataBinding variable
     *
     * @return variable id
     */
    public abstract
    @IdRes
    int getBindingVariable();

    /**
     * Override for set layout resource
     *
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();
}
