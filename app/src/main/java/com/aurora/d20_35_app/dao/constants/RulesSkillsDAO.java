package com.aurora.d20_35_app.dao.constants;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.constants.RulesSkills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class RulesSkillsDAO implements BaseDAO<RulesSkills> {

    @Query("SELECT COUNT(*) from RulesSkills")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM RulesSkills")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM RulesSkills")
    public abstract List<String> getNames();

    @Override
    public List<String> getSources() {
        return null;
    }

    @Query("SELECT * FROM RulesSkills")
    public abstract List<RulesSkills> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM RulesSkills")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Override
    public List<RulesSkills> getItemsWithSource(String source){
        return null;
    }

    @Query("SELECT * FROM RulesSkills WHERE Item_ID == :itemID")
    public abstract RulesSkills getItemWithId(int itemID);

    @Query("SELECT * FROM RulesSkills WHERE Name == :name")
    public abstract RulesSkills getItemWithName(String name);

    @Query("DELETE FROM RulesSkills")
    public abstract void deleteAll();

}
