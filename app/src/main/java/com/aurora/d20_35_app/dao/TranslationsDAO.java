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

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Translations")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Translations WHERE Source > :qSource")
    public abstract List<Translations> getItemsWithSource(String qSource);

    @Query("SELECT * FROM Translations WHERE Item_ID > :qId")
    public abstract Translations getItemWithId(String qId);

    @Query("SELECT * FROM Translations WHERE Name > :qName")
    public abstract Translations getItemWithName(String qName);

    @Query("DELETE FROM Translations")
    public abstract void deleteAll();
}
