package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Classes;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ClassesDAO {

    @Query("SELECT * FROM Classes")
    List<Classes> getClasses();

    @Query("SELECT DISTINCT Source FROM Classes")
    List<String> getSources();
}
