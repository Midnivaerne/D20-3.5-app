package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Races;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class RacesDAO implements BaseDAO<Races> {

    @Query("SELECT * FROM Races")
    public abstract List<Races> getRaces();

    //@Query("SELECT * FROM Races")
    //public abstract List<Item> getItemRaces(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT COUNT(*) from Races")
    public abstract int countRaces();

    @Query("SELECT DISTINCT Source FROM Races")
    public abstract List<String> getSources();

    @Query("DELETE FROM Races")
    public abstract void deleteAll();

    @Query("SELECT Name FROM Races")
    public abstract List<String> getNames();

    @Query("SELECT * FROM Races WHERE Name > :qName")
    public abstract Races getRaceWithName(String qName);
}
