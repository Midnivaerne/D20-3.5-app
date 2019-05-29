package com.aurora.core.database.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.BaseQualities;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;

@Dao
public abstract class BaseQualitiesDaO extends BaseDaO<BaseQualities> {

  @Query("SELECT COUNT(*) from BaseQualities")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM BaseQualities")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM BaseQualities")
  public abstract List<String> getAllNames();

  @Override
  public List<String> getAllSources() {
    return null;
  }

  @Query("SELECT * FROM BaseQualities")
  public abstract List<BaseQualities> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM BaseQualities")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
  }

  @Query("SELECT * FROM BaseQualities WHERE Item_ID IN (:ids)")
  public abstract List<BaseQualities> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM BaseQualities WHERE Item_ID IN (:ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> ids) {
    return null;
  }

  @Override
  public List<BaseQualities> getObjectsWithSource(String source) {
    return null;
  }

  @Query("SELECT * FROM BaseQualities WHERE Item_ID == :itemID")
  public abstract BaseQualities getObjectWithId(int itemID);

  @Query("SELECT * FROM BaseQualities WHERE Name == :name")
  public abstract BaseQualities getObjectWithName(String name);

  @Query("DELETE FROM BaseQualities")
  public abstract void deleteAll();

}
