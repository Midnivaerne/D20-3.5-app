package com.aurora.d20_35_app.dao.userData;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.userData.HeroPlayer;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public abstract class HeroPlayerDAO implements BaseDAO<HeroPlayer> {

    @Query("SELECT COUNT(*) from HeroPlayer")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM HeroPlayer")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM HeroPlayer")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM HeroPlayer")
    public abstract List<String> getSources();

    @Transaction
    public List<HeroPlayer> getItemWithSuperFields() {
        ArrayList<HeroPlayer> result = new ArrayList<>();
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

    @Query("SELECT * FROM HeroPlayer")
    public abstract List<HeroPlayer> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM HeroPlayer")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM HeroPlayer WHERE Source == :source")
    public abstract List<HeroPlayer> getItemsWithSource(String source);

    @Query("SELECT * FROM HeroPlayer WHERE Item_ID == :itemID")
    public abstract HeroPlayer getItemWithId(int itemID);

    @Query("SELECT * FROM HeroPlayer WHERE Name == :name")
    public abstract HeroPlayer getItemWithName(String name);

    @Query("DELETE FROM HeroPlayer")
    public abstract void deleteAll();
}
