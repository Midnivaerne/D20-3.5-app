package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.RulesCombat;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class RulesCombatDAO implements BaseDAO<RulesCombat> {

    @Query("SELECT COUNT(*) from RulesCombat")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM RulesCombat")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM RulesCombat")
    public abstract List<String> getNames();

    @Override
    public List<String> getSources() {
        return null;
    }

    @Query("SELECT * FROM RulesCombat")
    public abstract List<RulesCombat> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM RulesCombat")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Override
    public List<RulesCombat> getItemsWithSource(String qSource) {
        return null;
    }

    @Query("SELECT * FROM RulesCombat WHERE Item_ID > :qId")
    public abstract RulesCombat getItemWithId(String qId);

    @Query("SELECT * FROM RulesCombat WHERE Name > :qName")
    public abstract RulesCombat getItemWithName(String qName);

    @Query("DELETE FROM RulesCombat")
    public abstract void deleteAll();

}
