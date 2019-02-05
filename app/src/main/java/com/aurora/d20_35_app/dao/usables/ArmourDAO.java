package com.aurora.d20_35_app.dao.usables;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.usables.Armour;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public abstract class ArmourDAO implements BaseDAO<Armour> {

    @Query("SELECT COUNT(*) from Armour")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Armour")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Armour")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Armour")
    public abstract List<String> getSources();

    @Transaction
    public List<Armour> getItemWithSuperFields() {
        ArrayList<Armour> result = new ArrayList<>();
        result.addAll(getItems());
        ArrayList<Item> resultItem = new ArrayList<>();
        resultItem.addAll(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM Armour")
    public abstract List<Armour> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Armour")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Armour WHERE Source == :source")
    public abstract List<Armour> getItemsWithSource(String source);

    @Query("SELECT * FROM Armour WHERE Item_ID == :itemID")
    public abstract Armour getItemWithId(int itemID);

    @Query("SELECT * FROM Armour WHERE Name == :name")
    public abstract Armour getItemWithName(String name);

    @Query("DELETE FROM Armour")
    public abstract void deleteAll();
}
