package com.aurora.player.playercharacterutils;

import com.aurora.core.R;

public enum PlayerCharacterSavingThrowsEnum implements PlayerCharacterEnumBase {
  HERO_SAVING_THROWS_FORTITUDE("hero_saving_throws_fort_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_saving_throws_fortitude;
    }
  },
  HERO_SAVING_THROWS_REFLEX("hero_saving_throws_refl_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_saving_throws_reflex;
    }
  },
  HERO_SAVING_THROWS_WILL("hero_saving_throws_will_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_saving_throws_will;
    }
  };

  String description;

  PlayerCharacterSavingThrowsEnum(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }

}
