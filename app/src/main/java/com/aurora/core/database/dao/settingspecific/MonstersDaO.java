package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Monsters;

@Dao
public abstract class MonstersDaO implements BaseDaO<Monsters> {

  @Query("SELECT COUNT(*) from Monsters")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Monsters")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Monsters")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Monsters")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Monsters> getAllObjectsAsMergedObjectItem() {
    ArrayList<Monsters> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Monsters")
  public abstract List<Monsters> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Monsters")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Monsters> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<Monsters> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Monsters WHERE Item_ID IN (:ids)")
  public abstract List<Monsters> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Monsters WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Monsters WHERE Source == :source")
  public abstract List<Monsters> getObjectsWithSource(String source);

  @Query("SELECT * FROM Monsters WHERE Item_ID == :itemID")
  public abstract Monsters getObjectWithId(int itemID);

  @Query("SELECT * FROM Monsters WHERE Name == :name")
  public abstract Monsters getObjectWithName(String name);

  @Query("DELETE FROM Monsters")
  public abstract void deleteAll();
}
