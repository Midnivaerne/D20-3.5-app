package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Races;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface RacesDAO {

    @Query("SELECT * FROM Races")
    List<Races> getRaces();
}
