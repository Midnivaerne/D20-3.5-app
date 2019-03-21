package com.aurora.master.viewmodels;

import com.aurora.core.helper.ActivityViewModel;
import com.aurora.master.views.DMActivity;

public class DMVM extends ActivityViewModel<DMActivity> {

  public DMVM(DMActivity activity) {
    super(activity);

    getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
