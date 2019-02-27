package com.aurora.d20_35_app.dao.settingSpecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Feats;
import java.util.ArrayList;
import java.util.List;

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

    @Transaction
    public List<Feats> getItemWithSuperFields() {
        ArrayList<Feats> result = new ArrayList<>(getItems());
        ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

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
