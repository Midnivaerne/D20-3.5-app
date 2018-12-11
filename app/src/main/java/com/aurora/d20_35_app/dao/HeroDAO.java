package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Hero;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class HeroDAO  implements BaseDAO<Hero> {

    @Query("SELECT * FROM Hero")
    public abstract List<Hero> getHero();

    @Query("SELECT DISTINCT Source FROM Hero")
    public abstract List<String> getSources();

    @Query("DELETE FROM Hero")
    public abstract void deleteAll();
}
