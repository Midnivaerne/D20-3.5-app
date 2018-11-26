package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.RulesSkills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RulesSkillsDAO {

    @Query("SELECT * FROM RulesSkills")
    List<RulesSkills> getRulesSkills();

    @Query("SELECT COUNT(*) from RulesSkills")
    int countRulesSkills();

    @Insert
    void insertAll(RulesSkills... rulesSkills);

    @Delete
    void delete(RulesSkills rulesSkills);
}
