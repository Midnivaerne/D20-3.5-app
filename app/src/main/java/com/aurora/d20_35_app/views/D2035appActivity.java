package com.aurora.d20_35_app.views;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityD2035appBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.models.DatabaseCreator;
import com.aurora.d20_35_app.models.DatabaseManager;
import com.aurora.d20_35_app.viewModels.D2035appVM;

public class D2035appActivity extends BindingActivity<ActivityD2035appBinding, D2035appVM> {

    private static final String KEY_STATUS = "STATUS";

    @Override
    public D2035appVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            DatabaseCreator.initialDatabasesResolver(this);
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            switch (id) {
                case R.id.action_rules:
                    Intent intent_rules = new Intent(D2035appActivity.this, RulesExplanationActivity.class);
                    D2035appActivity.this.startActivity(intent_rules);
                    Log.i("Content ", " Main layout to RulesExplanation ");
                    break;
                case R.id.action_content:
                    Intent intent_content = new Intent(D2035appActivity.this, D2035appActivity.class);
                    D2035appActivity.this.startActivity(intent_content);
                    Log.i("Content ", " Main layout to Content ");
                    break;
                case R.id.action_database:
                    Intent intent_database = new Intent(D2035appActivity.this, DatabasesListActivity.class);
                    D2035appActivity.this.startActivity(intent_database);
                    Log.i("Content ", " Main layout to Database/RulesSetList ");
                    break;
                case R.id.action_settings:
                    Intent intent_settings = new Intent(D2035appActivity.this, Settings_Activity.class);
                    D2035appActivity.this.startActivity(intent_settings);
                    Log.i("Content ", " Main layout to Settings ");
                    break;
                case R.id.action_help:
                    Intent intent_help = new Intent(D2035appActivity.this, Help_Activity.class);
                    D2035appActivity.this.startActivity(intent_help);
                    Log.i("Content ", " Main layout to Help ");
                    break;
                case R.id.action_exit:
                    Log.i("Content ", " Main layout to Exit ");
                    finish();
                    moveTaskToBack(true);
                    break;
            }
            return super.onOptionsItemSelected(item);
        } else {
            Toast.makeText(getApplicationContext(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
