package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.SpecialQualities;

@Dao
public abstract class SpecialQualitiesDaO implements BaseDaO<SpecialQualities> {

  @Query("SELECT COUNT(*) from SpecialQualities")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM SpecialQualities")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM SpecialQualities")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM SpecialQualities")
  public abstract List<String> getAllSources();

  @Transaction
  public List<SpecialQualities> getAllObjectsAsMergedObjectItem() {
    ArrayList<SpecialQualities> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM SpecialQualities")
  public abstract List<SpecialQualities> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM SpecialQualities")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<SpecialQualities> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<SpecialQualities> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM SpecialQualities WHERE Item_ID IN (:ids)")
  public abstract List<SpecialQualities> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM SpecialQualities WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM SpecialQualities WHERE Source == :source")
  public abstract List<SpecialQualities> getObjectsWithSource(String source);

  @Query("SELECT * FROM SpecialQualities WHERE Item_ID == :itemID")
  public abstract SpecialQualities getObjectWithId(int itemID);

  @Query("SELECT * FROM SpecialQualities WHERE Name == :name")
  public abstract SpecialQualities getObjectWithName(String name);

  @Query("DELETE FROM SpecialQualities")
  public abstract void deleteAll();
}
