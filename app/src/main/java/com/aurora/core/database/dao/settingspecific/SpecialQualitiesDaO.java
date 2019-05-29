package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.SpecialQualities;

@Dao
public abstract class SpecialQualitiesDaO extends BaseDaO<SpecialQualities> {

  @Query("SELECT COUNT(*) from SpecialQualities")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM SpecialQualities")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM SpecialQualities")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM SpecialQualities")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM SpecialQualities")
  public abstract List<SpecialQualities> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM SpecialQualities")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

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
