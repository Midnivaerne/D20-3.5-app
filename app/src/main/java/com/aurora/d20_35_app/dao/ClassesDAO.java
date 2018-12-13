package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Classes;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class ClassesDAO implements BaseDAO<Classes> {

    @Query("SELECT COUNT(*) from Classes")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Classes")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Classes")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Classes")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Classes")
    public abstract List<Classes> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Classes")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Classes WHERE Source == :source")
    public abstract List<Classes> getItemsWithSource(String source);

    @Query("SELECT * FROM Classes WHERE Item_ID == :itemID")
    public abstract Classes getItemWithId(int itemID);

    @Query("SELECT * FROM Classes WHERE Name == :name")
    public abstract Classes getItemWithName(String name);

    @Query("DELETE FROM Classes")
    public abstract void deleteAll();
}
