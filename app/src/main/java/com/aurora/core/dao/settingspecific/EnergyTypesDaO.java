package com.aurora.core.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.EnergyTypes;

@Dao
public abstract class EnergyTypesDaO implements BaseDaO<EnergyTypes> {

  @Query("SELECT COUNT(*) from EnergyTypes")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM EnergyTypes")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM EnergyTypes")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM EnergyTypes")
  public abstract List<String> getAllSources();

  @Transaction
  public List<EnergyTypes> getAllObjectsAsMergedObjectItem() {
    ArrayList<EnergyTypes> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM EnergyTypes")
  public abstract List<EnergyTypes> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM EnergyTypes")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<EnergyTypes> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<EnergyTypes> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM EnergyTypes WHERE Item_ID IN (:ids)")
  public abstract List<EnergyTypes> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM EnergyTypes WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM EnergyTypes WHERE Source == :source")
  public abstract List<EnergyTypes> getObjectsWithSource(String source);

  @Query("SELECT * FROM EnergyTypes WHERE Item_ID == :itemID")
  public abstract EnergyTypes getObjectWithId(int itemID);

  @Query("SELECT * FROM EnergyTypes WHERE Name == :name")
  public abstract EnergyTypes getObjectWithName(String name);

  @Query("DELETE FROM EnergyTypes")
  public abstract void deleteAll();
}
