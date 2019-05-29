package com.aurora.player.fragments;

import static com.aurora.core.database.TranslationsHolder.translate;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.ABILITY_SCORES_DESCRIPTIONS;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.ARG_SECTION_NUMBER;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.ID_ABILITY_SCORES_VALUES;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.ID_ABILITY_SCORES_VALUES_SPECIFIC;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.ID_SAVING_THROWS_VALUES;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.ID_SAVING_THROWS_VALUES_SPECIFIC;
import static com.aurora.player.playerCharacterUtils.PlayerCharacterStatisticsUtils.SAVING_THROWS_DESCRIPTIONS;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurora.core.R;
import com.aurora.core.models.userdata.HeroValues;
import com.aurora.player.playerCharacterUtils.PlayerCharacterCombatEnum;
import com.aurora.player.viewmodels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterStatisticsPlaceholderFragment extends Fragment {

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
    for (PlayerCharacterCombatEnum combatField : PlayerCharacterCombatEnum.values()) {
      ((TextView) rootView.findViewById(combatField.getFieldId())
          .findViewById(combatField.getDescriptionId()))
          .setText(translate(combatField.toString()));
      ((TextView) rootView.findViewById(combatField.getFieldId()).findViewById(combatField.getValueId()))
          .setText(playerCharacterVM.getHero().getHeroValues().getCombatTextValues().get(combatField));
    }
    for (int i = 0; i < ID_ABILITY_SCORES_VALUES.length; i++) {
      ((TextView) rootView.findViewById(ID_ABILITY_SCORES_VALUES[i]).findViewById(ID_ABILITY_SCORES_VALUES_SPECIFIC[0]))
          .setText(translate(ABILITY_SCORES_DESCRIPTIONS[i]));
      ((TextView) rootView.findViewById(ID_ABILITY_SCORES_VALUES[i]).findViewById(ID_ABILITY_SCORES_VALUES_SPECIFIC[1]))
          .setText(playerCharacterVM.getHeroAbilityScoresTextValues()[i]);
      ((TextView) rootView.findViewById(ID_ABILITY_SCORES_VALUES[i]).findViewById(ID_ABILITY_SCORES_VALUES_SPECIFIC[2])).setText(
          String.valueOf(HeroValues.getStatisticModifier(Integer.parseInt(playerCharacterVM.getHeroAbilityScoresTextValues()[i]))));

      //todo add modifiers, don't show if the same as base values
      ((TextView) rootView.findViewById(ID_ABILITY_SCORES_VALUES[i]).findViewById(ID_ABILITY_SCORES_VALUES_SPECIFIC[3]))
          .setText(playerCharacterVM.getHeroAbilityScoresTextValues()[i]);
      ((TextView) rootView.findViewById(ID_ABILITY_SCORES_VALUES[i]).findViewById(ID_ABILITY_SCORES_VALUES_SPECIFIC[4])).setText(
          String.valueOf(HeroValues.getStatisticModifier(Integer.parseInt(playerCharacterVM.getHeroAbilityScoresTextValues()[i]))));
    }
    for (int i = 0; i < ID_SAVING_THROWS_VALUES.length; i++) {
      ((TextView) rootView.findViewById(ID_SAVING_THROWS_VALUES[i]).findViewById(ID_SAVING_THROWS_VALUES_SPECIFIC[0]))
          .setText(translate(SAVING_THROWS_DESCRIPTIONS[i]));
      ((TextView) rootView.findViewById(ID_SAVING_THROWS_VALUES[i]).findViewById(ID_SAVING_THROWS_VALUES_SPECIFIC[1]))
          .setText(playerCharacterVM.getHeroSavingThrowsTextValues()[i]);
    }
  }

}
