package com.aurora.d20_35_app.models.typeHelpers;

import com.aurora.d20_35_app.database.DBTableNames;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.constants.RulesAlignments;
import com.aurora.d20_35_app.models.constants.RulesCombat;
import com.aurora.d20_35_app.models.constants.RulesSizes;
import com.aurora.d20_35_app.models.constants.RulesSkills;
import com.aurora.d20_35_app.models.helpers.Rules;
import java.util.List;
import java.util.Map;

public enum RulesType implements CoreTypeHelper<RulesType, Rules> {
    /**
     * RulesSizes
     */
    RULES_SIZES(DBTableNames.RULES_SIZES) {
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
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.rulesSizesDAO().insertAll(databaseHolder.RULES_SIZES_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RULES_SIZES_LIST.addAll(databaseHolder.rulesSizesDAO().getItems());
            for (RulesSizes rulesSizes : databaseHolder.RULES_SIZES_LIST) {
                databaseHolder.RULES_SIZES_MAP.put(rulesSizes.getItemID(), rulesSizes);
            }
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesSizesDAO();
        }
    },
    /**
     * RulesAlignments
     */
    RULES_ALIGNMENTS(DBTableNames.RULES_ALIGNMENTS) {
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
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.rulesAlignmentsDAO().insertAll(databaseHolder.RULES_ALIGNMENTS_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RULES_ALIGNMENTS_LIST.addAll(databaseHolder.rulesAlignmentsDAO().getItems());
            for (RulesAlignments rulesAlignments : databaseHolder.RULES_ALIGNMENTS_LIST) {
                databaseHolder.RULES_ALIGNMENTS_MAP.put(rulesAlignments.getItemID(), rulesAlignments);
            }
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesAlignmentsDAO();
        }
    },
    /**
     * RulesCombat
     */
    RULES_COMBAT(DBTableNames.RULES_COMBAT) {
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
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.rulesCombatDAO().insertAll(databaseHolder.RULES_COMBAT_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RULES_COMBAT_LIST.addAll(databaseHolder.rulesCombatDAO().getItems());
            for (RulesCombat rulesCombat : databaseHolder.RULES_COMBAT_LIST) {
                databaseHolder.RULES_COMBAT_MAP.put(rulesCombat.getItemID(), rulesCombat);
            }
        }

        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.rulesCombatDAO();
        }
    },
    /**
     * RulesSkills
     */
    RULES_SKILLS(DBTableNames.RULES_SKILLS) {
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
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.rulesSkillsDAO().insertAll(databaseHolder.RULES_SKILLS_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RULES_SKILLS_LIST.addAll(databaseHolder.rulesSkillsDAO().getItems());
            for (RulesSkills rulesSkills : databaseHolder.RULES_SKILLS_LIST) {
                databaseHolder.RULES_SKILLS_MAP.put(rulesSkills.getItemID(), rulesSkills);
            }
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

}
