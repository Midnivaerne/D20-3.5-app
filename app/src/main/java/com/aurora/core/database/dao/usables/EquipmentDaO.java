package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.usables.Equipment;

@Dao
public abstract class EquipmentDaO extends BaseDaO<Equipment> {

  @Query("SELECT COUNT(*) from Equipment")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Equipment")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Equipment")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Equipment")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Equipment")
  public abstract List<Equipment> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Equipment")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Equipment WHERE Item_ID IN (:ids)")
  public abstract List<Equipment> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Equipment WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Equipment WHERE Source == :source")
  public abstract List<Equipment> getObjectsWithSource(String source);

  @Query("SELECT * FROM Equipment WHERE Item_ID == :itemID")
  public abstract Equipment getObjectWithId(int itemID);

  @Query("SELECT * FROM Equipment WHERE Name == :name")
  public abstract Equipment getObjectWithName(String name);

  @Query("DELETE FROM Equipment")
  public abstract void deleteAll();
}
