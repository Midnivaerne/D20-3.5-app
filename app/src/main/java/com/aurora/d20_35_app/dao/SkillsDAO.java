package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Skills;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface SkillsDAO {

    @Query("SELECT * FROM Skills")
    List<Skills> getSkills();
}
