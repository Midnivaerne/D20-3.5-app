package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Skills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

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

    @Query("SELECT * FROM Skills")
    public abstract List<Skills> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Skills")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Skills WHERE Source > :qSource")
    public abstract List<Skills> getItemsWithSource(String qSource);

    @Query("SELECT * FROM Skills WHERE Item_ID > :qId")
    public abstract Skills getItemWithId(String qId);

    @Query("SELECT * FROM Skills WHERE Name > :qName")
    public abstract Skills getItemWithName(String qName);

    @Query("DELETE FROM Skills")
    public abstract void deleteAll();
}
