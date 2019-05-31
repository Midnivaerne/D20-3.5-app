package com.aurora.player.playercharacterutils;

import com.aurora.core.R;

public enum PlayerCharacterAbilityScoresEnum implements PlayerCharacterEnumBase {
  HERO_ABILITY_SCORES_STR("hero_ability_scores_str_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_ability_scores_str;
    }
  },
  HERO_ABILITY_SCORES_DEX("hero_ability_scores_dex_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_ability_scores_dex;
    }
  },
  HERO_ABILITY_SCORES_CON("hero_ability_scores_con_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_ability_scores_con;
    }
  },
  HERO_ABILITY_SCORES_INT("hero_ability_scores_int_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_ability_scores_int;
    }
  },
  HERO_ABILITY_SCORES_WIS("hero_ability_scores_wis_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_ability_scores_wis;
    }
  },
  HERO_ABILITY_SCORES_CHA("hero_ability_scores_cha_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_ability_scores_cha;
    }
  };

  String description;

  PlayerCharacterAbilityScoresEnum(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }

}
