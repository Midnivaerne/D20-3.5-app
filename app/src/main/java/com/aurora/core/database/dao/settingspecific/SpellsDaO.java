package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.Spells;

@Dao
public abstract class SpellsDaO extends BaseDaO<Spells> {

  @Query("SELECT COUNT(*) from Spells")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Spells")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Spells")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Spells")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Spells")
  public abstract List<Spells> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Spells")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Spells WHERE Item_ID IN (:ids)")
  public abstract List<Spells> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Spells WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Spells WHERE Source == :source")
  public abstract List<Spells> getObjectsWithSource(String source);

  @Query("SELECT * FROM Spells WHERE Item_ID == :itemID")
  public abstract Spells getObjectWithId(int itemID);

  @Query("SELECT * FROM Spells WHERE Name == :name")
  public abstract Spells getObjectWithName(String name);

  @Query("DELETE FROM Spells")
  public abstract void deleteAll();
}
