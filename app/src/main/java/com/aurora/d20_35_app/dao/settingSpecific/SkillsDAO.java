package com.aurora.d20_35_app.dao.settingSpecific;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.settingSpecific.Skills;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public abstract class SkillsDAO  implements BaseDAO<Skills> {

    @Query("SELECT COUNT(*) from Skills")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Skills")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Skills")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Skills")
    public abstract List<String> getSources();

    @Transaction
    public List<Skills> getItemWithSuperFields() {
        ArrayList<Skills> result = new ArrayList<>();
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

    @Query("SELECT * FROM Skills")
    public abstract List<Skills> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Skills")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Skills WHERE Source == :source")
    public abstract List<Skills> getItemsWithSource(String source);

    @Query("SELECT * FROM Skills WHERE Item_ID == :itemID")
    public abstract Skills getItemWithId(int itemID);

    @Query("SELECT * FROM Skills WHERE Name > :name")
    public abstract Skills getItemWithName(String name);

    @Query("DELETE FROM Skills")
    public abstract void deleteAll();
}
