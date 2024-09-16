package com.aurora.player.playercharacterutils;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.R;
import com.aurora.core.database.models.userdata.HeroValues;

public enum PlayerCharacterSavingThrowsSpecificEnum implements
    PlayerCharacterSpecificEnumBase<PlayerCharacterSavingThrowsEnum, HeroValues> {
  NAME {
    @Override
    public int getSpecificFieldId(PlayerCharacterSavingThrowsEnum savingThrow) {
      return R.id.fragment_player_character_statistics_saving_throws_throw_name;
    }

    @Override
    public String getSpecificValue(PlayerCharacterSavingThrowsEnum savingThrow, HeroValues heroValues) {
      return translate(savingThrow.toString());
    }
  },
  VALUE {
    @Override
    public int getSpecificFieldId(PlayerCharacterSavingThrowsEnum savingThrow) {
      return R.id.fragment_player_character_statistics_saving_throws_throw_value;
    }

    @Override
    public String getSpecificValue(PlayerCharacterSavingThrowsEnum savingThrow, HeroValues heroValues) {
      return String.valueOf(heroValues.getSavingThrowsValues().get(savingThrow));
    }
  }
}
