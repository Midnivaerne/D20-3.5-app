package com.aurora.d20_35_app.dao.usables;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.usables.Equipment;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public abstract class EquipmentDAO  implements BaseDAO<Equipment> {

    @Query("SELECT COUNT(*) from Equipment")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Equipment")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Equipment")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Equipment")
    public abstract List<String> getSources();

    @Transaction
    public List<Equipment> getItemWithSuperFields() {
        ArrayList<Equipment> result = new ArrayList<>(getItems());
        ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM Equipment")
    public abstract List<Equipment> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Equipment")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Equipment WHERE Source == :source")
    public abstract List<Equipment> getItemsWithSource(String source);

    @Query("SELECT * FROM Equipment WHERE Item_ID == :itemID")
    public abstract Equipment getItemWithId(int itemID);

    @Query("SELECT * FROM Equipment WHERE Name == :name")
    public abstract Equipment getItemWithName(String name);

    @Query("DELETE FROM Equipment")
    public abstract void deleteAll();
}
