package com.aurora.master.viewmodels;

import com.aurora.core.helper.ActivityViewModel;
import com.aurora.master.views.DmActivity;

public class DmViewModel extends ActivityViewModel<DmActivity> {

  public DmViewModel(DmActivity activity) {
    super(activity);

    getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
