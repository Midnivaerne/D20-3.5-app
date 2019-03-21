package com.aurora.core.dao.settingSpecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingSpecific.Monsters;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class MonstersDAO implements BaseDAO<Monsters> {

  @Query("SELECT COUNT(*) from Monsters")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Monsters")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Monsters")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Monsters")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Monsters> getAllObjectsAsMergedObjectItem() {
    ArrayList<Monsters> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Monsters")
  public abstract List<Monsters> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Monsters")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Monsters> getObjectsWithIdsAsMergedObjectItem(List<Integer> Ids) {
    ArrayList<Monsters> result = new ArrayList<>(getObjectsWithIdsAsObject(Ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(Ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Monsters WHERE Item_ID IN (:Ids)")
  public abstract List<Monsters> getObjectsWithIdsAsObject(List<Integer> Ids);

  @Query("SELECT * FROM Monsters WHERE Item_ID IN (:Ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> Ids);

  @Query("SELECT * FROM Monsters WHERE Source == :source")
  public abstract List<Monsters> getObjectsWithSource(String source);

  @Query("SELECT * FROM Monsters WHERE Item_ID == :itemID")
  public abstract Monsters getObjectWithId(int itemID);

  @Query("SELECT * FROM Monsters WHERE Name == :name")
  public abstract Monsters getObjectWithName(String name);

  @Query("DELETE FROM Monsters")
  public abstract void deleteAll();
}
