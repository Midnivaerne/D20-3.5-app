package com.aurora.core.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.Item;

@Dao
public abstract class DatabasesDaO extends BaseDaO<Databases> {

  @Query("SELECT COUNT(*) from Databases")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM Databases")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM Databases")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM Databases")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM Databases")
  public abstract List<Databases> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Databases")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

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
