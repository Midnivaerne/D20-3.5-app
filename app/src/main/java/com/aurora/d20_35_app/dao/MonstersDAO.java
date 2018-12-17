package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Monsters;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class MonstersDAO  implements BaseDAO<Monsters> {

    @Query("SELECT COUNT(*) from Monsters")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Monsters")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Monsters")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Monsters")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Monsters")
    public abstract List<Monsters> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Monsters")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Monsters WHERE Source == :source")
    public abstract List<Monsters> getItemsWithSource(String source);

    @Query("SELECT * FROM Monsters WHERE Item_ID == :itemID")
    public abstract Monsters getItemWithId(int itemID);

    @Query("SELECT * FROM Monsters WHERE Name == :name")
    public abstract Monsters getItemWithName(String name);

    @Query("DELETE FROM Monsters")
    public abstract void deleteAll();
}
