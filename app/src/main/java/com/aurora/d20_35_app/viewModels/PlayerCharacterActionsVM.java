package com.aurora.d20_35_app.viewModels;

import android.view.View;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.PlayerCharacterActionsActivity;
import com.google.android.material.snackbar.Snackbar;

public class PlayerCharacterActionsVM extends ActivityViewModel<PlayerCharacterActionsActivity> {
    public PlayerCharacterActionsVM(PlayerCharacterActionsActivity activity) {
        super(activity);

    }

    public void PlayerCharacterActionsButtonOnClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
