package com.aurora.core.dao.settingSpecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingSpecific.Classes;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class ClassesDAO implements BaseDAO<Classes> {

  @Query("SELECT COUNT(*) from Classes")
  public abstract int countItems();

  @Query("SELECT Item_ID FROM Classes")
  public abstract List<Integer> getIds();

  @Query("SELECT Name FROM Classes")
  public abstract List<String> getNames();

  @Query("SELECT DISTINCT Source FROM Classes")
  public abstract List<String> getSources();

  @Transaction
  public List<Classes> getItemWithSuperFields() {
    ArrayList<Classes> result = new ArrayList<>(getItems());
    ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Classes")
  public abstract List<Classes> getItems();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Classes")
  public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Classes WHERE Source == :source")
  public abstract List<Classes> getItemsWithSource(String source);

  @Query("SELECT * FROM Classes WHERE Item_ID == :itemID")
  public abstract Classes getItemWithId(int itemID);

  @Query("SELECT * FROM Classes WHERE Name == :name")
  public abstract Classes getItemWithName(String name);

  @Query("DELETE FROM Classes")
  public abstract void deleteAll();
}
