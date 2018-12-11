package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.RulesSkills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class RulesSkillsDAO implements BaseDAO<RulesSkills> {

    @Query("SELECT * FROM RulesSkills")
    public abstract List<RulesSkills> getRulesSkills();

    @Query("SELECT COUNT(*) from RulesSkills")
    public abstract int countRulesSkills();

    @Override
    public List <String> getSources(){return null;}
}
