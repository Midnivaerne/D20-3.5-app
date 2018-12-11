package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Armour;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class ArmourDAO implements BaseDAO<Armour> {

    @Query("SELECT * FROM Armour")
    public abstract List<Armour> getArmour();

    @Query("SELECT DISTINCT Source FROM Armour")
    public abstract List<String> getSources();

    @Query("DELETE FROM Armour")
    public abstract void deleteAll();
}
