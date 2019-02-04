package com.aurora.master.viewModels;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.master.views.DMActivity;

public class DMVM extends ActivityViewModel<DMActivity> {
    public DMVM(DMActivity activity) {
        super(activity);

        getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
