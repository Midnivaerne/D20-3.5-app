package com.aurora.core.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;

@Dao
public abstract class SizesDaO implements BaseDaO<Sizes> {

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

  @Transaction
  public List<Sizes> getAllObjectsAsMergedObjectItem() {
    ArrayList<Sizes> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Rules> resultItem = new ArrayList<>(getItemsAsRules());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
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

  @Transaction
  public List<Sizes> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<Sizes> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Rules> resultItem = new ArrayList<>(getObjectsWithIdsAsRules(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
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
