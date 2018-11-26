package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.RulesCombat;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RulesCombatDAO {

    @Query("SELECT * FROM RulesCombat")
    List<RulesCombat> getRulesCombat();

    @Query("SELECT COUNT(*) from RulesCombat")
    int countRulesCombat();

    @Insert
    void insertAll(RulesCombat... rulesCombats);

    @Delete
    void delete(RulesCombat rulesCombat);
}
