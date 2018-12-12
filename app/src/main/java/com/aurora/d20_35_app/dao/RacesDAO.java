package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Races;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class RacesDAO implements BaseDAO<Races> {

    @Query("SELECT COUNT(*) from Races")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Races")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Races")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Races")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Races")
    public abstract List<Races> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Races")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Races WHERE Source > :qSource")
    public abstract List<Races> getItemsWithSource(String qSource);

    @Query("SELECT * FROM Races WHERE Item_ID > :qId")
    public abstract Races getItemWithId(String qId);

    @Query("SELECT * FROM Races WHERE Name > :qName")
    public abstract Races getItemWithName(String qName);

    @Query("DELETE FROM Races")
    public abstract void deleteAll();
}
