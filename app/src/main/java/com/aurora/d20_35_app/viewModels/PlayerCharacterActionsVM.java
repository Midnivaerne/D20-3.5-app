package com.aurora.d20_35_app.viewModels;

import android.view.View;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.PlayerCharacterActionsActivity;
import com.google.android.material.snackbar.Snackbar;

public class PlayerCharacterActionsVM extends ActivityViewModel<PlayerCharacterActionsActivity> {
    public PlayerCharacterActionsVM(PlayerCharacterActionsActivity activity) {
        super(activity);
        showBackButton();
    }

    public void playerCharacterActionsButtonOnClick(View view) {
        Snackbar.make(view, "FindMe2", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
