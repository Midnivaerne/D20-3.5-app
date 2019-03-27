package com.aurora.main.views;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.databinding.ActivityHelpBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.main.viewmodels.HelpVM;


public class HelpActivity extends BindingActivity<ActivityHelpBinding, HelpVM> {

  @Override
  public HelpVM onCreate() {
    setSupportActionBar(getExViewDataBinding().toolbar);
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
