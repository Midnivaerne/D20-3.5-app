package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.usables.ArmourSubtype;

@Dao
public abstract class ArmourSubtypeDaO extends BaseDaO<ArmourSubtype> {

  @Query("SELECT COUNT(*) from ArmourSubtype")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM ArmourSubtype")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM ArmourSubtype")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM ArmourSubtype")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM ArmourSubtype")
  public abstract List<ArmourSubtype> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM ArmourSubtype")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM ArmourSubtype WHERE Item_ID IN (:ids)")
  public abstract List<ArmourSubtype> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM ArmourSubtype WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM ArmourSubtype WHERE Source == :source")
  public abstract List<ArmourSubtype> getObjectsWithSource(String source);

  @Query("SELECT * FROM ArmourSubtype WHERE Item_ID == :itemID")
  public abstract ArmourSubtype getObjectWithId(int itemID);

  @Query("SELECT * FROM ArmourSubtype WHERE Name == :name")
  public abstract ArmourSubtype getObjectWithName(String name);

  @Query("DELETE FROM ArmourSubtype")
  public abstract void deleteAll();
}
