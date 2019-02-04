package com.aurora.main.viewModels;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.main.views.HelpActivity;

public class HelpVM extends ActivityViewModel<HelpActivity> {
    public HelpVM(HelpActivity activity) {
        super(activity);
        showBackButton();
    }
}
