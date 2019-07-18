package com.aurora.core.database;

import lombok.Getter;
import lombok.Setter;

import com.aurora.core.models.Translations;
import com.aurora.core.models.helpers.HeroChild;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.RaceTemplates;
import com.aurora.core.models.settingspecific.Races;
import com.aurora.core.models.settingspecific.Skills;
import com.aurora.core.models.usables.Armour;
import com.aurora.core.models.usables.WeaponSubtype;
import com.aurora.core.models.usables.WeaponType;
import com.aurora.core.models.usables.Weapons;
import com.aurora.core.models.userdata.HeroArmour;
import com.aurora.core.models.userdata.HeroEquipment;
import com.aurora.core.models.userdata.HeroPlayer;
import com.aurora.core.models.userdata.HeroWeapons;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesEnum;

public enum DbSettingColumns implements DbColumnNamesMethods<DbSettingColumns, Item> {

  ////////////////////////////////////////////////////////////////
  //////////////////////////  Databases  ////////////////////////

  /**
   * Data more or less depending on setting.
   */
  /////////////////////////////////////////////////////////////
  //////////////////////////  ITEM  //////////////////////////
  COL_ITEM_ID(DbColumnNames.ITEM_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setItemID(Integer.valueOf(data));
    }
  },
  COL_ITEM_NAME(DbColumnNames.ITEM_NAME_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setName(data);
    }
  },
  COL_SOURCE(DbColumnNames.SOURCE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      item.setSource(data);
    }
  },
  COL_ID_AS_NAME_BACKUP(DbColumnNames.ID_AS_NAME_BACKUP_COLUMN_NAME, false) {
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

  ////////////////////////////////////////////////////////////////
  ////////////////////////  WEAPON_TYPE  //////////////////////////
  COL_WEAPON_TYPE_CAN_HAVE_AMMO(DbColumnNames.WEAPON_TYPE_CAN_HAVE_AMMO_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((WeaponType) item).setCanHaveAmmo(Boolean.valueOf(data));
    }
  },
  COL_WEAPON_TYPE_IS_AMMO(DbColumnNames.WEAPON_TYPE_IS_AMMO_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((WeaponType) item).setIsAmmo(Boolean.valueOf(data));
    }
  },
  ////////////////////////////////////////////////////////////////
  //////////////////////  WEAPON_SUBTYPE  ////////////////////////
  COL_WEAPON_SUBTYPE_USED_AMMO_TYPE_ID(DbColumnNames.WEAPON_SUBTYPE_USED_AMMO_TYPE_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((WeaponSubtype) item).setUsedAmmoTypeId(Integer.valueOf(data));
    }
  },

  ////////////////////////////////////////////////////////////////
  ///////////////////////  WEAPON_SPECIALS  ///////////////////////

  ////////////////////////////////////////////////////////////////
  //////////////////////////  WEAPONS  //////////////////////////
  COL_WEAPON_TYPE_PARENT_ID(DbColumnNames.WEAPON_TYPE_PARENT_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Weapons) item).setWeaponTypeId(Integer.valueOf(data));
    }
  },
  COL_WEAPON_SUBTYPE_PARENT_ID(DbColumnNames.WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Weapons) item).setWeaponSubtypeId(Integer.valueOf(data));
    }
  },

  //////////////////////////////////////////////////////////////
  //////////////////////////  ARMOUR  //////////////////////////
  COL_ARMOUR_PRICE(DbColumnNames.ARMOUR_PRICE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourPrice(data);
    }
  },
  COL_ARMOUR_DEFLECTION(DbColumnNames.ARMOUR_DEFLECTION_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourDeflection(data);
    }
  },
  ColArmourMaxDexterityBonus(DbColumnNames.ARMOUR_MAX_DEXTERITY_BONUS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMaxDexterityBonus(data);
    }
  },
  COL_ARMOUR_PENALTY(DbColumnNames.ARMOUR_PENALTY_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourPenalty(data);
    }
  },
  COL_ARMOUR_ARCANE_FAILURE(DbColumnNames.ARMOUR_ARCANE_FAILURE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourArcaneFailure(data);
    }
  },
  COL_ARMOUR_MAX_SPEED(DbColumnNames.ARMOUR_MAX_SPEED_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMaxSpeed(data);
    }
  },
  COL_ARMOUR_WEIGHT(DbColumnNames.ARMOUR_WEIGHT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourWeight(data);
    }
  },
  COL_ARMOUR_SPECIAL_PROPERTIES(DbColumnNames.ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourSpecialProperties(data);
    }
  },
  COL_ARMOUR_MATERIAL(DbColumnNames.ARMOUR_MATERIAL_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMaterial(data);
    }
  },
  COL_ARMOUR_MAGIC_IMPROVEMENTS(DbColumnNames.ARMOUR_MAGIC_IMPROVEMENTS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Armour) item).setArmourMagicImprovements(data);
    }
  },

  //////////////////////////////////////////////////////////////////
  //////////////////////////  EQUIPMENT  //////////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  FEATS  //////////////////////////

  ////////////////////////////////////////////////////////////////
  //////////////////////////  CLASSES  //////////////////////////

  ///////////////////////////////////////////////////////////////
  ////////////////////////  HERO_PLAYER PLAYER /////////////////////////

  ///////////////////////////////////////////////////////////////
  ////////////////////////  HERO_PLAYER HERO /////////////////////////
  COL_HERO_PARENT_HERO_ID(DbColumnNames.HERO_PARENT_HERO_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroChild) item).setHeroParentHeroId(Integer.valueOf(data));
    }
  },

  //////////////////////////////////////////////////////////////////
  //////////////////////  HERO_PLAYER DESCRIPTION  ///////////////////////
  COL_HERO_PLAYER(DbColumnNames.HERO_PLAYER_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroPlayer(data);
    }
  },

  COL_HERO_CLASS_AND_LEVEL(DbColumnNames.HERO_CLASS_ID_LIST_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroClassIdList(data);
    }
  },

  COL_HERO_RACE(DbColumnNames.HERO_RACE_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroRaceId(data);
    }
  },
  COL_HERO_ALIGNMENT(DbColumnNames.HERO_ALIGNMENT_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAlignmentId(Integer.valueOf(data));
    }
  },
  COL_HERO_DEITY(DbColumnNames.HERO_DEITY_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroDeityId(Integer.valueOf(data));
    }
  },
  COL_HERO_SIZE(DbColumnNames.HERO_SIZE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroSizeId(Integer.valueOf(data));
    }
  },
  COL_HERO_AGE(DbColumnNames.HERO_AGE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroAge(Integer.valueOf(data));
    }
  },
  COL_HERO_GENDER(DbColumnNames.HERO_GENDER_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroGender(data);
    }
  },
  COL_HERO_HEIGHT(DbColumnNames.HERO_HEIGHT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroHeight(data);
    }
  },
  COL_HERO_WEIGHT(DbColumnNames.HERO_WEIGHT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroWeight(data);
    }
  },
  COL_HERO_EYES(DbColumnNames.HERO_EYES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroEyes(data);
    }
  },
  COL_HERO_HAIR(DbColumnNames.HERO_HAIR_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroHair(data);
    }
  },
  COL_HERO_SKIN(DbColumnNames.HERO_SKIN_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroDescription().setHeroSkin(data);
    }
  },
  COL_HERO_HP(DbColumnNames.HERO_HIT_POINTS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroHitPoints(data);
    }
  },
  COL_HERO_STR(DbColumnNames.HERO_ABILITY_SCORE_STR_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreStr(Integer.valueOf(data));
    }
  },
  COL_HERO_DEX(DbColumnNames.HERO_ABILITY_SCORE_DEX_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreDex(Integer.valueOf(data));
    }
  },
  COL_HERO_CON(DbColumnNames.HERO_ABILITY_SCORE_CON_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreCon(Integer.valueOf(data));
    }
  },
  COL_HERO_INT(DbColumnNames.HERO_ABILITY_SCORE_INT_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreInt(Integer.valueOf(data));
    }
  },
  COL_HERO_WIS(DbColumnNames.HERO_ABILITY_SCORE_WIS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreWis(Integer.valueOf(data));
    }
  },
  COL_HERO_CHA(DbColumnNames.HERO_ABILITY_SCORE_CHA_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroValues().setHeroAbilityScoreCha(Integer.valueOf(data));
    }
  },
  COL_HERO_SKILLS(DbColumnNames.HERO_SKILLS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroPlayer) item).getHeroSkills().setHeroSkills(data);
    }
  },

  /////////////////////////////////////////////////////////////////////
  //////////////////////  HERO_PLAYER WEAPONS  ///////////////////////

  COL_HERO_WEAPONS_PARENT_WEAPON_ID(DbColumnNames.HERO_WEAPON_PARENT_WEAPON_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroWeapons) item).setWeaponId(Integer.valueOf(data));
    }
  },

  /////////////////////////////////////////////////////////////////////
  //////////////////////  HERO_PLAYER ARMOUR  ///////////////////////

  COL_HERO_WEAPONS_PARENT_ARMOUR_ID(DbColumnNames.HERO_ARMOUR_PARENT_ARMOUR_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroArmour) item).setArmourId(Integer.valueOf(data));
    }
  },

  /////////////////////////////////////////////////////////////////////
  //////////////////////  HERO_PLAYER ARMOUR  ///////////////////////

  COL_HERO_EQUIPMENT_PARENT_ARMOUR_ID(DbColumnNames.HERO_EQUIPMENT_PARENT_EQUIPMENT_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroEquipment) item).setEquipmentId(Integer.valueOf(data));
    }
  },

  COL_HERO_EQUIPMENT_WORN_PLACE(DbColumnNames.HERO_EQUIPMENT_WORN_PLACE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((HeroEquipment) item).setWornPlace((PlayerCharacterWornEquipmentPlacesEnum.valueOf(data)));
    }
  },

  /////////////////////////////////////////////////////////////////
  //////////////////////////  MONSTERS  //////////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  RACES  //////////////////////////
  COL_RACE_DESCRIPTION(DbColumnNames.RACE_DESCRIPTION_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceDescription(data);
    }
  },
  COL_RACE_ATTRIBUTE_MODIFIERS(DbColumnNames.RACE_ATTRIBUTE_MODIFIERS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceAttributeModifiers(data);
    }
  },
  COL_RACE_SIZE(DbColumnNames.RACE_SIZE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceSize(data);
    }
  },
  COL_RACE_SPEED(DbColumnNames.RACE_SPEED_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceSpeed(data);
    }
  },
  COL_RACE_FEATS(DbColumnNames.RACE_FEATS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceFeats(data);
    }
  },
  COL_RACE_SKILLS(DbColumnNames.RACE_SKILLS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceSkills(data);
    }
  },
  COL_RACE_LANGUAGES(DbColumnNames.RACE_LANGUAGES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setRaceLanguages(data);
    }
  },
  COL_FAVOURITE_CLASS(DbColumnNames.RACE_FAVOURITE_CLASS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Races) item).setFavouriteClass(data);
    }
  },
  ///////////////////////////////////////////////////////////////////////
  //////////////////////////  RACE TEMPLATES  //////////////////////////
  COL_RACE_TEMPALTE_DESCRIPTION(DbColumnNames.RACE_TEMPLATE_DESCRIPTION_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateDescription(data);
    }
  },
  COL_RACE_TEMPALTE_ATTRIBUTE_MODIFIERS(DbColumnNames.RACE_TEMPLATE_ATTRIBUTE_MODIFIERS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateAttributeModifiers(data);
    }
  },
  COL_RACE_TEMPALTE_SIZE(DbColumnNames.RACE_TEMPLATE_SIZE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateSize(data);
    }
  },
  COL_RACE_TEMPALTE_SPEED(DbColumnNames.RACE_TEMPLATE_SPEED_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateSpeed(data);
    }
  },
  COL_RACEv_TEMPALTE_FEATS(DbColumnNames.RACE_TEMPLATE_FEATS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateFeats(data);
    }
  },
  COL_RACE_TEMPALTE_SKILLS(DbColumnNames.RACE_TEMPLATE_SKILLS_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateSkills(data);
    }
  },
  COL_RACE_TEMPALTE_LANGUAGES(DbColumnNames.RACE_TEMPLATE_LANGUAGES_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((RaceTemplates) item).setRaceTemplateLanguages(data);
    }
  },

  ///////////////////////////////////////////////////////////////
  //////////////////////////  SKILLS  //////////////////////////
  COL_SKILLS_ATTRIBUTE(DbColumnNames.SKILL_ATTRIBUTE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillAttribute(data);
    }
  },
  COL_SKILLS_EXCLUSIVE(DbColumnNames.SKILL_EXCLUSIVE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillExclusive(data);
    }
  },
  COL_SKILLS_ARMOUR_PENALTY(DbColumnNames.SKILL_USE_ARMOUR_PENALTY_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillUseArmourPenalty(data);
    }
  },
  COL_SKILLS_CAN_HAVE_SUBSKILL(DbColumnNames.SKILL_CAN_HAVE_SUBSKILL_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillCanHaveSubskills(data);
    }
  },
  COL_SKILLS_SUBSKILL(DbColumnNames.SKILL_SUBSKILL_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillSubskill(data);
    }
  },
  COL_SKILLS_IMPROVES_OTHER(DbColumnNames.SKILL_IMPROVES_OTHER_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillImprovesOther(data);
    }
  },
  COL_SKILLS_OTHER_TO_IMPROVE(DbColumnNames.SKILL_OTHER_TO_IMPROVE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Skills) item).setSkillOtherToImprove(data);
    }
  },

  ///////////////////////////////////////////////////////////////
  //////////////////////////  SPELLS  //////////////////////////

  //////////////////////////////////////////////////////////////
  //////////////////////////  DEITIES  //////////////////////////

  ////////////////////////////////////////////////////////////////
  //////////////////////////  TRANSLATIONS  //////////////////////
  COL_CATEGORY(DbColumnNames.CATEGORY_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Translations) item).setCategory(data);
    }
  },
  COL_LANGUAGE(DbColumnNames.LANGUAGE_COLUMN_NAME, false) {
    @Override
    public void setParameter(Item item, String data) {
      ((Translations) item).setLanguage(data);
    }
  },
  COL_TRANSLATION(DbColumnNames.TRANS_COLUMN_NAME, false) {
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

  DbSettingColumns(String columnName, boolean colBool) {
    this.columnName = columnName;
    this.columnIsUsed = colBool;
  }

  @Override
  public String toString() {
    return this.columnName;
  }

}
