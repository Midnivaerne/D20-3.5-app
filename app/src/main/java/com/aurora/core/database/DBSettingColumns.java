package com.aurora.core.database;

import lombok.Getter;
import lombok.Setter;

import com.aurora.core.models.Translations;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingSpecific.RaceTemplates;
import com.aurora.core.models.settingSpecific.Races;
import com.aurora.core.models.usables.Armour;
import com.aurora.core.models.userData.HeroPlayer;

public enum DBSettingColumns implements DBColumnNamesMethods<DBSettingColumns, Item> {

  ////////////////////////////////////////////////////////////////
  //////////////////////////  Databases  ////////////////////////

  /**
   * Data more or less depending on setting
   */
  /////////////////////////////////////////////////////////////
  //////////////////////////  ITEM  //////////////////////////
  COL_ITEM_ID(DBColumnNames.ITEM_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setItemID(Integer.valueOf(data));
    }
  },
  COL_ITEM_NAME(DBColumnNames.ITEM_NAME_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setName(data);
    }
  },
  COL_SOURCE(DBColumnNames.SOURCE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setSource(data);
    }
  },
  COL_ID_AS_NAME_BACKUP(DBColumnNames.ID_AS_NAME_BACKUP_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setIdAsNameBackup(data);
    }
  },

  //////////////////////////////////////////////////////////////////
  ///////////////////////  MATERIAL_TYPES  ////////////////////////

  //////////////////////////////////////////////////////////////////
  ////////////////////////  ENERGY_TYPES  /////////////////////////

  //////////////////////////////////////////////////////////////////
  ///////////////////////  SPECIAL_ATTACKS  ///////////////////////

  //////////////////////////////////////////////////////////////////
  //////////////////////  SPECIAL_QUALITIES  //////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  ARMOUR  //////////////////////////
  COL_ARMOUR_PRICE(DBColumnNames.ARMOUR_PRICE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourPrice(data);
    }
  },
  COL_ARMOUR_DEFLECTION(DBColumnNames.ARMOUR_DEFLECTION_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourDeflection(data);
    }
  },
  ColArmourMaxDexterityBonus(DBColumnNames.ARMOUR_MAX_DEXTERITY_BONUS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMaxDexterityBonus(data);
    }
  },
  COL_ARMOUR_PENALTY(DBColumnNames.ARMOUR_PENALTY_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourPenalty(data);
    }
  },
  COL_ARMOUR_ARCANE_FAILURE(DBColumnNames.ARMOUR_ARCANE_FAILURE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourArcaneFailure(data);
    }
  },
  COL_ARMOUR_MAX_SPEED(DBColumnNames.ARMOUR_MAX_SPEED_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMaxSpeed(data);
    }
  },
  COL_ARMOUR_WEIGHT(DBColumnNames.ARMOUR_WEIGHT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourWeight(data);
    }
  },
  COL_ARMOUR_SPECIAL_PROPERTIES(DBColumnNames.ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourSpecialProperties(data);
    }
  },
  COL_ARMOUR_MATERIAL(DBColumnNames.ARMOUR_MATERIAL_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMaterial(data);
    }
  },
  COL_ARMOUR_MAGIC_IMPROVEMENTS(DBColumnNames.ARMOUR_MAGIC_IMPROVEMENTS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMagicImprovements(data);
    }
  },
  ////////////////////////////////////////////////////////////////
  //////////////////////////  CLASSES  //////////////////////////

  //////////////////////////////////////////////////////////////////
  //////////////////////////  EQUIPMENT  //////////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  FEATS  //////////////////////////

  ///////////////////////////////////////////////////////////////
  ////////////////////////  HERO_PLAYER PLAYER /////////////////////////

  //////////////////////////////////////////////////////////////////
  //////////////////////  HERO_PLAYER DESCRIPTION  ///////////////////////
  COL_HERO_PLAYER(DBColumnNames.HERO_PLAYER_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroPlayer(data);
    }
  },

  COL_HERO_CLASS_AND_LEVEL(DBColumnNames.HERO_CLASS_ID_LIST_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroClassIdList(data);
    }
  },

  COL_HERO_RACE(DBColumnNames.HERO_RACE_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroRaceId(data);
    }
  },
  COL_HERO_ALIGNMENT(DBColumnNames.HERO_ALIGNMENT_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAlignmentId(Integer.valueOf(data));
    }
  },
  COL_HERO_DEITY(DBColumnNames.HERO_DEITY_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroDeityId(Integer.valueOf(data));
    }
  },
  COL_HERO_SIZE(DBColumnNames.HERO_SIZE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroSizeId(Integer.valueOf(data));
    }
  },
  COL_HERO_AGE(DBColumnNames.HERO_AGE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroAge(Integer.valueOf(data));
    }
  },
  COL_HERO_GENDER(DBColumnNames.HERO_GENDER_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroGender(data);
    }
  },
  COL_HERO_HEIGHT(DBColumnNames.HERO_HEIGHT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroHeight(data);
    }
  },
  COL_HERO_WEIGHT(DBColumnNames.HERO_WEIGHT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroWeight(data);
    }
  },
  COL_HERO_EYES(DBColumnNames.HERO_EYES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroEyes(data);
    }
  },
  COL_HERO_HAIR(DBColumnNames.HERO_HAIR_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroHair(data);
    }
  },
  COL_HERO_SKIN(DBColumnNames.HERO_SKIN_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroSkin(data);
    }
  },
  COL_HERO_HP(DBColumnNames.HERO_HIT_POINTS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroHitPoints(data);
    }
  },
  COL_HERO_STR(DBColumnNames.HERO_ABILITY_SCORE_STR_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreStr(Integer.valueOf(data));
    }
  },
  COL_HERO_DEX(DBColumnNames.HERO_ABILITY_SCORE_DEX_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreDex(Integer.valueOf(data));
    }
  },
  COL_HERO_CON(DBColumnNames.HERO_ABILITY_SCORE_CON_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreCon(Integer.valueOf(data));
    }
  },
  COL_HERO_INT(DBColumnNames.HERO_ABILITY_SCORE_INT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreInt(Integer.valueOf(data));
    }
  },
  COL_HERO_WIS(DBColumnNames.HERO_ABILITY_SCORE_WIS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreWis(Integer.valueOf(data));
    }
  },
  COL_HERO_CHA(DBColumnNames.HERO_ABILITY_SCORE_CHA_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreCha(Integer.valueOf(data));
    }
  },

  /////////////////////////////////////////////////////////////////
  //////////////////////////  MONSTERS  //////////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  RACES  //////////////////////////
  COL_RACE_DESCRIPTION(DBColumnNames.RACE_DESCRIPTION_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceDescription(data);
    }
  },
  COL_RACE_ATTRIBUTE_MODIFIERS(DBColumnNames.RACE_ATTRIBUTE_MODIFIERS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceAttributeModifiers(data);
    }
  },
  COL_RACE_SIZE(DBColumnNames.RACE_SIZE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceSize(data);
    }
  },
  COL_RACE_SPEED(DBColumnNames.RACE_SPEED_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceSpeed(data);
    }
  },
  COL_RACE_FEATS(DBColumnNames.RACE_FEATS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceFeats(data);
    }
  },
  COL_RACE_SKILLS(DBColumnNames.RACE_SKILLS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceSkills(data);
    }
  },
  COL_RACE_LANGUAGES(DBColumnNames.RACE_LANGUAGES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceLanguages(data);
    }
  },
  COL_FAVOURITE_CLASS(DBColumnNames.RACE_FAVOURITE_CLASS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setFavouriteClass(data);
    }
  },
  ///////////////////////////////////////////////////////////////////////
  //////////////////////////  RACE TEMPLATES  //////////////////////////
  COL_RACE_TEMPALTE_DESCRIPTION(DBColumnNames.RACE_TEMPLATE_DESCRIPTION_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateDescription(data);
    }
  },
  COL_RACE_TEMPALTE_ATTRIBUTE_MODIFIERS(DBColumnNames.RACE_TEMPLATE_ATTRIBUTE_MODIFIERS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateAttributeModifiers(data);
    }
  },
  COL_RACE_TEMPALTE_SIZE(DBColumnNames.RACE_TEMPLATE_SIZE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateSize(data);
    }
  },
  COL_RACE_TEMPALTE_SPEED(DBColumnNames.RACE_TEMPLATE_SPEED_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateSpeed(data);
    }
  },
  COL_RACEv_TEMPALTE_FEATS(DBColumnNames.RACE_TEMPLATE_FEATS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateFeats(data);
    }
  },
  COL_RACE_TEMPALTE_SKILLS(DBColumnNames.RACE_TEMPLATE_SKILLS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateSkills(data);
    }
  },
  COL_RACE_TEMPALTE_LANGUAGES(DBColumnNames.RACE_TEMPLATE_LANGUAGES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateLanguages(data);
    }
  },
  ///////////////////////////////////////////////////////////////
  //////////////////////////  SKILLS  //////////////////////////

  ///////////////////////////////////////////////////////////////
  //////////////////////////  SPELLS  //////////////////////////

  ////////////////////////////////////////////////////////////////
  //////////////////////////  WEAPONS  //////////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  DEITIES  //////////////////////////

  ////////////////////////////////////////////////////////////////
  //////////////////////////  TRANSLATIONS  //////////////////////
  COL_CATEGORY(DBColumnNames.CATEGORY_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Translations) item).setCategory(data);
    }
  },
  COL_LANGUAGE(DBColumnNames.LANGUAGE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Translations) item).setLanguage(data);
    }
  },
  COL_TRANSLATION(DBColumnNames.TRANS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Translations) item).setTrans(data);
    }
  };

  @Getter
  private String columnName;

  @Getter
  @Setter
  private Boolean columnIsUsed;

  DBSettingColumns(String columnName, boolean colBool) {
    this.columnName = columnName;
    this.columnIsUsed = colBool;
  }

  @Override
  public String toString() {
    return this.columnName;
  }

}
