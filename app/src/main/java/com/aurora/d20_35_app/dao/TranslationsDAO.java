package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Translations;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

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
