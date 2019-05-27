package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.usables.Weapons;

@Dao
public abstract class WeaponsDaO implements BaseDaO<Weapons> {

  @Query("SELECT COUNT(*) from Weapons")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Weapons")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Weapons")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Weapons")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Weapons> getAllObjectsAsMergedObjectItem() {
    ArrayList<Weapons> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Weapons")
  public abstract List<Weapons> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Weapons")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Weapons> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<Weapons> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Weapons WHERE Item_ID IN (:ids)")
  public abstract List<Weapons> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Weapons WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Weapons WHERE Source == :source")
  public abstract List<Weapons> getObjectsWithSource(String source);

  @Query("SELECT * FROM Weapons WHERE Item_ID == :itemID")
  public abstract Weapons getObjectWithId(int itemID);

  @Query("SELECT * FROM Weapons WHERE Name == :name")
  public abstract Weapons getObjectWithName(String name);

  @Query("DELETE FROM Weapons")
  public abstract void deleteAll();
}
