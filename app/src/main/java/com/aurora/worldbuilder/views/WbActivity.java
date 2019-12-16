package com.aurora.worldbuilder.views;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.R;
import com.aurora.core.databinding.ActivityDmBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.worldbuilder.viewmodels.WbViewModel;

public class WbActivity extends BindingActivity<ActivityDmBinding, WbViewModel> {

  @Override
  public WbViewModel onCreate() {
    setSupportActionBar(findViewById(R.id.toolbar));
    return new WbViewModel(this);
  }

  @Override
  protected void setTranslatedTexts() {
  }

  @Override
  public int getBindingVariable() {
    return com.aurora.core.BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_wb;
  }

  @Override
  public void onFragmentAttached() {

  }

  @Override
  public void onFragmentDetached(String tag) {
    getSupportActionBar().setTitle(translate("title_activity_wb_"));
  }
}
