package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Monsters;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface MonstersDAO {

    @Query("SELECT * FROM Monsters")
    List<Monsters> getMonsters();
}
