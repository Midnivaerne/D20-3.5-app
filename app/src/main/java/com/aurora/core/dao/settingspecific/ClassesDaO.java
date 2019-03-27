package com.aurora.core.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Classes;

@Dao
public abstract class ClassesDaO implements BaseDaO<Classes> {

  @Query("SELECT COUNT(*) from Classes")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Classes")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Classes")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Classes")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Classes> getAllObjectsAsMergedObjectItem() {
    ArrayList<Classes> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Classes")
  public abstract List<Classes> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Classes")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Classes> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<Classes> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Classes WHERE Item_ID IN (:ids)")
  public abstract List<Classes> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Classes WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Classes WHERE Source == :source")
  public abstract List<Classes> getObjectsWithSource(String source);

  @Query("SELECT * FROM Classes WHERE Item_ID == :itemID")
  public abstract Classes getObjectWithId(int itemID);

  @Query("SELECT * FROM Classes WHERE Name == :name")
  public abstract Classes getObjectWithName(String name);

  @Query("DELETE FROM Classes")
  public abstract void deleteAll();
}
