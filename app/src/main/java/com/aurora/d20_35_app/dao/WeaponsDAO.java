package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Weapons;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface WeaponsDAO {

    @Query("SELECT * FROM Weapons")
    List<Weapons> getWeapons();

    @Query("SELECT DISTINCT Source FROM Weapons")
    List<String> getSources();
}
