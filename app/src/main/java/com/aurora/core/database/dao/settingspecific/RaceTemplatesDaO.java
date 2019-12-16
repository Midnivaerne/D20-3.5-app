package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.RaceTemplates;

@Dao
public abstract class RaceTemplatesDaO extends BaseDaO<RaceTemplates> {

  @Query("SELECT COUNT(*) from RaceTemplates")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM RaceTemplates")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM RaceTemplates")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM RaceTemplates")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM RaceTemplates")
  public abstract List<RaceTemplates> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM RaceTemplates")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM RaceTemplates WHERE Item_ID IN (:ids)")
  public abstract List<RaceTemplates> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM RaceTemplates WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM RaceTemplates WHERE Source == :source")
  public abstract List<RaceTemplates> getObjectsWithSource(String source);

  @Query("SELECT * FROM RaceTemplates WHERE Item_ID == :itemID")
  public abstract RaceTemplates getObjectWithId(int itemID);

  @Query("SELECT * FROM RaceTemplates WHERE Name == :name")
  public abstract RaceTemplates getObjectWithName(String name);

  @Query("DELETE FROM RaceTemplates")
  public abstract void deleteAll();
}
