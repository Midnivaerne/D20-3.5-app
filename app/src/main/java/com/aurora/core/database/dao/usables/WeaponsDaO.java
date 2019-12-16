package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.usables.Weapons;

@Dao
public abstract class WeaponsDaO extends BaseDaO<Weapons> {

  @Query("SELECT COUNT(*) from Weapons")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Weapons")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Weapons")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Weapons")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Weapons")
  public abstract List<Weapons> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Weapons")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Weapons WHERE Item_ID IN (:ids)")
  public abstract List<Weapons> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
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
