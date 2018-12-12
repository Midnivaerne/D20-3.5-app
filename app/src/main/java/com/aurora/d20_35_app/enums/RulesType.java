package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.helper.Rules;
import com.aurora.d20_35_app.models.RulesCombat;
import com.aurora.d20_35_app.models.RulesSkills;
import com.aurora.d20_35_app.utilsDatabase.DatabaseHolder;

import java.util.List;
import java.util.Map;

public enum RulesType {
    /**
     * RulesCombat
     */
    RulesCombat("RulesCombat") {
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
    },
    /**
     * RulesSkills
     */
    RulesSkills("RulesSkills") {
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

}
