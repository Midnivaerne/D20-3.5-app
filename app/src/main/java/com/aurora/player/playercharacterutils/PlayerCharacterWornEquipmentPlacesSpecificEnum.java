package com.aurora.player.playercharacterutils;

import static com.aurora.core.database.TranslationsHolder.translate;

import com.aurora.core.database.models.userdata.HeroEquipment;

public enum PlayerCharacterWornEquipmentPlacesSpecificEnum implements
    PlayerCharacterSpecificEnumBase<PlayerCharacterWornEquipmentPlacesEnum, HeroEquipment> {
  NAME {
    @Override
    public int getSpecificFieldId(PlayerCharacterWornEquipmentPlacesEnum wornPlace) {
      return wornPlace.getNameId();
    }

    @Override
    public String getSpecificValue(PlayerCharacterWornEquipmentPlacesEnum wornPlace, HeroEquipment heroEquipment) {
      return String.valueOf(heroEquipment.getName());
    }
  },
  DESCRIPTION {
    @Override
    public int getSpecificFieldId(PlayerCharacterWornEquipmentPlacesEnum wornPlace) {
      return wornPlace.getDescriptionId();
    }

    @Override
    public String getSpecificValue(PlayerCharacterWornEquipmentPlacesEnum wornPlace, HeroEquipment heroEquipment) {
      return translate(wornPlace.toString());
    }
  };
}
