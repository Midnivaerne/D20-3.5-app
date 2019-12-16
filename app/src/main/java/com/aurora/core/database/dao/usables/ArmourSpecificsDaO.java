package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.usables.ArmourSpecifics;

@Dao
public abstract class ArmourSpecificsDaO extends BaseDaO<ArmourSpecifics> {

  @Query("SELECT COUNT(*) from ArmourSpecifics")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM ArmourSpecifics")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM ArmourSpecifics")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM ArmourSpecifics")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM ArmourSpecifics")
  public abstract List<ArmourSpecifics> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM ArmourSpecifics")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM ArmourSpecifics WHERE Item_ID IN (:ids)")
  public abstract List<ArmourSpecifics> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM ArmourSpecifics WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM ArmourSpecifics WHERE Source == :source")
  public abstract List<ArmourSpecifics> getObjectsWithSource(String source);

  @Query("SELECT * FROM ArmourSpecifics WHERE Item_ID == :itemID")
  public abstract ArmourSpecifics getObjectWithId(int itemID);

  @Query("SELECT * FROM ArmourSpecifics WHERE Name == :name")
  public abstract ArmourSpecifics getObjectWithName(String name);

  @Query("DELETE FROM ArmourSpecifics")
  public abstract void deleteAll();
}
