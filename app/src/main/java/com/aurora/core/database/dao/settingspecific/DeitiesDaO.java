package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Deities;

@Dao
public abstract class DeitiesDaO extends BaseDaO<Deities> {

  @Query("SELECT COUNT(*) from Deities")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Deities")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Deities")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Deities")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Deities")
  public abstract List<Deities> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Deities")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

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
