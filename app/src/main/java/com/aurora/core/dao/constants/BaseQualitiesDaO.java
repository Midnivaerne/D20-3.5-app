package com.aurora.core.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.BaseQualities;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;

@Dao
public abstract class BaseQualitiesDaO implements BaseDaO<BaseQualities> {

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

  @Transaction
  public List<BaseQualities> getAllObjectsAsMergedObjectItem() {
    ArrayList<BaseQualities> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Rules> resultItem = new ArrayList<>(getItemsAsRules());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
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

  @Transaction
  public List<BaseQualities> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<BaseQualities> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Rules> resultItem = new ArrayList<>(getObjectsWithIdsAsRules(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
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
