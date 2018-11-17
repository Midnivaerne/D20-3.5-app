package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Spells;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface SpellsDAO {

    @Query("SELECT * FROM Spells")
    List<Spells> getSpells();
}
