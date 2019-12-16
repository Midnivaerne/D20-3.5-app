package com.aurora.core.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.Races;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesEnum;

public class DataTypeConverters {

  @TypeConverter
  public List<Races> racesListFromItemList(List<Item> list) {
    List<Races> tmp = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      tmp.add((Races) list.get(i));
    }
    return tmp;
  }

  @TypeConverter
  public PlayerCharacterWornEquipmentPlacesEnum StringToWornPlace(String wornPlace) {
    if (wornPlace != null) {
      return PlayerCharacterWornEquipmentPlacesEnum.valueOf(wornPlace);
    }
    return null;
  }

  @TypeConverter
  public String StringToWornPlace(PlayerCharacterWornEquipmentPlacesEnum wornPlace) {
    if (wornPlace != null) {
      return wornPlace.toString();
    }
    return null;
  }

}
