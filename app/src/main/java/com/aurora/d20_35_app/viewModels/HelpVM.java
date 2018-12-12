package com.aurora.d20_35_app.viewModels;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.HelpActivity;

public class HelpVM extends ActivityViewModel<HelpActivity> {
    public HelpVM(HelpActivity activity) {
        super(activity);

        showBackButton();
    }
}
