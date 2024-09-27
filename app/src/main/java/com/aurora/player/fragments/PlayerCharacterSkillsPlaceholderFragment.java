package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aurora.core.R;
import com.aurora.player.adapters.SkillsRecyclerViewAdapter;
import com.aurora.player.viewmodels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterSkillsPlaceholderFragment extends Fragment {

  /**
   * The fragment argument representing the section number for this fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterSkillsPlaceholderFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section number.
   */
  public static PlayerCharacterSkillsPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
    PlayerCharacterSkillsPlaceholderFragment fragment = new PlayerCharacterSkillsPlaceholderFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    fragment.playerCharacterVM = playerCharacterVM;
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_player_character_skills, container, false);
    loadHeroDataFromVMtoView(rootView);
    return rootView;
  }

  private void loadHeroDataFromVMtoView(View rootView) {
    View recyclerView = rootView.findViewById(R.id.fragment_player_character_skills_layout);
    assert recyclerView != null;
    ((RecyclerView) recyclerView).setAdapter(new SkillsRecyclerViewAdapter(playerCharacterVM.getHero()));
  }
}

