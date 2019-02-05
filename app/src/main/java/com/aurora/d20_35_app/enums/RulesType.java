package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.constants.RulesAlignments;
import com.aurora.d20_35_app.models.constants.RulesSizes;
import com.aurora.d20_35_app.models.helpers.Rules;
import com.aurora.d20_35_app.models.constants.RulesCombat;
import com.aurora.d20_35_app.models.constants.RulesSkills;
import com.aurora.d20_35_app.utils.database.DatabaseHolder;

import java.util.List;
import java.util.Map;

public enum RulesType {
    /**
     * RulesSizes
     */
    RULES_SIZES("RulesSizes") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_SIZES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_SIZES_MAP;
        }

        @Override
        public Rules getNewObject() {
            return new RulesSizes();
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesSizesDAO();
        }
    },
    /**
     * RulesAlignments
     */
    RULES_ALIGNMENTS("RulesAlignments") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_ALIGNMENTS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_ALIGNMENTS_MAP;
        }

        @Override
        public Rules getNewObject() {
            return new RulesAlignments();
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesAlignmentsDAO();
        }
    },
    /**
     * RulesCombat
     */
    RULES_COMBAT("RulesCombat") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_COMBAT_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_COMBAT_MAP;
        }

        @Override
        public Rules getNewObject() {
            return new RulesCombat();
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesCombatDAO();
        }
    },
    /**
     * RulesSkills
     */
    RULES_SKILLS("RulesSkills") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_SKILLS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RULES_SKILLS_MAP;
        }

        @Override
        public Rules getNewObject() {
            return new RulesSkills();
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesSkillsDAO();
        }
    };

    private String rulesType;

    RulesType(String rulesType) {
        this.rulesType = rulesType;
    }

    @Override
    public String toString() {
        return this.rulesType;
    }

    public static boolean contains(String name) {
        for (RulesType it : RulesType.values()) {
            if (it.toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public abstract List getDatabaseList(DatabaseHolder databaseHolder);

    public abstract Map getDatabaseMap(DatabaseHolder databaseHolder);

    public abstract Rules getNewObject();

    public abstract BaseDAO<Rules> getDAO(DatabaseHolder databaseHolder);

    public List<Rules> getAllFromDatabase(DatabaseHolder databaseHolder) {
        return getDAO(databaseHolder).getItems();
    }
}
