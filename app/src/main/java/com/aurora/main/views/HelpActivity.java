package com.aurora.main.views;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityHelpBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.main.viewModels.HelpVM;

import static com.aurora.d20_35_app.database.TranslationsHolder.translate;


public class HelpActivity extends BindingActivity<ActivityHelpBinding, HelpVM> {

    @Override
    public HelpVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        return new HelpVM(this);
    }

    @Override
    protected void setTranslatedTexts() {
        getSupportActionBar().setTitle(translate("title_activity_help_"));
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
