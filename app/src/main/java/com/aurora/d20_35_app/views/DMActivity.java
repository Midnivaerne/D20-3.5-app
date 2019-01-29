package com.aurora.d20_35_app.views;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityDmBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.DMVM;

import static com.aurora.d20_35_app.utils.database.TranslationsHolder.translate;

public class DMActivity extends BindingActivity<ActivityDmBinding, DMVM> {

    @Override
    public DMVM onCreate() {
        setSupportActionBar(findViewById(R.id.toolbar));
        return new DMVM(this);
    }

    @Override
    protected void setTranslatedTexts() {
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dm;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {
        getSupportActionBar().setTitle(translate("title_activity_dm_"));
    }
}
