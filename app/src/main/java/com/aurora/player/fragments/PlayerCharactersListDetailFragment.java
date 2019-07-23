package com.aurora.player.fragments;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;
import static com.aurora.core.database.TranslationsHolder.translate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.helper.BindingFragment;
import com.aurora.core.database.models.userdata.Hero;
import com.aurora.player.views.PlayerCharactersListActivity;
import com.aurora.player.views.PlayerCharactersListFrameItemDetailActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * A fragment representing a single Rules set detail screen. This fragment is either contained in a {@link PlayerCharactersListActivity} in
 * two-pane mode (on tablets) or a {@link PlayerCharactersListFrameItemDetailActivity} on handsets.
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
      item = getDatabaseHolder(super.getContext()).heroesPlayerList.get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)) - 1);
    }
  }

  @Override
  protected void setTranslatedTexts() {
    if (item != null) {
      Activity activity = this.getActivity();
      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity
          .findViewById(R.id.activity_player_characters_list_frame_item_detail_toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(translate(item.getName()));
      }
      ((TextView) getRootView().findViewById(R.id.activity_player_characters_list_frame_item_detail_container))
          .setText(translate(item.getName()));
    }
  }
}
