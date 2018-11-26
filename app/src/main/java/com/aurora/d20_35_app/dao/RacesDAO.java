package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Races;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RacesDAO {

    @Query("SELECT * FROM Races")
    List<Races> getRaces();

    @Query("SELECT COUNT(*) from Races")
    int countRaces();

    @Query("SELECT DISTINCT Source FROM Races")
    List<String> getSources();

    @Insert
    void insertAll(Races... races);

    @Delete
    void delete(Races races);
}
