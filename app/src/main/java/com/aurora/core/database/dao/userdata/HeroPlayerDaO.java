package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userdata.HeroPlayer;

@Dao
public abstract class HeroPlayerDaO extends BaseDaO<HeroPlayer> {

  @Query("SELECT COUNT(*) from HeroPlayer")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroPlayer")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroPlayer")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroPlayer")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroPlayer")
  public abstract List<HeroPlayer> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroPlayer")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroPlayer WHERE Item_ID IN (:ids)")
  public abstract List<HeroPlayer> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroPlayer WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroPlayer WHERE Source == :source")
  public abstract List<HeroPlayer> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroPlayer WHERE Item_ID == :itemID")
  public abstract HeroPlayer getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroPlayer WHERE Name == :name")
  public abstract HeroPlayer getObjectWithName(String name);

  @Query("DELETE FROM HeroPlayer")
  public abstract void deleteAll();
}
