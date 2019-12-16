package com.aurora.player.playercharacterutils;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.R;
import com.aurora.core.database.models.userdata.HeroValues;

public enum PlayerCharacterAbilityScoresSpecificEnum implements
    PlayerCharacterSpecificEnumBase<PlayerCharacterAbilityScoresEnum, HeroValues> {
  NAME {
    @Override
    public int getSpecificFieldId(PlayerCharacterAbilityScoresEnum abilityScore) {
      return R.id.fragment_player_character_statistics_ability_scores_score_name;
    }

    @Override
    public String getSpecificValue(PlayerCharacterAbilityScoresEnum abilityScore, HeroValues heroValues) {
      return translate(abilityScore.toString());
    }
  },
  VALUE {
    @Override
    public int getSpecificFieldId(PlayerCharacterAbilityScoresEnum abilityScore) {
      return R.id.fragment_player_character_statistics_ability_scores_score_value;
    }

    @Override
    public String getSpecificValue(PlayerCharacterAbilityScoresEnum abilityScore, HeroValues heroValues) {
      return String.valueOf(heroValues.getAbilityScoreValues().get(abilityScore));
    }
  },
  MODIFIER {
    @Override
    public int getSpecificFieldId(PlayerCharacterAbilityScoresEnum abilityScore) {
      return R.id.fragment_player_character_statistics_ability_scores_score_modifier;
    }

    @Override
    public String getSpecificValue(PlayerCharacterAbilityScoresEnum abilityScore, HeroValues heroValues) {
      return String
          .valueOf(HeroValues.getStatisticModifier(Integer.parseInt(String.valueOf(heroValues.getAbilityScoreValues().get(abilityScore)))));
    }
  },
  TEMPORARY_VALUE {
    @Override
    public int getSpecificFieldId(PlayerCharacterAbilityScoresEnum abilityScore) {
      return R.id.fragment_player_character_statistics_ability_scores_score_temporary_value;
    }

    @Override
    public String getSpecificValue(PlayerCharacterAbilityScoresEnum abilityScore,
        HeroValues heroValues) {
      return String.valueOf(heroValues.getAbilityScoreValues().get(abilityScore));
      //todo add modifiers, don't show if the same as base values
    }
  },
  TEMPORARY_MODIFIER {
    @Override
    public int getSpecificFieldId(PlayerCharacterAbilityScoresEnum abilityScore) {
      return R.id.fragment_player_character_statistics_ability_scores_score_temporary_modifier;
    }

    @Override
    public String getSpecificValue(PlayerCharacterAbilityScoresEnum abilityScore, HeroValues heroValues) {
      return String
          .valueOf(HeroValues.getStatisticModifier(Integer.parseInt(String.valueOf(heroValues.getAbilityScoreValues().get(abilityScore)))));
      //todo add modifiers, don't show if the same as base values
    }
  }
}
