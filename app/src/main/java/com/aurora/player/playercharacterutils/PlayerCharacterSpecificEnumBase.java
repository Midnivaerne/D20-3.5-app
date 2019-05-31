package com.aurora.player.playercharacterutils;

import com.aurora.core.models.userdata.HeroValues;

public interface PlayerCharacterSpecificEnumBase<T extends PlayerCharacterEnumBase> extends PlayerCharacterSuperBase {

  int getSpecificFieldId(T enumBase);

  String getSpecificValue(T abilityScore, HeroValues heroValues);
}
