package com.aurora.d20_35_app.viewModels;

import android.app.Activity;
import android.view.View;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.PCActivity;

import com.google.android.material.snackbar.Snackbar;

public class PCVM extends ActivityViewModel<PCActivity> {
    public PCVM(PCActivity activity) {
        super(activity);

    }

    public void addCharacterButtonOnClick(View view, Activity activity) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        //activity.getActionBar().setDisplayHomeAsUpEnabled(true); TODO check and implement setDisplayHomeAsUpEnabled
    }
}
