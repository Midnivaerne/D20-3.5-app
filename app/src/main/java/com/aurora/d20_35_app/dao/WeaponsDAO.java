package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Weapons;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class WeaponsDAO implements BaseDAO<Weapons> {

    @Query("SELECT COUNT(*) from Weapons")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Weapons")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Weapons")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Weapons")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Weapons")
    public abstract List<Weapons> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Weapons")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Weapons WHERE Source > :qSource")
    public abstract List<Weapons> getItemsWithSource(String qSource);

    @Query("SELECT * FROM Weapons WHERE Item_ID > :qId")
    public abstract Weapons getItemWithId(String qId);

    @Query("SELECT * FROM Weapons WHERE Name > :qName")
    public abstract Weapons getItemWithName(String qName);

    @Query("DELETE FROM Weapons")
    public abstract void deleteAll();
}
