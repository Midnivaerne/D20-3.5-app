package com.aurora.player.viewmodels;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.aurora.core.R;
import com.aurora.core.helper.ActivityViewModel;
import com.aurora.player.adapters.PlayerCharacterRecyclerViewAdapter;
import com.aurora.player.views.PlayerCharactersListActivity;
import com.google.android.material.snackbar.Snackbar;

public class PlayerCharactersListVM extends ActivityViewModel<PlayerCharactersListActivity> {

  public PlayerCharactersListVM(PlayerCharactersListActivity activity) {
    super(activity);
    showBackButton();
    checkTwoPane(R.id.activity_player_characters_list_frame_horizontal_container);

    View recyclerView = getActivity().findViewById(R.id.activity_player_characters_list_frame_view);
    assert recyclerView != null;
    setupRecyclerView((RecyclerView) recyclerView);
  }

  public void addCharacterButtonOnClick(View view) {
    Snackbar.make(view, "Create new hero", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
  }

  private void setupRecyclerView(RecyclerView recyclerView) {
    recyclerView
        .setAdapter(
            new PlayerCharacterRecyclerViewAdapter(this.getActivity(), getDatabaseHolder(super.getActivity()).heroesPlayerMap, vmTwoPane));
  }
}
