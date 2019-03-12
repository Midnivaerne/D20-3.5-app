package com.aurora.main.views;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.view.MenuItem;
import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.databinding.ActivityRulesBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.main.viewModels.RulesVM;

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
