package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.MaterialTypes;

@Dao
public abstract class MaterialTypesDaO extends BaseDaO<MaterialTypes> {

  @Query("SELECT COUNT(*) from MaterialTypes")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM MaterialTypes")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM MaterialTypes")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM MaterialTypes")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM MaterialTypes")
  public abstract List<MaterialTypes> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM MaterialTypes")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM MaterialTypes WHERE Item_ID IN (:ids)")
  public abstract List<MaterialTypes> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM MaterialTypes WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM MaterialTypes WHERE Source == :source")
  public abstract List<MaterialTypes> getObjectsWithSource(String source);

  @Query("SELECT * FROM MaterialTypes WHERE Item_ID == :itemID")
  public abstract MaterialTypes getObjectWithId(int itemID);

  @Query("SELECT * FROM MaterialTypes WHERE Name == :name")
  public abstract MaterialTypes getObjectWithName(String name);

  @Query("DELETE FROM MaterialTypes")
  public abstract void deleteAll();
}
