package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.SpecialAttacks;

@Dao
public abstract class SpecialAttacksDaO implements BaseDaO<SpecialAttacks> {

  @Query("SELECT COUNT(*) from SpecialAttacks")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM SpecialAttacks")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM SpecialAttacks")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM SpecialAttacks")
  public abstract List<String> getAllSources();

  @Transaction
  public List<SpecialAttacks> getAllObjectsAsMergedObjectItem() {
    ArrayList<SpecialAttacks> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM SpecialAttacks")
  public abstract List<SpecialAttacks> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM SpecialAttacks")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<SpecialAttacks> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<SpecialAttacks> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM SpecialAttacks WHERE Item_ID IN (:ids)")
  public abstract List<SpecialAttacks> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM SpecialAttacks WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM SpecialAttacks WHERE Source == :source")
  public abstract List<SpecialAttacks> getObjectsWithSource(String source);

  @Query("SELECT * FROM SpecialAttacks WHERE Item_ID == :itemID")
  public abstract SpecialAttacks getObjectWithId(int itemID);

  @Query("SELECT * FROM SpecialAttacks WHERE Name == :name")
  public abstract SpecialAttacks getObjectWithName(String name);

  @Query("DELETE FROM SpecialAttacks")
  public abstract void deleteAll();
}
