package com.aurora.core.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.Translations;
import com.aurora.core.models.helpers.Item;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class TranslationsDAO implements BaseDAO<Translations> {

  @Query("SELECT COUNT(*) from Translations")
  public abstract int countItems();

  @Query("SELECT Item_ID FROM Translations")
  public abstract List<Integer> getIds();

  @Query("SELECT Name FROM Translations")
  public abstract List<String> getNames();

  @Query("SELECT DISTINCT Source FROM Translations")
  public abstract List<String> getSources();

  @Transaction
  public List<Translations> getItemWithSuperFields() {
    ArrayList<Translations> result = new ArrayList<>(getItems());
    ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM Translations")
  public abstract List<Translations> getItems();

  @Query("SELECT * FROM Translations WHERE Language == :language")
  public abstract List<Translations> getItemsForLanguage(String language);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Translations")
  public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM Translations WHERE Source == :source")
  public abstract List<Translations> getItemsWithSource(String source);

  @Query("SELECT * FROM Translations WHERE Item_ID == :itemID")
  public abstract Translations getItemWithId(int itemID);

  @Query("SELECT * FROM Translations WHERE Name == :name")
  public abstract Translations getItemWithName(String name);

  @Query("DELETE FROM Translations")
  public abstract void deleteAll();

  @Query("SELECT Translation FROM Translations")
  public abstract List<String> getAllTranslations();

  @Query("SELECT Translation FROM Translations WHERE Language == :language")
  public abstract List<String> getAllTranslationsForLanguage(String language);

  @Query("SELECT * FROM Translations WHERE Source == :source AND Language == :language")
  public abstract List<Translations> getTranslationsWithSourceForLanguage(String source, String language);

  @Query("SELECT * FROM Translations WHERE Item_ID == :itemID AND Language == :language")
  public abstract Translations getTranslationWithIdForLanguage(int itemID, String language);

  @Query("SELECT * FROM Translations WHERE Name == :name AND Language == :language")
  public abstract Translations getTranslationWithNameForLanguage(String name, String language);
}
