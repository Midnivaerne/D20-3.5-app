package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.RulesCombat;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class RulesCombatDAO implements BaseDAO<RulesCombat> {

    @Query("SELECT * FROM RulesCombat")
    public abstract List<RulesCombat> getRulesCombat();

    @Query("SELECT COUNT(*) from RulesCombat")
    public abstract int countRulesCombat();

    @Override
    public List <String> getSources(){return null;}
}
