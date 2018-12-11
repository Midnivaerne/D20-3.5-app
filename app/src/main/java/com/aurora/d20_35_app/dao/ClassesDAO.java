package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Classes;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class ClassesDAO implements BaseDAO<Classes> {

    @Query("SELECT * FROM Classes")
    public abstract List<Classes> getClasses();

    @Query("SELECT DISTINCT Source FROM Classes")
    public abstract List<String> getSources();

    @Query("DELETE FROM Classes")
    public abstract void deleteAll();
}
