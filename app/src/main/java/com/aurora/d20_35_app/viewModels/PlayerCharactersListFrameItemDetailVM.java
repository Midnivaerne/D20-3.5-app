package com.aurora.d20_35_app.viewModels;

import android.view.View;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.PlayerCharactersListFrameItemDetailActivity;
import com.google.android.material.snackbar.Snackbar;

public class PlayerCharactersListFrameItemDetailVM extends ActivityViewModel<PlayerCharactersListFrameItemDetailActivity> {
    public PlayerCharactersListFrameItemDetailVM(PlayerCharactersListFrameItemDetailActivity activity) {
        super(activity);
        showBackButton();
        //checkTwoPane(R.id.activity_player_characters_list_frame_horizontal_container); // todo find proper id
    }

    public void playerCharactersListItemDetailButtonOnClick(View view) {
        Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
