package com.aurora.core.models.typehelpers;

import java.util.List;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DbTableNames;
import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.constants.BaseQualities;
import com.aurora.core.models.constants.CoreStates;
import com.aurora.core.models.constants.RulesCombat;
import com.aurora.core.models.constants.RulesSkills;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.helpers.Rules;

public enum RulesType implements CoreTypeHelper<RulesType, Rules> {
  /**
   * Sizes.
   */
  SIZES(DbTableNames.SIZES) {
    @Override
    public List getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.sizesList;
    }

    @Override
    public Map getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.sizesMap;
    }

    @Override
    public Rules getNewObject() {
      return new Sizes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.rulesSizesDaO().insertAll(databaseHolder.sizesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.sizesList.addAll(databaseHolder.rulesSizesDaO().getAllObjectsAsObject());
      for (Sizes sizes : databaseHolder.sizesList) {
        databaseHolder.sizesMap.put(sizes.getItemID(), sizes);
      }
    }

    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesSizesDaO();
    }
  },
  /**
   * Alignments.
   */
  ALIGNMENTS(DbTableNames.ALIGNMENTS) {
    @Override
    public List getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.alignmentsList;
    }

    @Override
    public Map getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.alignmentsMap;
    }

    @Override
    public Rules getNewObject() {
      return new Alignments();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.rulesAlignmentsDaO().insertAll(databaseHolder.alignmentsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.alignmentsList.addAll(databaseHolder.rulesAlignmentsDaO().getAllObjectsAsObject());
      for (Alignments alignments : databaseHolder.alignmentsList) {
        databaseHolder.alignmentsMap.put(alignments.getItemID(), alignments);
      }
    }

    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesAlignmentsDaO();
    }
  },
  CORE_STATES(DbTableNames.CORE_STATES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.coreStatesDaO();
    }

    @Override
    public List<CoreStates> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.coreStatesList;
    }

    @Override
    public Map<Integer, CoreStates> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.coreStatesMap;
    }

    @Override
    public Rules getNewObject() {
      return new CoreStates();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.coreStatesDaO().insertAll(databaseHolder.coreStatesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.coreStatesList.addAll(databaseHolder.coreStatesDaO().getAllObjectsAsObject());
      for (CoreStates coreStates : databaseHolder.coreStatesList) {
        databaseHolder.coreStatesMap.put(coreStates.getItemID(), coreStates);
      }
    }
  },
  BASE_QUALITIES(DbTableNames.BASE_QUALITIES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.baseQualitiesDaO();
    }

    @Override
    public List<BaseQualities> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.baseQualitiesList;
    }

    @Override
    public Map<Integer, BaseQualities> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.baseQualitiesMap;
    }

    @Override
    public Rules getNewObject() {
      return new BaseQualities();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.baseQualitiesDaO().insertAll(databaseHolder.baseQualitiesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.baseQualitiesList.addAll(databaseHolder.baseQualitiesDaO().getAllObjectsAsObject());
      for (BaseQualities baseQualities : databaseHolder.baseQualitiesList) {
        databaseHolder.baseQualitiesMap.put(baseQualities.getItemID(), baseQualities);
      }
    }
  },
  /**
   * RulesCombat.
   */
  RULES_COMBAT(DbTableNames.RULES_COMBAT) {
    @Override
    public List getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesCombatList;
    }

    @Override
    public Map getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesCombatMap;
    }

    @Override
    public Rules getNewObject() {
      return new RulesCombat();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.rulesCombatDaO().insertAll(databaseHolder.rulesCombatList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.rulesCombatList.addAll(databaseHolder.rulesCombatDaO().getAllObjectsAsObject());
      for (RulesCombat rulesCombat : databaseHolder.rulesCombatList) {
        databaseHolder.rulesCombatMap.put(rulesCombat.getItemID(), rulesCombat);
      }
    }

    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesCombatDaO();
    }
  },
  /**
   * RulesSkills.
   */
  RULES_SKILLS(DbTableNames.RULES_SKILLS) {
    @Override
    public List getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesSkillsList;
    }

    @Override
    public Map getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesSkillsMap;
    }

    @Override
    public Rules getNewObject() {
      return new RulesSkills();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.rulesSkillsDaO().insertAll(databaseHolder.rulesSkillsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.rulesSkillsList.addAll(databaseHolder.rulesSkillsDaO().getAllObjectsAsObject());
      for (RulesSkills rulesSkills : databaseHolder.rulesSkillsList) {
        databaseHolder.rulesSkillsMap.put(rulesSkills.getItemID(), rulesSkills);
      }
    }

    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.rulesSkillsDaO();
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
