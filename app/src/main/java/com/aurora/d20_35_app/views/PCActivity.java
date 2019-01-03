package com.aurora.d20_35_app.views;

import android.view.Menu;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityPcBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.PCVM;

import static com.aurora.d20_35_app.utilsDatabase.TranslationsHolder.translate;

public class PCActivity extends BindingActivity<ActivityPcBinding, PCVM> {

    @Override
    public PCVM onCreate() {
        setSupportActionBar(findViewById(R.id.toolbar));
        return new PCVM(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pc;
    }

    @Override
    protected void setTranslatedTexts() {
        getSupportActionBar().setTitle(translate("title_activity_pc_"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_character, menu);
        menu.findItem(R.id.action_settings).setTitle(translate("action_settings"));
        return true;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
