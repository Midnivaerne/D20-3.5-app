package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.HeroNpc;

@Dao
public abstract class HeroNpcDaO extends BaseDaO<HeroNpc> {

  @Query("SELECT COUNT(*) from HeroNpc")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroNpc")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroNpc")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroNpc")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroNpc")
  public abstract List<HeroNpc> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroNpc")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroNpc WHERE Item_ID IN (:ids)")
  public abstract List<HeroNpc> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM HeroNpc WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroNpc WHERE Source == :source")
  public abstract List<HeroNpc> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroNpc WHERE Item_ID == :itemID")
  public abstract HeroNpc getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroNpc WHERE Name == :name")
  public abstract HeroNpc getObjectWithName(String name);

  @Query("DELETE FROM HeroNpc")
  public abstract void deleteAll();
}
