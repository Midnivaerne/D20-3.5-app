package com.aurora.d20_35_app.views;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityMainMenuBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.MainMenuVM;

public class MainMenuActivity extends BindingActivity<ActivityMainMenuBinding, MainMenuVM> {

    private static final String KEY_STATUS = "STATUS";

    @Override
    public MainMenuVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        return new MainMenuVM(this, getIntent().getStringExtra(KEY_STATUS));
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_menu;
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
                return DatabasesActivity.class;
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
}
