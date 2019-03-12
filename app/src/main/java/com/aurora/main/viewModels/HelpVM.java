package com.aurora.main.viewModels;

import com.aurora.core.helper.ActivityViewModel;
import com.aurora.main.views.HelpActivity;

public class HelpVM extends ActivityViewModel<HelpActivity> {

  public HelpVM(HelpActivity activity) {
    super(activity);
    showBackButton();
  }
}
