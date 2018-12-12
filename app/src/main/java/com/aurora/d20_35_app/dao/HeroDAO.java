package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Hero;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class HeroDAO  implements BaseDAO<Hero> {

    @Query("SELECT COUNT(*) from Hero")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Hero")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Hero")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Hero")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Hero")
    public abstract List<Hero> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Hero")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Hero WHERE Source > :qSource")
    public abstract List<Hero> getItemsWithSource(String qSource);

    @Query("SELECT * FROM Hero WHERE Item_ID > :qId")
    public abstract Hero getItemWithId(String qId);

    @Query("SELECT * FROM Hero WHERE Name > :qName")
    public abstract Hero getItemWithName(String qName);

    @Query("DELETE FROM Hero")
    public abstract void deleteAll();
}
