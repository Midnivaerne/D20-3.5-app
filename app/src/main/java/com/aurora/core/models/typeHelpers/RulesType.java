package com.aurora.core.models.typeHelpers;

import com.aurora.core.database.DBTableNames;
import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.constants.BaseQualities;
import com.aurora.core.models.constants.CoreStates;
import com.aurora.core.models.constants.RulesCombat;
import com.aurora.core.models.constants.RulesSkills;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.helpers.Rules;
import java.util.List;
import java.util.Map;

public enum RulesType implements CoreTypeHelper<RulesType, Rules> {
  /**
   * Sizes
   */
  SIZES(DBTableNames.SIZES) {
    @Override
    public List getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.SIZES_LIST;
    }

    @Override
    public Map getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.SIZES_MAP;
    }

    @Override
    public Rules getNewObject() {
      return new Sizes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.rulesSizesDAO().insertAll(databaseHolder.SIZES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.SIZES_LIST.addAll(databaseHolder.rulesSizesDAO().getAllObjectsAsObject());
      for (Sizes sizes : databaseHolder.SIZES_LIST) {
        databaseHolder.SIZES_MAP.put(sizes.getItemID(), sizes);
      }
    }

    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesSizesDAO();
    }
  },
  /**
   * Alignments
   */
  ALIGNMENTS(DBTableNames.ALIGNMENTS) {
    @Override
    public List getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.ALIGNMENTS_LIST;
    }

    @Override
    public Map getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.ALIGNMENTS_MAP;
    }

    @Override
    public Rules getNewObject() {
      return new Alignments();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.rulesAlignmentsDAO().insertAll(databaseHolder.ALIGNMENTS_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.ALIGNMENTS_LIST.addAll(databaseHolder.rulesAlignmentsDAO().getAllObjectsAsObject());
      for (Alignments alignments : databaseHolder.ALIGNMENTS_LIST) {
        databaseHolder.ALIGNMENTS_MAP.put(alignments.getItemID(), alignments);
      }
    }

    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesAlignmentsDAO();
    }
  },
  CORE_STATES(DBTableNames.CORE_STATES) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.coreStatesDAO();
    }

    @Override
    public List<CoreStates> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.CORE_STATES_LIST;
    }

    @Override
    public Map<Integer, CoreStates> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.CORE_STATES_MAP;
    }

    @Override
    public Rules getNewObject() {
      return new CoreStates();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.coreStatesDAO().insertAll(databaseHolder.CORE_STATES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.CORE_STATES_LIST.addAll(databaseHolder.coreStatesDAO().getAllObjectsAsObject());
      for (CoreStates coreStates : databaseHolder.CORE_STATES_LIST) {
        databaseHolder.CORE_STATES_MAP.put(coreStates.getItemID(), coreStates);
      }
    }
  },
  BASE_QUALITIES(DBTableNames.BASE_QUALITIES) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.baseQualitiesDAO();
    }

    @Override
    public List<BaseQualities> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.BASE_QUALITIES_LIST;
    }

    @Override
    public Map<Integer, BaseQualities> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.BASE_QUALITIES_MAP;
    }

    @Override
    public Rules getNewObject() {
      return new BaseQualities();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.baseQualitiesDAO().insertAll(databaseHolder.BASE_QUALITIES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.BASE_QUALITIES_LIST.addAll(databaseHolder.baseQualitiesDAO().getAllObjectsAsObject());
      for (BaseQualities baseQualities : databaseHolder.BASE_QUALITIES_LIST) {
        databaseHolder.BASE_QUALITIES_MAP.put(baseQualities.getItemID(), baseQualities);
      }
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
      databaseHolder.RULES_COMBAT_LIST.addAll(databaseHolder.rulesCombatDAO().getAllObjectsAsObject());
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
      databaseHolder.RULES_SKILLS_LIST.addAll(databaseHolder.rulesSkillsDAO().getAllObjectsAsObject());
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
