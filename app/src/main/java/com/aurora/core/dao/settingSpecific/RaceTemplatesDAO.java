package com.aurora.core.dao.settingSpecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingSpecific.RaceTemplates;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class RaceTemplatesDAO implements BaseDAO<RaceTemplates> {

  @Query("SELECT COUNT(*) from RaceTemplates")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM RaceTemplates")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM RaceTemplates")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM RaceTemplates")
  public abstract List<String> getAllSources();

  @Transaction
  public List<RaceTemplates> getAllObjectsAsMergedObjectItem() {
    ArrayList<RaceTemplates> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM RaceTemplates")
  public abstract List<RaceTemplates> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM RaceTemplates")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<RaceTemplates> getObjectsWithIdsAsMergedObjectItem(List<Integer> Ids) {
    ArrayList<RaceTemplates> result = new ArrayList<>(getObjectsWithIdsAsObject(Ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(Ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM RaceTemplates WHERE Item_ID IN (:Ids)")
  public abstract List<RaceTemplates> getObjectsWithIdsAsObject(List<Integer> Ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM RaceTemplates WHERE Item_ID IN (:Ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> Ids);

  @Query("SELECT * FROM RaceTemplates WHERE Source == :source")
  public abstract List<RaceTemplates> getObjectsWithSource(String source);

  @Query("SELECT * FROM RaceTemplates WHERE Item_ID == :itemID")
  public abstract RaceTemplates getObjectWithId(int itemID);

  @Query("SELECT * FROM RaceTemplates WHERE Name == :name")
  public abstract RaceTemplates getObjectWithName(String name);

  @Query("DELETE FROM RaceTemplates")
  public abstract void deleteAll();
}
