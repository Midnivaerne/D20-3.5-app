package com.aurora.d20_35_app.dao.settingSpecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Monsters;
import java.util.ArrayList;
import java.util.List;

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

    @Transaction
    public List<Monsters> getItemWithSuperFields() {
        ArrayList<Monsters> result = new ArrayList<>(getItems());
        ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

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
