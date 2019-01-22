package com.aurora.d20_35_app.dao.settingSpecific;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.settingSpecific.Deities;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class DeitiesDAO implements BaseDAO<Deities> {

    @Query("SELECT COUNT(*) from Deities")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Deities")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Deities")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Deities")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Deities")
    public abstract List<Deities> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Deities")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Deities WHERE Source == :source")
    public abstract List<Deities> getItemsWithSource(String source);

    @Query("SELECT * FROM Deities WHERE Item_ID == :itemID")
    public abstract Deities getItemWithId(int itemID);

    @Query("SELECT * FROM Deities WHERE Name == :name")
    public abstract Deities getItemWithName(String name);

    @Query("DELETE FROM Deities")
    public abstract void deleteAll();
}
