package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.aurora.core.R;
import com.aurora.player.playerCharacterUtils.PlayerCharacterAbilityScoresEnum;
import com.aurora.player.playerCharacterUtils.PlayerCharacterAbilityScoresSpecificEnum;
import com.aurora.player.playerCharacterUtils.PlayerCharacterCombatEnum;
import com.aurora.player.playerCharacterUtils.PlayerCharacterCombatSpecificEnum;
import com.aurora.player.playerCharacterUtils.PlayerCharacterEnumBase;
import com.aurora.player.playerCharacterUtils.PlayerCharacterSavingThrowsEnum;
import com.aurora.player.playerCharacterUtils.PlayerCharacterSavingThrowsSpecificEnum;
import com.aurora.player.playerCharacterUtils.PlayerCharacterSpecificEnumBase;
import com.aurora.player.playerCharacterUtils.PlayerCharacterSuperBase;
import com.aurora.player.viewmodels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterStatisticsPlaceholderFragment extends Fragment {

  public static final String ARG_SECTION_NUMBER = "section_number";

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterStatisticsPlaceholderFragment() {
  }

  public static PlayerCharacterStatisticsPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
    PlayerCharacterStatisticsPlaceholderFragment fragment = new PlayerCharacterStatisticsPlaceholderFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    fragment.playerCharacterVM = playerCharacterVM;
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_player_character_statistics, container, false);
    loadHeroDataFromVMtoView(rootView);
    return rootView;
  }

  private void loadHeroDataFromVMtoView(View rootView) {
    PlayerCharacterSuperBase[][][] all = {{PlayerCharacterCombatEnum.values(), PlayerCharacterCombatSpecificEnum.values()},
        {PlayerCharacterAbilityScoresEnum.values(), PlayerCharacterAbilityScoresSpecificEnum.values()},
        {PlayerCharacterSavingThrowsEnum.values(), PlayerCharacterSavingThrowsSpecificEnum.values()}};
    for (PlayerCharacterSuperBase[][] values : all) {
      for (PlayerCharacterEnumBase field : ((PlayerCharacterEnumBase[]) values[0])) {
        for (PlayerCharacterSpecificEnumBase specific : ((PlayerCharacterSpecificEnumBase[]) values[1])) {
          loader(rootView, field, specific);
        }
      }
    }
  }

  private void loader(View rootView, PlayerCharacterEnumBase fieldBase, PlayerCharacterSpecificEnumBase specificBase) {
    ((TextView) rootView.findViewById(fieldBase.getFieldId()).findViewById(specificBase.getSpecificFieldId(fieldBase)))
        .setText(specificBase.getSpecificValue(fieldBase, playerCharacterVM.getHero().getHeroValues()));
  }

}
