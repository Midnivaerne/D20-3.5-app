package com.aurora.core.database.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.usables.Price;

@Dao
public abstract class PriceDaO extends BaseDaO<Price> {

  @Query("SELECT COUNT(*) from Price")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Price")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Price")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Price")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Price")
  public abstract List<Price> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Price")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Price WHERE Item_ID IN (:ids)")
  public abstract List<Price> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Price WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Price WHERE Source == :source")
  public abstract List<Price> getObjectsWithSource(String source);

  @Query("SELECT * FROM Price WHERE Item_ID == :itemID")
  public abstract Price getObjectWithId(int itemID);

  @Query("SELECT * FROM Price WHERE Name == :name")
  public abstract Price getObjectWithName(String name);

  @Query("DELETE FROM Price")
  public abstract void deleteAll();
}
