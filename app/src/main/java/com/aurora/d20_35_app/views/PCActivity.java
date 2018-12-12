package com.aurora.d20_35_app.views;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityPcBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.PCVM;

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
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
