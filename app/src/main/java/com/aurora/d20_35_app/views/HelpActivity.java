package com.aurora.d20_35_app.views;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityHelpBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.HelpVM;


public class HelpActivity extends BindingActivity<ActivityHelpBinding, HelpVM> {

    @Override
    public HelpVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        return new HelpVM(this);
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
