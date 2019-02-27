package com.aurora.d20_35_app.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.constants.RulesSkills;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.helpers.Rules;
import java.util.ArrayList;
import java.util.List;

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

    @Transaction
    public List<RulesSkills> getItemWithSuperFields() {
        ArrayList<RulesSkills> result = new ArrayList<>(getItems());
        ArrayList<Rules> resultItem = new ArrayList<>(getItemsAsRules());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
        }
        return result;
    }

    @Query("SELECT * FROM RulesSkills")
    public abstract List<RulesSkills> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM RulesSkills")
    public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

    @Override
    public List<Item> getItemsAsItem() {
        return null;
    }

    @Override
    public List<RulesSkills> getItemsWithSource(String source) {
        return null;
    }

    @Query("SELECT * FROM RulesSkills WHERE Item_ID == :itemID")
    public abstract RulesSkills getItemWithId(int itemID);

    @Query("SELECT * FROM RulesSkills WHERE Name == :name")
    public abstract RulesSkills getItemWithName(String name);

    @Query("DELETE FROM RulesSkills")
    public abstract void deleteAll();

}
