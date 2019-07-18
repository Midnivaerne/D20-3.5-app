package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.usables.WeaponSpecifics;

@Dao
public abstract class WeaponSpecificsDaO extends BaseDaO<WeaponSpecifics> {

  @Query("SELECT COUNT(*) from WeaponSpecifics")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM WeaponSpecifics")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM WeaponSpecifics")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM WeaponSpecifics")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM WeaponSpecifics")
  public abstract List<WeaponSpecifics> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM WeaponSpecifics")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM WeaponSpecifics WHERE Item_ID IN (:ids)")
  public abstract List<WeaponSpecifics> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM WeaponSpecifics WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM WeaponSpecifics WHERE Source == :source")
  public abstract List<WeaponSpecifics> getObjectsWithSource(String source);

  @Query("SELECT * FROM WeaponSpecifics WHERE Item_ID == :itemID")
  public abstract WeaponSpecifics getObjectWithId(int itemID);

  @Query("SELECT * FROM WeaponSpecifics WHERE Name == :name")
  public abstract WeaponSpecifics getObjectWithName(String name);

  @Query("DELETE FROM WeaponSpecifics")
  public abstract void deleteAll();
}
