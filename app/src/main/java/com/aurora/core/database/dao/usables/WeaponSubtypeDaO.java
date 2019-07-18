package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.usables.WeaponSubtype;

@Dao
public abstract class WeaponSubtypeDaO extends BaseDaO<WeaponSubtype> {

  @Query("SELECT COUNT(*) from WeaponSubtype")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM WeaponSubtype")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM WeaponSubtype")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM WeaponSubtype")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM WeaponSubtype")
  public abstract List<WeaponSubtype> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM WeaponSubtype")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM WeaponSubtype WHERE Item_ID IN (:ids)")
  public abstract List<WeaponSubtype> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM WeaponSubtype WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM WeaponSubtype WHERE Source == :source")
  public abstract List<WeaponSubtype> getObjectsWithSource(String source);

  @Query("SELECT * FROM WeaponSubtype WHERE Item_ID == :itemID")
  public abstract WeaponSubtype getObjectWithId(int itemID);

  @Query("SELECT * FROM WeaponSubtype WHERE Name == :name")
  public abstract WeaponSubtype getObjectWithName(String name);

  @Query("DELETE FROM WeaponSubtype")
  public abstract void deleteAll();
}
