package com.aurora.d20_35_app.dao.settingSpecific;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Races;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

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

    @Transaction
    public List<Races> getItemWithSuperFields() {
        ArrayList<Races> result = new ArrayList<>(getItems());
        ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM Races")
    public abstract List<Races> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Races")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Races WHERE Source == :source")
    public abstract List<Races> getItemsWithSource(String source);

    @Query("SELECT * FROM Races WHERE Item_ID == :itemID")
    public abstract Races getItemWithId(int itemID);

    @Query("SELECT * FROM Races WHERE Name == :name")
    public abstract Races getItemWithName(String name);

    @Query("DELETE FROM Races")
    public abstract void deleteAll();
}
