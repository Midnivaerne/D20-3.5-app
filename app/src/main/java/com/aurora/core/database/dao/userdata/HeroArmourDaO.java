package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userdata.HeroArmour;

@Dao
public abstract class HeroArmourDaO extends BaseDaO<HeroArmour> {

  @Query("SELECT COUNT(*) from HeroArmour")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroArmour")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroArmour")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroArmour")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroArmour")
  public abstract List<HeroArmour> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroArmour")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroArmour WHERE Item_ID IN (:ids)")
  public abstract List<HeroArmour> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroArmour WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroArmour WHERE Source == :source")
  public abstract List<HeroArmour> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroArmour WHERE Item_ID == :itemID")
  public abstract HeroArmour getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroArmour WHERE Name == :name")
  public abstract HeroArmour getObjectWithName(String name);

  @Query("SELECT * FROM HeroArmour WHERE Parent_Hero_Id == :parentHeroId")
  public abstract List<HeroArmour> getArmourWithParentHero(Integer parentHeroId);

  @Query("DELETE FROM HeroArmour")
  public abstract void deleteAll();
}
