package com.aurora.d20_35_app.dao.settingSpecific;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.settingSpecific.HeroNPC;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class HeroNPCDAO implements BaseDAO<HeroNPC> {

    @Query("SELECT COUNT(*) from HeroNPC")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM HeroNPC")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM HeroNPC")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM HeroNPC")
    public abstract List<String> getSources();

    @Query("SELECT * FROM HeroNPC")
    public abstract List<HeroNPC> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM HeroNPC")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM HeroNPC WHERE Source == :source")
    public abstract List<HeroNPC> getItemsWithSource(String source);

    @Query("SELECT * FROM HeroNPC WHERE Item_ID == :itemID")
    public abstract HeroNPC getItemWithId(int itemID);

    @Query("SELECT * FROM HeroNPC WHERE Name == :name")
    public abstract HeroNPC getItemWithName(String name);

    @Query("DELETE FROM HeroNPC")
    public abstract void deleteAll();
}
