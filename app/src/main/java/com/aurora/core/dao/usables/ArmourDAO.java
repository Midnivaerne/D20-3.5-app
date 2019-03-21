package com.aurora.core.dao.usables;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.usables.Armour;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class ArmourDAO implements BaseDAO<Armour> {

  @Query("SELECT COUNT(*) from Armour")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Armour")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Armour")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Armour")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Armour> getAllObjectsAsMergedObjectItem() {
    ArrayList<Armour> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Armour")
  public abstract List<Armour> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Armour")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Armour> getObjectsWithIdsAsMergedObjectItem(List<Integer> Ids) {
    ArrayList<Armour> result = new ArrayList<>(getObjectsWithIdsAsObject(Ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(Ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Armour WHERE Item_ID IN (:Ids)")
  public abstract List<Armour> getObjectsWithIdsAsObject(List<Integer> Ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Armour WHERE Item_ID IN (:Ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> Ids);

  @Query("SELECT * FROM Armour WHERE Source == :source")
  public abstract List<Armour> getObjectsWithSource(String source);

  @Query("SELECT * FROM Armour WHERE Item_ID == :itemID")
  public abstract Armour getObjectWithId(int itemID);

  @Query("SELECT * FROM Armour WHERE Name == :name")
  public abstract Armour getObjectWithName(String name);

  @Query("DELETE FROM Armour")
  public abstract void deleteAll();
}
