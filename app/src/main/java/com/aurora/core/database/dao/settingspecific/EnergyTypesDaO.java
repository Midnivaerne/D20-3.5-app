package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.EnergyTypes;

@Dao
public abstract class EnergyTypesDaO extends BaseDaO<EnergyTypes> {

  @Query("SELECT COUNT(*) from EnergyTypes")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM EnergyTypes")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM EnergyTypes")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM EnergyTypes")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM EnergyTypes")
  public abstract List<EnergyTypes> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM EnergyTypes")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM EnergyTypes WHERE Item_ID IN (:ids)")
  public abstract List<EnergyTypes> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM EnergyTypes WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM EnergyTypes WHERE Source == :source")
  public abstract List<EnergyTypes> getObjectsWithSource(String source);

  @Query("SELECT * FROM EnergyTypes WHERE Item_ID == :itemID")
  public abstract EnergyTypes getObjectWithId(int itemID);

  @Query("SELECT * FROM EnergyTypes WHERE Name == :name")
  public abstract EnergyTypes getObjectWithName(String name);

  @Query("DELETE FROM EnergyTypes")
  public abstract void deleteAll();
}
