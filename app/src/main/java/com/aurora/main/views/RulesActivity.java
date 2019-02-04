package com.aurora.main.views;

import android.view.MenuItem;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityRulesBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.main.viewModels.RulesVM;

import static com.aurora.d20_35_app.utils.database.TranslationsHolder.translate;

public class RulesActivity extends BindingActivity<ActivityRulesBinding, RulesVM> {

    @Override
    public RulesVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        return new RulesVM(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rules;
    }

    @Override
    protected void setTranslatedTexts() {
        getSupportActionBar().setTitle(translate("title_rulesset_detail"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
    }
}
