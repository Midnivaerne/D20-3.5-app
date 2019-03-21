package com.aurora.core.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.constants.CoreStates;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class CoreStatesDAO implements BaseDAO<CoreStates> {

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

  @Transaction
  public List<CoreStates> getAllObjectsAsMergedObjectItem() {
    ArrayList<CoreStates> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Rules> resultItem = new ArrayList<>(getItemsAsRules());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
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

  @Transaction
  public List<CoreStates> getObjectsWithIdsAsMergedObjectItem(List<Integer> Ids) {
    ArrayList<CoreStates> result = new ArrayList<>(getObjectsWithIdsAsObject(Ids));
    ArrayList<Rules> resultItem = new ArrayList<>(getObjectsWithIdsAsRules(Ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
  }

  @Query("SELECT * FROM CoreStates WHERE Item_ID IN (:Ids)")
  public abstract List<CoreStates> getObjectsWithIdsAsObject(List<Integer> Ids);

  @Query("SELECT * FROM CoreStates WHERE Item_ID IN (:Ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> Ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> Ids) {
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
