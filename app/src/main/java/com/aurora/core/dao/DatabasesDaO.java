package com.aurora.core.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;

@Dao
public abstract class DatabasesDaO implements BaseDaO<Databases> {

  @Query("SELECT COUNT(*) from Databases")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Databases")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Databases")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Databases")
  public abstract List<String> getAllSources();

  @Transaction
  public List<Databases> getAllObjectsAsMergedObjectItem() {
    ArrayList<Databases> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Databases")
  public abstract List<Databases> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Databases")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Transaction
  public List<Databases> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<Databases> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Databases WHERE Item_ID IN (:ids)")
  public abstract List<Databases> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM Databases WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM Databases WHERE Source == :source")
  public abstract List<Databases> getObjectsWithSource(String source);

  @Query("SELECT * FROM Databases WHERE Item_ID == :itemID")
  public abstract Databases getObjectWithId(int itemID);

  @Query("SELECT * FROM Databases WHERE Name == :name")
  public abstract Databases getObjectWithName(String name);

  @Query("DELETE FROM Databases")
  public abstract void deleteAll();


  @Query("SELECT Translations.Translation FROM Databases "
      + "INNER JOIN Translations ON Databases.Name = Translations.Name WHERE Translations.Language = :language")
  public abstract List<String> getNamesTranslated(String language);

}
