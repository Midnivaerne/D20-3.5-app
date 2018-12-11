package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Monsters;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class MonstersDAO  implements BaseDAO<Monsters> {

    @Query("SELECT * FROM Monsters")
    public abstract List<Monsters> getMonsters();

    @Query("SELECT DISTINCT Source FROM Monsters")
    public abstract List<String> getSources();

    @Query("DELETE FROM Monsters")
    public abstract void deleteAll();
}
