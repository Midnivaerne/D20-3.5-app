package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Equipment;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public abstract class EquipmentDAO  implements BaseDAO<Equipment> {

    @Query("SELECT COUNT(*) from Equipment")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM Equipment")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM Equipment")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM Equipment")
    public abstract List<String> getSources();

    @Query("SELECT * FROM Equipment")
    public abstract List<Equipment> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM Equipment")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM Equipment WHERE Source > :qSource")
    public abstract List<Equipment> getItemsWithSource(String qSource);

    @Query("SELECT * FROM Equipment WHERE Item_ID > :qId")
    public abstract Equipment getItemWithId(String qId);

    @Query("SELECT * FROM Equipment WHERE Name > :qName")
    public abstract Equipment getItemWithName(String qName);

    @Query("DELETE FROM Equipment")
    public abstract void deleteAll();
}
