package com.aurora.core.database.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.constants.Sizes;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.helpers.Rules;

@Dao
public abstract class SizesDaO extends BaseDaO<Sizes> {

  @Query("SELECT COUNT(*) from Sizes")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Sizes")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Sizes")
  public abstract List<String> getAllNames();

  @Override
  public List<String> getAllSources() {
    return null;
  }

  @Query("SELECT * FROM Sizes")
  public abstract List<Sizes> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Sizes")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
  }

  @Query("SELECT * FROM Sizes WHERE Item_ID IN (:ids)")
  public abstract List<Sizes> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Sizes WHERE Item_ID IN (:ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> ids) {
    return null;
  }

  @Override
  public List<Sizes> getObjectsWithSource(String source) {
    return null;
  }

  @Query("SELECT * FROM Sizes WHERE Item_ID == :itemID")
  public abstract Sizes getObjectWithId(int itemID);

  @Query("SELECT * FROM Sizes WHERE Name == :name")
  public abstract Sizes getObjectWithName(String name);

  @Query("DELETE FROM Sizes")
  public abstract void deleteAll();

}
