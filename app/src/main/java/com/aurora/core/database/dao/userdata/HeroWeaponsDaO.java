package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userdata.HeroWeapons;

@Dao
public abstract class HeroWeaponsDaO extends BaseDaO<HeroWeapons> {

  @Query("SELECT COUNT(*) from HeroWeapons")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroWeapons")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroWeapons")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroWeapons")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroWeapons")
  public abstract List<HeroWeapons> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroWeapons")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroWeapons WHERE Item_ID IN (:ids)")
  public abstract List<HeroWeapons> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroWeapons WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroWeapons WHERE Source == :source")
  public abstract List<HeroWeapons> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroWeapons WHERE Item_ID == :itemID")
  public abstract HeroWeapons getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroWeapons WHERE Name == :name")
  public abstract HeroWeapons getObjectWithName(String name);

  @Query("SELECT * FROM HeroWeapons WHERE Parent_Hero_Id == :parentHeroId")
  public abstract List<HeroWeapons> getWeaponsWithParentHero(Integer parentHeroId);

  @Query("DELETE FROM HeroWeapons")
  public abstract void deleteAll();
}
