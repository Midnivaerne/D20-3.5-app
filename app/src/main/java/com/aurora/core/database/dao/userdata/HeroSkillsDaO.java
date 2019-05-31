package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userdata.HeroSkills;

@Dao
public abstract class HeroSkillsDaO extends BaseDaO<HeroSkills> {

  @Query("SELECT COUNT(*) from HeroSkills")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroSkills")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroSkills")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroSkills")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroSkills")
  public abstract List<HeroSkills> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroSkills")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroSkills WHERE Item_ID IN (:ids)")
  public abstract List<HeroSkills> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroSkills WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroSkills WHERE Source == :source")
  public abstract List<HeroSkills> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroSkills WHERE Item_ID == :itemID")
  public abstract HeroSkills getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroSkills WHERE Name == :name")
  public abstract HeroSkills getObjectWithName(String name);

  @Query("DELETE FROM HeroSkills")
  public abstract void deleteAll();

}
