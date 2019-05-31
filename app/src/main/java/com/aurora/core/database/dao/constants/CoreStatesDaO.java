package com.aurora.core.database.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.CoreStates;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;

@Dao
public abstract class CoreStatesDaO extends BaseDaO<CoreStates> {

  @Query("SELECT COUNT(*) from CoreStates")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM CoreStates")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM CoreStates")
  public abstract List<String> getAllNames();

  @Override
  public List<String> getAllSources() {
    return null;
  }

  @Query("SELECT * FROM CoreStates")
  public abstract List<CoreStates> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM CoreStates")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
  }

  @Query("SELECT * FROM CoreStates WHERE Item_ID IN (:ids)")
  public abstract List<CoreStates> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM CoreStates WHERE Item_ID IN (:ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> ids) {
    return null;
  }

  @Override
  public List<CoreStates> getObjectsWithSource(String source) {
    return null;
  }

  @Query("SELECT * FROM CoreStates WHERE Item_ID == :itemID")
  public abstract CoreStates getObjectWithId(int itemID);

  @Query("SELECT * FROM CoreStates WHERE Name == :name")
  public abstract CoreStates getObjectWithName(String name);

  @Query("DELETE FROM CoreStates")
  public abstract void deleteAll();

}
