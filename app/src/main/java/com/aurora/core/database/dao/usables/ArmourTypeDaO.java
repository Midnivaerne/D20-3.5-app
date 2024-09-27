package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.usables.ArmourType;

@Dao
public abstract class ArmourTypeDaO extends BaseDaO<ArmourType> {

  @Query("SELECT COUNT(*) from ArmourType")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM ArmourType")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM ArmourType")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM ArmourType")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM ArmourType")
  public abstract List<ArmourType> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM ArmourType")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM ArmourType WHERE Item_ID IN (:ids)")
  public abstract List<ArmourType> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM ArmourType WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM ArmourType WHERE Source == :source")
  public abstract List<ArmourType> getObjectsWithSource(String source);

  @Query("SELECT * FROM ArmourType WHERE Item_ID == :itemID")
  public abstract ArmourType getObjectWithId(int itemID);

  @Query("SELECT * FROM ArmourType WHERE Name == :name")
  public abstract ArmourType getObjectWithName(String name);

  @Query("DELETE FROM ArmourType")
  public abstract void deleteAll();
}
