package com.aurora.core.database.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;

@Dao
public abstract class AlignmentsDaO extends BaseDaO<Alignments> {

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

  @Query("SELECT * FROM Alignments")
  public abstract List<Alignments> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Alignments")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
  }

  @Query("SELECT * FROM Alignments WHERE Item_ID IN (:ids)")
  public abstract List<Alignments> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Alignments WHERE Item_ID IN (:ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> ids) {
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
