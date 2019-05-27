package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userdata.HeroPlayer;

@Dao
public abstract class HeroPlayerDaO implements BaseDaO<HeroPlayer> {

  @Query("SELECT COUNT(*) from HeroPlayer")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroPlayer")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroPlayer")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroPlayer")
  public abstract List<String> getAllSources();

  @Transaction
  public List<HeroPlayer> getAllObjectsAsMergedObjectItem() {
    ArrayList<HeroPlayer> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroPlayer")
  public abstract List<HeroPlayer> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroPlayer")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<HeroPlayer> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<HeroPlayer> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroPlayer WHERE Item_ID IN (:ids)")
  public abstract List<HeroPlayer> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroPlayer WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroPlayer WHERE Source == :source")
  public abstract List<HeroPlayer> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroPlayer WHERE Item_ID == :itemID")
  public abstract HeroPlayer getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroPlayer WHERE Name == :name")
  public abstract HeroPlayer getObjectWithName(String name);

  @Query("DELETE FROM HeroPlayer")
  public abstract void deleteAll();
}
