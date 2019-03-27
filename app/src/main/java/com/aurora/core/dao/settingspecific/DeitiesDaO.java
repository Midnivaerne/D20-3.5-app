package com.aurora.core.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Deities;

@Dao
public abstract class DeitiesDaO implements BaseDaO<Deities> {

  @Query("SELECT COUNT(*) from Deities")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Deities")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Deities")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Deities")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Deities> getAllObjectsAsMergedObjectItem() {
    ArrayList<Deities> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Deities")
  public abstract List<Deities> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Deities")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Deities> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<Deities> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Deities WHERE Item_ID IN (:ids)")
  public abstract List<Deities> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Deities WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Deities WHERE Source == :source")
  public abstract List<Deities> getObjectsWithSource(String source);

  @Query("SELECT * FROM Deities WHERE Item_ID == :itemID")
  public abstract Deities getObjectWithId(int itemID);

  @Query("SELECT * FROM Deities WHERE Name == :name")
  public abstract Deities getObjectWithName(String name);

  @Query("DELETE FROM Deities")
  public abstract void deleteAll();
}
