package com.aurora.d20_35_app.viewModels;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.DMActivity;

public class DMVM extends ActivityViewModel<DMActivity> {
    public DMVM(DMActivity activity) {
        super(activity);

        getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
