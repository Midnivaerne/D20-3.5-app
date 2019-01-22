package com.aurora.d20_35_app.dao.settingSpecific;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.settingSpecific.Spells;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class SpellsDAO  implements BaseDAO<Spells> {

    @Query("SELECT COUNT(*) from Spells")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Spells")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Spells")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Spells")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Spells")
    public abstract List<Spells> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Spells")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Spells WHERE Source == :source")
    public abstract List<Spells> getItemsWithSource(String source);

    @Query("SELECT * FROM Spells WHERE Item_ID == :itemID")
    public abstract Spells getItemWithId(int itemID);

    @Query("SELECT * FROM Spells WHERE Name == :name")
    public abstract Spells getItemWithName(String name);

    @Query("DELETE FROM Spells")
    public abstract void deleteAll();
}
