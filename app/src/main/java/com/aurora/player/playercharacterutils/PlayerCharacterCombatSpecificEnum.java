package com.aurora.player.playercharacterutils;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.models.userdata.HeroValues;

public enum PlayerCharacterCombatSpecificEnum implements PlayerCharacterSpecificEnumBase<PlayerCharacterCombatEnum, HeroValues> {
  NAME {
    @Override
    public int getSpecificFieldId(PlayerCharacterCombatEnum combatField) {
      return combatField.getDescriptionId();
    }

    @Override
    public String getSpecificValue(PlayerCharacterCombatEnum combatField, HeroValues heroValues) {
      return translate(combatField.toString());
    }
  },
  VALUE {
    @Override
    public int getSpecificFieldId(PlayerCharacterCombatEnum combatField) {
      return combatField.getValueId();
    }

    @Override
    public String getSpecificValue(PlayerCharacterCombatEnum combatField, HeroValues heroValues) {
      return String.valueOf(heroValues.getCombatTextValues().get(combatField));
    }
  }
}
