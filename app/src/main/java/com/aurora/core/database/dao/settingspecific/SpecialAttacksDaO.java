package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.SpecialAttacks;

@Dao
public abstract class SpecialAttacksDaO extends BaseDaO<SpecialAttacks> {

  @Query("SELECT COUNT(*) from SpecialAttacks")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM SpecialAttacks")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM SpecialAttacks")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM SpecialAttacks")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM SpecialAttacks")
  public abstract List<SpecialAttacks> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM SpecialAttacks")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM SpecialAttacks WHERE Item_ID IN (:ids)")
  public abstract List<SpecialAttacks> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM SpecialAttacks WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM SpecialAttacks WHERE Source == :source")
  public abstract List<SpecialAttacks> getObjectsWithSource(String source);

  @Query("SELECT * FROM SpecialAttacks WHERE Item_ID == :itemID")
  public abstract SpecialAttacks getObjectWithId(int itemID);

  @Query("SELECT * FROM SpecialAttacks WHERE Name == :name")
  public abstract SpecialAttacks getObjectWithName(String name);

  @Query("DELETE FROM SpecialAttacks")
  public abstract void deleteAll();
}
