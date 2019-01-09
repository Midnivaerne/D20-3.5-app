package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Databases;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class DatabasesDAO implements BaseDAO<Databases> {

    @Query("SELECT COUNT(*) from Databases")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Databases")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Databases")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Databases")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Databases")
    public abstract List<Databases> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Databases")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Databases WHERE Source == :source")
    public abstract List<Databases> getItemsWithSource(String source);

    @Query("SELECT * FROM Databases WHERE Item_ID == :itemID")
    public abstract Databases getItemWithId(int itemID);

    @Query("SELECT * FROM Databases WHERE Name == :name")
    public abstract Databases getItemWithName(String name);

    @Query("DELETE FROM Databases")
    public abstract void deleteAll();


    @Query("SELECT Translations.Translation FROM Databases INNER JOIN Translations ON Databases.Name = Translations.Name WHERE Translations.Language = :language")
    public abstract List<String> getNamesTranslated(String language);

}
