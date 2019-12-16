package com.aurora.player.playercharacterutils;

import com.aurora.core.database.models.helpers.Item;

public interface PlayerCharacterSpecificEnumBase<T extends PlayerCharacterEnumBase, V extends Item> extends PlayerCharacterSuperBase {

  int getSpecificFieldId(T enumBase);

  String getSpecificValue(T enumBase, V heroValues);
}
