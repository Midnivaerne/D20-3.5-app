package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.userdata.HeroDescription;

@Dao
public abstract class HeroDescriptionDaO extends BaseDaO<HeroDescription> {

  @Query("SELECT COUNT(*) from HeroDescription")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroDescription")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroDescription")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroDescription")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroDescription")
  public abstract List<HeroDescription> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroDescription")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroDescription WHERE Item_ID IN (:ids)")
  public abstract List<HeroDescription> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroDescription WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroDescription WHERE Source == :source")
  public abstract List<HeroDescription> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroDescription WHERE Item_ID == :itemID")
  public abstract HeroDescription getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroDescription WHERE Name == :name")
  public abstract HeroDescription getObjectWithName(String name);

  @Query("DELETE FROM HeroDescription")
  public abstract void deleteAll();
}
