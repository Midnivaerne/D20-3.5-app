package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Hero;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface HeroDAO {

    @Query("SELECT * FROM Hero")
    List<Hero> getHero();

    @Query("SELECT DISTINCT Source FROM Hero")
    List<String> getSources();
}
