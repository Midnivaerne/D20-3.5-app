package com.aurora.player.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityPlayerCharactersListFrameItemDetailBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.player.fragments.PlayerCharactersListDetailFragment;
import com.aurora.player.viewModels.PlayerCharactersListFrameItemDetailVM;

/**
 * An activity representing a single Rules set detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PlayerCharactersListActivity}.
 */
public class PlayerCharactersListFrameItemDetailActivity extends BindingActivity<ActivityPlayerCharactersListFrameItemDetailBinding, PlayerCharactersListFrameItemDetailVM> {

    @Override
    public PlayerCharactersListFrameItemDetailVM onCreate() {
        setSupportActionBar(findViewById(R.id.activity_player_characters_list_frame_item_detail_toolbar));

        if (getIsSavedInstanceStateNull()) {  //todo move it?
            Bundle arguments = new Bundle();
            arguments.putString(PlayerCharactersListDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PlayerCharactersListDetailFragment.ARG_ITEM_ID));
            PlayerCharactersListDetailFragment fragment = new PlayerCharactersListDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_player_characters_list_frame_item_detail_container, fragment)
                    .commit();
        }

        return new PlayerCharactersListFrameItemDetailVM(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_player_characters_list_frame_item_detail;
    }

    @Override
    protected void setTranslatedTexts() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, PlayerCharactersListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
