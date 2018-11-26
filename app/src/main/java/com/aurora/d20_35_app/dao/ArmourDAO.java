package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Armour;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ArmourDAO {

    @Query("SELECT * FROM Armour")
    List<Armour> getArmour();

    @Query("SELECT DISTINCT Source FROM Armour")
    List<String> getSources();
}
