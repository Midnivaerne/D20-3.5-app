package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import lombok.NonNull;

import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.userdata.Hero;
import com.aurora.core.database.models.userdata.HeroPlayer;
import com.aurora.player.fragments.PlayerCharactersListDetailFragment;
import com.aurora.player.views.PlayerCharacterActivity;
import com.aurora.player.views.PlayerCharactersListActivity;
import com.aurora.player.views.PlayerCharactersListFrameItemDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlayerCharacterRecyclerViewAdapter extends RecyclerView.Adapter<PlayerCharacterRecyclerViewAdapter.ViewHolder> {

  private final PlayerCharactersListActivity playerCharactersListActivity;
  private final Map<Integer, HeroPlayer> heroPlayerMap;
  private final boolean twoPaneMode;

  public PlayerCharacterRecyclerViewAdapter(PlayerCharactersListActivity parent,
      Map<Integer, HeroPlayer> items,
      boolean twoPane) {
    heroPlayerMap = items;
    playerCharactersListActivity = parent;
    twoPaneMode = twoPane;
  }

  private final View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if (twoPaneMode) {
        Bundle arguments = new Bundle();
        arguments.putString(PlayerCharactersListDetailFragment.ARG_ITEM_ID, getListId(view));
        arguments.putString(PlayerCharacterActivity.HERO_PLAYER_ID, getHeroId(view));
        PlayerCharactersListDetailFragment fragment = new PlayerCharactersListDetailFragment();
        fragment.setArguments(arguments);
        playerCharactersListActivity.getSupportFragmentManager().beginTransaction()
            .replace(R.id.activity_player_characters_list_frame_horizontal_container, fragment)
            .commit();
      } else {
        Context context = view.getContext();
        Intent intent = new Intent(context, PlayerCharactersListFrameItemDetailActivity.class);
        intent.putExtra(PlayerCharactersListDetailFragment.ARG_ITEM_ID, getListId(view));
        intent.putExtra(PlayerCharacterActivity.HERO_PLAYER_ID, getHeroId(view));
        context.startActivity(intent);
      }
    }
  };

  private final View.OnClickListener playerCharactersListItemButtonOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Context context = view.getContext();
      Intent intent = new Intent(context, PlayerCharacterActivity.class);
      intent.putExtra(PlayerCharacterActivity.HERO_PLAYER_ID, getHeroId(view));
      context.startActivity(intent);
    }
  };

  private String getListId(View view) {
    Item heroPlayer = (HeroPlayer) view.getTag();
    int listId = heroPlayer.getItemID();
    return String.valueOf(listId);
  }

  private String getHeroId(View view) {
    HeroPlayer heroPlayer = (HeroPlayer) view.getTag();
    int listId = heroPlayer.getItemID();
    return String.valueOf(heroPlayerMap.get(listId).getItemID());
  }

  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.activity_player_characters_list_frame_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
    Hero hero = heroPlayerMap.get(position + 1); //todo check for id conflicts after several in-app hero player adding and deleting

    holder.idView.setText(translate(hero.getName()));
    holder.idView.setTag(hero);
    holder.idView.setOnClickListener(onClickListener);

    holder.contentView.setText(translate(hero.getContent()));
    holder.contentView.setTag(hero);
    holder.contentView.setOnClickListener(onClickListener);

    holder.openButton.setTag(hero);
    holder.openButton.setOnClickListener(playerCharactersListItemButtonOnClickListener);
  }

  @Override
  public int getItemCount() {
    return heroPlayerMap.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final TextView idView;
    final TextView contentView;
    final FloatingActionButton openButton;

    ViewHolder(View view) {
      super(view);
      idView = (TextView) view.findViewById(R.id.id_pc_text);
      contentView = (TextView) view.findViewById(R.id.pc_content);
      openButton = (FloatingActionButton) view.findViewById(R.id.open_character);
    }
  }
}
