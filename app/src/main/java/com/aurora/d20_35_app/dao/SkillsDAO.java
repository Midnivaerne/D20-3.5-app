package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Skills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class SkillsDAO  implements BaseDAO<Skills> {

    @Query("SELECT * FROM Skills")
    public abstract List<Skills> getSkills();

    @Query("SELECT DISTINCT Source FROM Skills")
    public abstract List<String> getSources();


    @Query("DELETE FROM Skills")
    public abstract void deleteAll();
}
