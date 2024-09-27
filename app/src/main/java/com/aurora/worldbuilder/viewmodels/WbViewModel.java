package com.aurora.worldbuilder.viewmodels;

import com.aurora.core.helper.ActivityViewModel;
import com.aurora.worldbuilder.views.WbActivity;

public class WbViewModel extends ActivityViewModel<WbActivity> {

  public WbViewModel(WbActivity activity) {
    super(activity);

    getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
