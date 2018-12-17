package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Feats;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class FeatsDAO  implements BaseDAO<Feats> {

    @Query("SELECT COUNT(*) from Feats")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Feats")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Feats")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Feats")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Feats")
    public abstract List<Feats> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Feats")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Feats WHERE Source == :source")
    public abstract List<Feats> getItemsWithSource(String source);

    @Query("SELECT * FROM Feats WHERE Item_ID == :itemID")
    public abstract Feats getItemWithId(int itemID);

    @Query("SELECT * FROM Feats WHERE Name == :name")
    public abstract Feats getItemWithName(String name);

    @Query("DELETE FROM Feats")
    public abstract void deleteAll();
}
