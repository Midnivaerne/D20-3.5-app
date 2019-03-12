package com.aurora.player.fragments;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.aurora.core.R;
import com.aurora.core.models.userData.HeroValues;
import com.aurora.player.viewModels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterStatisticsPlaceholderFragment extends Fragment {

  private static final String ARG_SECTION_NUMBER = "section_number";

  private static final String HERO_COMBAT_HIT_POINTS_NAME_DESCRIPTION = "hero_combat_hp_name_description";
  private static final String HERO_COMBAT_DAMAGE_REDUCTION_NAME_DESCRIPTION = "hero_combat_damage_reduction_name_description";
  private static final String HERO_COMBAT_ARMOUR_CLASS_NAME_DESCRIPTION = "hero_combat_armour_class_name_description";
  private static final String HERO_COMBAT_ARMOUR_CLASS_TOUCH_NAME_DESCRIPTION = "hero_combat_armour_class_touch_name_description";
  private static final String HERO_COMBAT_ARMOUR_CLASS_FLATFOOTED_NAME_DESCRIPTION = "hero_combat_armour_class_flatfooted_name_description";
  private static final String HERO_COMBAT_SPEED_NAME_DESCRIPTION = "hero_combat_speed_name_description";
  private static final String HERO_COMBAT_INITIATIVE_NAME_DESCRIPTION = "hero_combat_initiative_name_description";
  private static final String HERO_COMBAT_ATTACK_NAME_DESCRIPTION = "hero_combat_attack_name_description";
  private static final String HERO_COMBAT_ATTACK_MELEE_NAME_DESCRIPTION = "hero_combat_attack_melee_name_description";
  private static final String HERO_COMBAT_ATTACK_RANGED_NAME_DESCRIPTION = "hero_combat_attack_ranged_name_description";
  private static final String HERO_COMBAT_GRAPPLE_NAME_DESCRIPTION = "hero_combat_grapple_name_description";
  private static final String HERO_COMBAT_SPELL_RESISTANCE_NAME_DESCRIPTION = "hero_combat_spell_resistance_name_description";

  private static final String COMBAT_DESCRIPTIONS[] = {
      HERO_COMBAT_HIT_POINTS_NAME_DESCRIPTION,
      HERO_COMBAT_DAMAGE_REDUCTION_NAME_DESCRIPTION,
      HERO_COMBAT_ARMOUR_CLASS_NAME_DESCRIPTION,
      HERO_COMBAT_ARMOUR_CLASS_TOUCH_NAME_DESCRIPTION,
      HERO_COMBAT_ARMOUR_CLASS_FLATFOOTED_NAME_DESCRIPTION,
      HERO_COMBAT_SPEED_NAME_DESCRIPTION,
      HERO_COMBAT_INITIATIVE_NAME_DESCRIPTION,
      HERO_COMBAT_ATTACK_NAME_DESCRIPTION,
      HERO_COMBAT_ATTACK_MELEE_NAME_DESCRIPTION,
      HERO_COMBAT_ATTACK_RANGED_NAME_DESCRIPTION,
      HERO_COMBAT_GRAPPLE_NAME_DESCRIPTION,
      HERO_COMBAT_SPELL_RESISTANCE_NAME_DESCRIPTION};

  private static final int ID_COMBAT_VALUES[] = {
      R.id.fragment_player_character_statistics_combat_hit_points,
      R.id.fragment_player_character_statistics_combat_damage_reduction,
      R.id.fragment_player_character_statistics_combat_armour_class,
      R.id.fragment_player_character_statistics_combat_armour_class_touch,
      R.id.fragment_player_character_statistics_combat_armour_class_flatfooted,
      R.id.fragment_player_character_statistics_combat_speed,
      R.id.fragment_player_character_statistics_combat_initiative,
      R.id.fragment_player_character_statistics_combat_attack,
      R.id.fragment_player_character_statistics_combat_attack_melee,
      R.id.fragment_player_character_statistics_combat_attack_ranged,
      R.id.fragment_player_character_statistics_combat_grapple,
      R.id.fragment_player_character_statistics_combat_spell_resistance};

  private static final int ID_COMBAT_VALUES_SPECIFIC[][] = {
      {R.id.fragment_player_character_statistics_combat_hit_points_description,
          R.id.fragment_player_character_statistics_combat_hit_points_value},
      {R.id.fragment_number_value_with_description_box_description, R.id.fragment_number_value_with_description_box_value},
      {R.id.fragment_number_value_with_description_box_description, R.id.fragment_number_value_with_description_box_value},
      {R.id.fragment_number_value_with_description_box_description, R.id.fragment_number_value_with_description_box_value},
      {R.id.fragment_number_value_with_description_box_description, R.id.fragment_number_value_with_description_box_value},
      {R.id.fragment_number_value_with_description_box_description, R.id.fragment_number_value_with_description_box_value},
      {R.id.fragment_number_value_with_description_rollable_box_description,
          R.id.fragment_number_value_with_description_rollable_box_value},
      {R.id.fragment_number_value_with_description_rollable_box_description,
          R.id.fragment_number_value_with_description_rollable_box_value},
      {R.id.fragment_number_value_with_description_rollable_box_description,
          R.id.fragment_number_value_with_description_rollable_box_value},
      {R.id.fragment_number_value_with_description_rollable_box_description,
          R.id.fragment_number_value_with_description_rollable_box_value},
      {R.id.fragment_number_value_with_description_rollable_box_description,
          R.id.fragment_number_value_with_description_rollable_box_value},
      {R.id.fragment_number_value_with_description_box_description, R.id.fragment_number_value_with_description_box_value}};

  private static final String HERO_ABILITY_SCORES_STR_NAME_DESCRIPTION = "hero_ability_scores_str_name_description";
  private static final String HERO_ABILITY_SCORES_DEX_NAME_DESCRIPTION = "hero_ability_scores_dex_name_description";
  private static final String HERO_ABILITY_SCORES_CON_NAME_DESCRIPTION = "hero_ability_scores_con_name_description";
  private static final String HERO_ABILITY_SCORES_INT_NAME_DESCRIPTION = "hero_ability_scores_int_name_description";
  private static final String HERO_ABILITY_SCORES_WIS_NAME_DESCRIPTION = "hero_ability_scores_wis_name_description";
  private static final String HERO_ABILITY_SCORES_CHA_NAME_DESCRIPTION = "hero_ability_scores_cha_name_description";

  private static final String ABILITY_SCORES_DESCRIPTIONS[] = {
      HERO_ABILITY_SCORES_STR_NAME_DESCRIPTION,
      HERO_ABILITY_SCORES_DEX_NAME_DESCRIPTION,
      HERO_ABILITY_SCORES_CON_NAME_DESCRIPTION,
      HERO_ABILITY_SCORES_INT_NAME_DESCRIPTION,
      HERO_ABILITY_SCORES_WIS_NAME_DESCRIPTION,
      HERO_ABILITY_SCORES_CHA_NAME_DESCRIPTION};

  private static final int ID_ABILITY_SCORES_VALUES[] = {
      R.id.fragment_player_character_statistics_ability_scores_str,
      R.id.fragment_player_character_statistics_ability_scores_dex,
      R.id.fragment_player_character_statistics_ability_scores_con,
      R.id.fragment_player_character_statistics_ability_scores_int,
      R.id.fragment_player_character_statistics_ability_scores_wis,
      R.id.fragment_player_character_statistics_ability_scores_cha};

  private static final int ID_ABILITY_SCORES_VALUES_SPECIFIC[] = {
      R.id.fragment_player_character_statistics_ability_scores_score_name,
      R.id.fragment_player_character_statistics_ability_scores_score_value,
      R.id.fragment_player_character_statistics_ability_scores_score_modifier,
      R.id.fragment_player_character_statistics_ability_scores_score_temporary_value,
      R.id.fragment_player_character_statistics_ability_scores_score_temporary_modifier};

  private static final String HERO_SAVING_THROWS_FORT_NAME_DESCRIPTION = "hero_saving_throws_fort_name_description";
  private static final String HERO_SAVING_THROWS_REFL_NAME_DESCRIPTION = "hero_saving_throws_refl_name_description";
  private static final String HERO_SAVING_THROWS_WILL_NAME_DESCRIPTION = "hero_saving_throws_will_name_description";

  private static final String SAVING_THROWS_DESCRIPTIONS[] = {
      HERO_SAVING_THROWS_FORT_NAME_DESCRIPTION,
      HERO_SAVING_THROWS_REFL_NAME_DESCRIPTION,
      HERO_SAVING_THROWS_WILL_NAME_DESCRIPTION};

  private static final int ID_SAVING_THROWS_VALUES[] = {
      R.id.fragment_player_character_statistics_saving_throws_fortitude,
      R.id.fragment_player_character_statistics_saving_throws_reflex,
      R.id.fragment_player_character_statistics_saving_throws_will,};

  private static final int ID_SAVING_THROWS_VALUES_SPECIFIC[] = {
      R.id.fragment_player_character_statistics_saving_throws_throw_name,
      R.id.fragment_player_character_statistics_saving_throws_throw_value};

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
    for (int i = 0; i < ID_COMBAT_VALUES.length; i++) {
      ((TextView) rootView.findViewById(ID_COMBAT_VALUES[i]).findViewById(ID_COMBAT_VALUES_SPECIFIC[i][0]))
          .setText(translate(COMBAT_DESCRIPTIONS[i]));
      ((TextView) rootView.findViewById(ID_COMBAT_VALUES[i]).findViewById(ID_COMBAT_VALUES_SPECIFIC[i][1]))
          .setText(playerCharacterVM.getHeroCombatTextValues()[i]);
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
