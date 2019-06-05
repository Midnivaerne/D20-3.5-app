package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Monsters;

@Dao
public abstract class MonstersDaO extends BaseDaO<Monsters> {

  @Query("SELECT COUNT(*) from Monsters")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Monsters")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Monsters")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Monsters")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Monsters")
  public abstract List<Monsters> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Monsters")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

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
