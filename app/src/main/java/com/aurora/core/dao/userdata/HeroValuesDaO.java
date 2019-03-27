package com.aurora.core.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userdata.HeroValues;

@Dao
public abstract class HeroValuesDaO implements BaseDaO<HeroValues> {

  @Query("SELECT COUNT(*) from HeroValues")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroValues")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroValues")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroValues")
  public abstract List<String> getAllSources();

  @Transaction
  public List<HeroValues> getAllObjectsAsMergedObjectItem() {
    ArrayList<HeroValues> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroValues")
  public abstract List<HeroValues> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroValues")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<HeroValues> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<HeroValues> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroValues WHERE Item_ID IN (:ids)")
  public abstract List<HeroValues> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroValues WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroValues WHERE Source == :source")
  public abstract List<HeroValues> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroValues WHERE Item_ID == :itemID")
  public abstract HeroValues getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroValues WHERE Name == :name")
  public abstract HeroValues getObjectWithName(String name);

  @Query("DELETE FROM HeroValues")
  public abstract void deleteAll();
}
