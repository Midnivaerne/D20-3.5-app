package com.aurora.core.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Races;
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
    return PlayerCharacterWornEquipmentPlacesEnum.valueOf(wornPlace);
  }

  @TypeConverter
  public String StringToWornPlace(PlayerCharacterWornEquipmentPlacesEnum wornPlace) {
    return wornPlace.toString();
  }

}
