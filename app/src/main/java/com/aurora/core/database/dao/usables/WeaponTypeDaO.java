package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.usables.WeaponType;

@Dao
public abstract class WeaponTypeDaO extends BaseDaO<WeaponType> {

  @Query("SELECT COUNT(*) from WeaponType")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM WeaponType")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM WeaponType")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM WeaponType")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM WeaponType")
  public abstract List<WeaponType> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM WeaponType")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM WeaponType WHERE Item_ID IN (:ids)")
  public abstract List<WeaponType> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM WeaponType WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM WeaponType WHERE Source == :source")
  public abstract List<WeaponType> getObjectsWithSource(String source);

  @Query("SELECT * FROM WeaponType WHERE Item_ID == :itemID")
  public abstract WeaponType getObjectWithId(int itemID);

  @Query("SELECT * FROM WeaponType WHERE Name == :name")
  public abstract WeaponType getObjectWithName(String name);

  @Query("DELETE FROM WeaponType")
  public abstract void deleteAll();
}
