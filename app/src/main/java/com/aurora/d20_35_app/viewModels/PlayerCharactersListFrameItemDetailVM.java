package com.aurora.d20_35_app.viewModels;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.views.PlayerCharacterActivity;
import com.aurora.d20_35_app.views.PlayerCharactersListFrameItemDetailActivity;

public class PlayerCharactersListFrameItemDetailVM extends ActivityViewModel<PlayerCharactersListFrameItemDetailActivity> {
    public PlayerCharactersListFrameItemDetailVM(PlayerCharactersListFrameItemDetailActivity activity) {
        super(activity);
        showBackButton();
        //checkTwoPane(R.id.activity_player_characters_list_frame_horizontal_container); // todo find proper id
    }

    public void playerCharactersListItemDetailButtonOnClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, PlayerCharacterActivity.class);
        intent.putExtra(PlayerCharacterActivity.HERO_ID, getActivity().getIntent().getStringExtra(PlayerCharacterActivity.HERO_ID));
        context.startActivity(intent);
    }
}
