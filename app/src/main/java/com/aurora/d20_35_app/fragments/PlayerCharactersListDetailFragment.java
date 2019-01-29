package com.aurora.d20_35_app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.helper.BindingFragment;
import com.aurora.d20_35_app.models.userData.Hero;
import com.aurora.d20_35_app.views.PlayerCharactersListActivity;
import com.aurora.d20_35_app.views.PlayerCharactersListFrameItemDetailActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static com.aurora.d20_35_app.utils.database.DatabaseHolder.getDatabaseHolder;
import static com.aurora.d20_35_app.utils.database.TranslationsHolder.translate;

/**
 * A fragment representing a single Rules set detail screen.
 * This fragment is either contained in a {@link PlayerCharactersListActivity}
 * in two-pane mode (on tablets) or a {@link PlayerCharactersListFrameItemDetailActivity}
 * on handsets.
 */
public class PlayerCharactersListDetailFragment extends BindingFragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Hero item;

    public PlayerCharactersListDetailFragment() {
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_player_characters_list_frame_item_detail_contents_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            item = getDatabaseHolder(super.getContext()).HEROES_PLAYER_LIST.get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)) - 1);
        }
    }

    @Override
    protected void setTranslatedTexts() {
        if (item != null) {
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.activity_player_characters_list_frame_item_detail_toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(translate(item.getName()));
            }
            ((TextView) getMRootView().findViewById(R.id.activity_player_characters_list_frame_item_detail_container)).setText(translate(item.getName()));
        }
    }
}
