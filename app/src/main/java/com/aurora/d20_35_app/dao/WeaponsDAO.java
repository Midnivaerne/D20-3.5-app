package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Weapons;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class WeaponsDAO  implements BaseDAO<Weapons> {

    @Query("SELECT * FROM Weapons")
    public abstract List<Weapons> getWeapons();

    @Query("SELECT DISTINCT Source FROM Weapons")
    public abstract List<String> getSources();

    @Query("DELETE FROM Weapons")
    public abstract void deleteAll();
}
