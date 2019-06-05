package com.aurora.master.views;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.databinding.ActivityDmBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.master.viewmodels.DmViewModel;

public class DmActivity extends BindingActivity<ActivityDmBinding, DmViewModel> {

  @Override
  public DmViewModel onCreate() {
    setSupportActionBar(findViewById(R.id.toolbar));
    return new DmViewModel(this);
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
