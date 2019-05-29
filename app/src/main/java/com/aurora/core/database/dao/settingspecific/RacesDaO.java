package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Races;

@Dao
public abstract class RacesDaO extends BaseDaO<Races> {

  @Query("SELECT COUNT(*) from Races")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Races")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Races")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Races")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Races")
  public abstract List<Races> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Races")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Races WHERE Item_ID IN (:ids)")
  public abstract List<Races> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Races WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Races WHERE Source == :source")
  public abstract List<Races> getObjectsWithSource(String source);

  @Query("SELECT * FROM Races WHERE Item_ID == :itemID")
  public abstract Races getObjectWithId(int itemID);

  @Query("SELECT * FROM Races WHERE Name == :name")
  public abstract Races getObjectWithName(String name);

  @Query("DELETE FROM Races")
  public abstract void deleteAll();
}
