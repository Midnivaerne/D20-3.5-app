package com.aurora.core.database.dao.settingspecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Feats;

@Dao
public abstract class FeatsDaO extends BaseDaO<Feats> {

  @Query("SELECT COUNT(*) from Feats")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Feats")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Feats")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Feats")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Feats")
  public abstract List<Feats> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Feats")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Feats WHERE Item_ID IN (:ids)")
  public abstract List<Feats> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Feats WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Feats WHERE Source == :source")
  public abstract List<Feats> getObjectsWithSource(String source);

  @Query("SELECT * FROM Feats WHERE Item_ID == :itemID")
  public abstract Feats getObjectWithId(int itemID);

  @Query("SELECT * FROM Feats WHERE Name == :name")
  public abstract Feats getObjectWithName(String name);

  @Query("DELETE FROM Feats")
  public abstract void deleteAll();
}
