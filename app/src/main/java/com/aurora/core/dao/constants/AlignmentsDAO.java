package com.aurora.core.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class AlignmentsDAO implements BaseDAO<Alignments> {

  @Query("SELECT COUNT(*) from Alignments")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Alignments")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Alignments")
  public abstract List<String> getAllNames();

  @Override
  public List<String> getAllSources() {
    return null;
  }

  @Transaction
  public List<Alignments> getAllObjectsAsMergedObjectItem() {
    ArrayList<Alignments> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Rules> resultItem = new ArrayList<>(getItemsAsRules());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
  }

  @Query("SELECT * FROM Alignments")
  public abstract List<Alignments> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Alignments")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
  }

  @Transaction
  public List<Alignments> getObjectsWithIdsAsMergedObjectItem(List<Integer> Ids) {
    ArrayList<Alignments> result = new ArrayList<>(getObjectsWithIdsAsObject(Ids));
    ArrayList<Rules> resultItem = new ArrayList<>(getObjectsWithIdsAsRules(Ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
  }

  @Query("SELECT * FROM Alignments WHERE Item_ID IN (:Ids)")
  public abstract List<Alignments> getObjectsWithIdsAsObject(List<Integer> Ids);

  @Query("SELECT * FROM Alignments WHERE Item_ID IN (:Ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> Ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> Ids) {
    return null;
  }

  @Override
  public List<Alignments> getObjectsWithSource(String source) {
    return null;
  }

  @Query("SELECT * FROM Alignments WHERE Item_ID == :itemID")
  public abstract Alignments getObjectWithId(int itemID);

  @Query("SELECT * FROM Alignments WHERE Name == :name")
  public abstract Alignments getObjectWithName(String name);

  @Query("DELETE FROM Alignments")
  public abstract void deleteAll();

}
