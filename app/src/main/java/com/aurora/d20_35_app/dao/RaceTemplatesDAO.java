package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.RaceTemplates;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class RaceTemplatesDAO  implements BaseDAO<RaceTemplates> {

    @Query("SELECT COUNT(*) from RaceTemplates")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM RaceTemplates")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM RaceTemplates")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM RaceTemplates")
    public abstract List<String> getSources();

    @Query("SELECT * FROM RaceTemplates")
    public abstract List<RaceTemplates> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM RaceTemplates")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM RaceTemplates WHERE Source == :source")
    public abstract List<RaceTemplates> getItemsWithSource(String source);

    @Query("SELECT * FROM RaceTemplates WHERE Item_ID == :itemID")
    public abstract RaceTemplates getItemWithId(int itemID);

    @Query("SELECT * FROM RaceTemplates WHERE Name == :name")
    public abstract RaceTemplates getItemWithName(String name);

    @Query("DELETE FROM RaceTemplates")
    public abstract void deleteAll();
}
