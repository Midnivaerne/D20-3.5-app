package com.aurora.d20_35_app.views;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityPlayerCharacterBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.PlayerCharacterVM;

import static com.aurora.d20_35_app.utilsDatabase.TranslationsHolder.translate;


public class PlayerCharacterActivity extends BindingActivity<ActivityPlayerCharacterBinding, PlayerCharacterVM> {

    @Override
    public PlayerCharacterVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        return new PlayerCharacterVM(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_player_character;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_character, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    protected void setTranslatedTexts() {
        ((Toolbar)findViewById(R.id.toolbar)).setTitle(translate("app_name"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
