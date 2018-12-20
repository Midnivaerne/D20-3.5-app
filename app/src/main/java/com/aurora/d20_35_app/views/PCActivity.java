package com.aurora.d20_35_app.views;

import android.view.MenuItem;

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
    protected void setTranslatedTexts() {
        ((MenuItem)findViewById(R.id.action_settings)).setTitle(translate("action_settings"));
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
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
