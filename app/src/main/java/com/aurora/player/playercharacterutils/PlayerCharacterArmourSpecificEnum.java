package com.aurora.player.playercharacterutils;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.models.userdata.HeroArmour;

public enum PlayerCharacterArmourSpecificEnum implements PlayerCharacterSpecificEnumBase<PlayerCharacterArmourEnum, HeroArmour> {

  NAME {
    @Override
    public int getSpecificFieldId(PlayerCharacterArmourEnum armourField) {
      return armourField.getDescriptionId();
    }

    @Override
    public String getSpecificValue(PlayerCharacterArmourEnum armourField, HeroArmour heroArmour) {
      return translate(armourField.toString());
    }
  },
  VALUE {
    @Override
    public int getSpecificFieldId(PlayerCharacterArmourEnum armourField) {
      return armourField.getValueId();
    }

    @Override
    public String getSpecificValue(PlayerCharacterArmourEnum armourField, HeroArmour heroArmour) {
      return String.valueOf(heroArmour.getArmourValues().get(armourField));
    }
  }
}
