package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Spells;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class SpellsDAO  implements BaseDAO<Spells> {

    @Query("SELECT * FROM Spells")
    public abstract List<Spells> getSpells();

    @Query("SELECT DISTINCT Source FROM Spells")
    public abstract List<String> getSources();

    @Query("DELETE FROM Spells")
    public abstract void deleteAll();
}
