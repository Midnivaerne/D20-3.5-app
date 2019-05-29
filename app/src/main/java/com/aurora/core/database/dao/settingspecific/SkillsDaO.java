package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Skills;

@Dao
public abstract class SkillsDaO extends BaseDaO<Skills> {

  @Query("SELECT COUNT(*) from Skills")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Skills")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Skills")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Skills")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Skills")
  public abstract List<Skills> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Skills")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Skills WHERE Item_ID IN (:ids)")
  public abstract List<Skills> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Skills WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Skills WHERE Source == :source")
  public abstract List<Skills> getObjectsWithSource(String source);

  @Query("SELECT * FROM Skills WHERE Item_ID == :itemID")
  public abstract Skills getObjectWithId(int itemID);

  @Query("SELECT * FROM Skills WHERE Name > :name")
  public abstract Skills getObjectWithName(String name);

  @Query("DELETE FROM Skills")
  public abstract void deleteAll();
}
