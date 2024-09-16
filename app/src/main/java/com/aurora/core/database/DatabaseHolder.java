package com.aurora.core.database;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.aurora.core.database.dao.DatabasesDaO;
import com.aurora.core.database.dao.TranslationsDaO;
import com.aurora.core.database.dao.constants.AlignmentsDaO;
import com.aurora.core.database.dao.constants.BaseQualitiesDaO;
import com.aurora.core.database.dao.constants.CoreStatesDaO;
import com.aurora.core.database.dao.constants.RulesCombatDaO;
import com.aurora.core.database.dao.constants.RulesSkillsDaO;
import com.aurora.core.database.dao.constants.SizesDaO;
import com.aurora.core.database.dao.settingspecific.ClassesDaO;
import com.aurora.core.database.dao.settingspecific.DeitiesDaO;
import com.aurora.core.database.dao.settingspecific.EnergyTypesDaO;
import com.aurora.core.database.dao.settingspecific.FeatsDaO;
import com.aurora.core.database.dao.settingspecific.HeroNpcDaO;
import com.aurora.core.database.dao.settingspecific.MaterialTypesDaO;
import com.aurora.core.database.dao.settingspecific.MonstersDaO;
import com.aurora.core.database.dao.settingspecific.RaceTemplatesDaO;
import com.aurora.core.database.dao.settingspecific.RacesDaO;
import com.aurora.core.database.dao.settingspecific.SkillsDaO;
import com.aurora.core.database.dao.settingspecific.SpecialAttacksDaO;
import com.aurora.core.database.dao.settingspecific.SpecialQualitiesDaO;
import com.aurora.core.database.dao.settingspecific.SpellsDaO;
import com.aurora.core.database.dao.usables.ArmourDaO;
import com.aurora.core.database.dao.usables.ArmourSpecificsDaO;
import com.aurora.core.database.dao.usables.ArmourSubtypeDaO;
import com.aurora.core.database.dao.usables.ArmourTypeDaO;
import com.aurora.core.database.dao.usables.EquipmentDaO;
import com.aurora.core.database.dao.usables.PriceDaO;
import com.aurora.core.database.dao.usables.WeaponSpecificsDaO;
import com.aurora.core.database.dao.usables.WeaponSubtypeDaO;
import com.aurora.core.database.dao.usables.WeaponTypeDaO;
import com.aurora.core.database.dao.usables.WeaponsDaO;
import com.aurora.core.database.dao.userdata.HeroArmourDaO;
import com.aurora.core.database.dao.userdata.HeroDescriptionDaO;
import com.aurora.core.database.dao.userdata.HeroEquipmentDaO;
import com.aurora.core.database.dao.userdata.HeroPlayerDaO;
import com.aurora.core.database.dao.userdata.HeroSkillsDaO;
import com.aurora.core.database.dao.userdata.HeroValuesDaO;
import com.aurora.core.database.dao.userdata.HeroWeaponsDaO;
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.Translations;
import com.aurora.core.database.models.constants.Alignments;
import com.aurora.core.database.models.constants.BaseQualities;
import com.aurora.core.database.models.constants.CoreStates;
import com.aurora.core.database.models.constants.RulesCombat;
import com.aurora.core.database.models.constants.RulesSkills;
import com.aurora.core.database.models.constants.Sizes;
import com.aurora.core.database.models.helpers.Rules;
import com.aurora.core.database.models.settingspecific.Classes;
import com.aurora.core.database.models.settingspecific.Deities;
import com.aurora.core.database.models.settingspecific.EnergyTypes;
import com.aurora.core.database.models.settingspecific.Feats;
import com.aurora.core.database.models.settingspecific.HeroNpc;
import com.aurora.core.database.models.settingspecific.MaterialTypes;
import com.aurora.core.database.models.settingspecific.Monsters;
import com.aurora.core.database.models.settingspecific.RaceTemplates;
import com.aurora.core.database.models.settingspecific.Races;
import com.aurora.core.database.models.settingspecific.Skills;
import com.aurora.core.database.models.settingspecific.SpecialAttacks;
import com.aurora.core.database.models.settingspecific.SpecialQualities;
import com.aurora.core.database.models.settingspecific.Spells;
import com.aurora.core.database.models.typehelpers.RulesType;
import com.aurora.core.database.models.usables.Armour;
import com.aurora.core.database.models.usables.ArmourSpecifics;
import com.aurora.core.database.models.usables.ArmourSubtype;
import com.aurora.core.database.models.usables.ArmourType;
import com.aurora.core.database.models.usables.Equipment;
import com.aurora.core.database.models.usables.Price;
import com.aurora.core.database.models.usables.WeaponSpecifics;
import com.aurora.core.database.models.usables.WeaponSubtype;
import com.aurora.core.database.models.usables.WeaponType;
import com.aurora.core.database.models.usables.Weapons;
import com.aurora.core.database.models.userdata.HeroArmour;
import com.aurora.core.database.models.userdata.HeroDescription;
import com.aurora.core.database.models.userdata.HeroEquipment;
import com.aurora.core.database.models.userdata.HeroPlayer;
import com.aurora.core.database.models.userdata.HeroSkills;
import com.aurora.core.database.models.userdata.HeroValues;
import com.aurora.core.database.models.userdata.HeroWeapons;

@Singleton
@Database(entities = {
    Databases.class,
    CoreStates.class, BaseQualities.class,
    RulesCombat.class, RulesSkills.class, Alignments.class, Sizes.class,
    EnergyTypes.class, MaterialTypes.class,
    SpecialAttacks.class, SpecialQualities.class,
    Feats.class, Skills.class, Spells.class,
    Price.class,
    WeaponType.class, WeaponSubtype.class, WeaponSpecifics.class, Weapons.class,
    ArmourType.class, ArmourSubtype.class, ArmourSpecifics.class, Armour.class,
    Equipment.class,
    Classes.class, Monsters.class, Races.class, RaceTemplates.class, Deities.class,
    HeroNpc.class, HeroPlayer.class,
    HeroDescription.class, HeroValues.class, HeroSkills.class, HeroWeapons.class, HeroArmour.class, HeroEquipment.class,
    Translations.class},
    version = 1, exportSchema = false)
@TypeConverters(DataTypeConverters.class)
@SuppressLint("UseSparseArrays")
public abstract class DatabaseHolder extends RoomDatabase {

  static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
    }
  };
  private static final String DATABASE_NAME = "database.db";
  private static DatabaseHolder INSTANCE;
  /**
   * Lists to hold database items.
   */
  public final List<Databases> databasesList = new ArrayList<>();
  public final List<Sizes> sizesList = new ArrayList<>();
  public final List<Alignments> alignmentsList = new ArrayList<>();
  public final List<RulesCombat> rulesCombatList = new ArrayList<>();
  public final List<RulesSkills> rulesSkillsList = new ArrayList<>();
  public final List<BaseQualities> baseQualitiesList = new ArrayList<>();
  public final List<CoreStates> coreStatesList = new ArrayList<>();
  public final List<EnergyTypes> energyTypesList = new ArrayList<>();
  public final List<MaterialTypes> materialTypesList = new ArrayList<>();
  public final List<SpecialAttacks> specialAttacksList = new ArrayList<>();
  public final List<SpecialQualities> specialQualitiesList = new ArrayList<>();
  /**
   * An array of races available for heroPlayer.
   */
  public final List<Races> racesList = new ArrayList<>();
  public final List<Classes> classesList = new ArrayList<>();
  public final List<Skills> skillsList = new ArrayList<>();
  public final List<Feats> featsList = new ArrayList<>();
  public final List<Price> priceList = new ArrayList<>();
  public final List<WeaponType> weaponTypeList = new ArrayList<>();
  public final List<WeaponSubtype> weaponSubtypeList = new ArrayList<>();
  public final List<WeaponSpecifics> weaponSpecificsList = new ArrayList<>();
  public final List<Weapons> weaponsList = new ArrayList<>();
  public final List<ArmourType> armourTypeList = new ArrayList<>();
  public final List<ArmourSubtype> armourSubtypeList = new ArrayList<>();
  public final List<ArmourSpecifics> armourSpecificsList = new ArrayList<>();
  public final List<Armour> armourList = new ArrayList<>();
  public final List<Equipment> equipmentList = new ArrayList<>();
  public final List<Spells> spellsList = new ArrayList<>();
  public final List<Monsters> monstersList = new ArrayList<>();
  public final List<RaceTemplates> raceTemplatesList = new ArrayList<>();
  public final List<HeroPlayer> heroesPlayerList = new ArrayList<>();
  public final List<HeroWeapons> heroesWeaponsList = new ArrayList<>();
  public final List<HeroArmour> heroesArmourList = new ArrayList<>();
  public final List<HeroEquipment> heroesEquipmentList = new ArrayList<>();
  public final List<HeroNpc> heroesNpcList = new ArrayList<>();
  public final List<Deities> deitiesList = new ArrayList<>();
  public final List<Translations> translationsList = new ArrayList<>();
  /**
   * Maps to hold database items with ids.
   */
  public final Map<Integer, Databases> databasesMap = new HashMap<>();
  ///////////////////////MAPS FOR RULES///////////////////////////
  public final Map<Integer, Sizes> sizesMap = new HashMap<>();
  public final Map<Integer, Alignments> alignmentsMap = new HashMap<>();
  public final Map<Integer, RulesCombat> rulesCombatMap = new HashMap<>();
  public final Map<Integer, RulesSkills> rulesSkillsMap = new HashMap<>();
  public final Map<Integer, BaseQualities> baseQualitiesMap = new HashMap<>();
  public final Map<Integer, CoreStates> coreStatesMap = new HashMap<>();
  public final Map<Integer, EnergyTypes> energyTypesMap = new HashMap<>();
  public final Map<Integer, MaterialTypes> materialTypesMap = new HashMap<>();
  public final Map<Integer, SpecialAttacks> specialAttacksMap = new HashMap<>();
  public final Map<Integer, SpecialQualities> specialQualitiesMap = new HashMap<>();
  /**
   * A map of races, by ID.
   */
  public final Map<Integer, Races> racesMap = new HashMap<>();
  public final Map<Integer, Classes> classesMap = new HashMap<>();
  public final Map<Integer, Skills> skillsMap = new HashMap<>();
  public final Map<Integer, Feats> featsMap = new HashMap<>();
  public final Map<Integer, Price> priceMap = new HashMap<>();
  public final Map<Integer, WeaponType> weaponTypeMap = new HashMap<>();
  public final Map<Integer, WeaponSubtype> weaponSubtypeMap = new HashMap<>();
  public final Map<Integer, WeaponSpecifics> weaponSpecificsMap = new HashMap<>();
  public final Map<Integer, Weapons> weaponsMap = new HashMap<>();
  public final Map<Integer, ArmourType> armourTypeMap = new HashMap<>();
  public final Map<Integer, ArmourSubtype> armourSubtypeMap = new HashMap<>();
  public final Map<Integer, ArmourSpecifics> armourSpecificsMap = new HashMap<>();
  public final Map<Integer, Armour> armourMap = new HashMap<>();

  ///////////////////////LISTS FOR SETTING///////////////////////////
  public final Map<Integer, Equipment> equipmentMap = new HashMap<>();
  public final Map<Integer, Spells> spellsMap = new HashMap<>();
  public final Map<Integer, Monsters> monstersMap = new HashMap<>();
  public final Map<Integer, RaceTemplates> raceTemplatesMap = new HashMap<>();
  public final Map<Integer, HeroPlayer> heroesPlayerMap = new HashMap<>();
  public final Map<Integer, HeroWeapons> heroesWeaponsMap = new HashMap<>();
  public final Map<Integer, HeroArmour> heroesArmourMap = new HashMap<>();
  public final Map<Integer, HeroEquipment> heroesEquipmentMap = new HashMap<>();
  public final Map<Integer, HeroNpc> heroesNpcMap = new HashMap<>();
  public final Map<Integer, Deities> deitiesMap = new HashMap<>();
  public final Map<Integer, Translations> translationsMap = new HashMap<>();
  ///////////////////////LISTS FOR RULES///////////////////////////
  @Getter
  @Setter
  private List<Rules> rulesList = new ArrayList<>();

  public static DatabaseHolder getDatabaseHolder(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseHolder.class, DATABASE_NAME).addCallback(new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          INSTANCE.onCreateRulesList();

        }
      }).build();
    }
    return INSTANCE;
  }

  public static void destroyInstance() {
    INSTANCE = null;
  }

  public abstract DatabasesDaO databasesDaO();

  ////////////////////////////////////////////////////////////////////////////
  public abstract SizesDaO rulesSizesDaO();

  public abstract AlignmentsDaO rulesAlignmentsDaO();

  public abstract RulesSkillsDaO rulesSkillsDaO();

  public abstract RulesCombatDaO rulesCombatDaO();

  public abstract BaseQualitiesDaO baseQualitiesDaO();

  public abstract CoreStatesDaO coreStatesDaO();

  ////////////////////////////////////////////////////////////////////////////
  ///////////////////////MAPS FOR SETTING///////////////////////////
  public abstract EnergyTypesDaO energyTypesDaO();

  public abstract MaterialTypesDaO materialTypesDaO();

  public abstract SpecialAttacksDaO specialAttacksDaO();

  public abstract SpecialQualitiesDaO specialQualitiesDaO();

  public abstract ClassesDaO classesDaO();

  public abstract FeatsDaO featsDaO();

  public abstract HeroPlayerDaO heroPlayerDaO();

  public abstract HeroDescriptionDaO heroDescriptionDaO();

  public abstract HeroValuesDaO heroStatisticsAbilityScoresDaO();

  public abstract HeroSkillsDaO heroSkillsDaO();

  public abstract HeroWeaponsDaO heroWeaponsDaO();

  public abstract HeroArmourDaO heroArmourDaO();

  public abstract HeroEquipmentDaO heroEquipmentDaO();

  public abstract HeroNpcDaO heroNpcDaO();

  public abstract MonstersDaO monstersDaO();

  public abstract RacesDaO racesDaO();

  public abstract RaceTemplatesDaO raceTemplatesDaO();

  public abstract SkillsDaO skillsDaO();

  public abstract SpellsDaO spellsDaO();

  public abstract DeitiesDaO deitiesDaO();

  ///////////////////////MAPS FOR USABLES///////////////////////////
  public abstract PriceDaO priceDaO();

  public abstract WeaponTypeDaO weaponTypeDaO();

  public abstract WeaponSubtypeDaO weaponSubtypeDaO();

  public abstract WeaponSpecificsDaO weaponSpecificsDaO();

  public abstract WeaponsDaO weaponsDaO();

  public abstract ArmourTypeDaO armourTypeDaO();

  public abstract ArmourSubtypeDaO armourSubtypeDaO();

  public abstract ArmourSpecificsDaO armourSpecificsDaO();

  public abstract ArmourDaO armourDaO();

  public abstract EquipmentDaO equipmentDaO();

  ////////////////////////////////////////////////////////////////////////////
  public abstract TranslationsDaO translationsDaO();

  private void onCreateRulesList() {
    rulesList.clear();
    for (RulesType rt : RulesType.values()) {
      rulesList.add((Rules) rt.getNewObject());
    }
  }
  ////////////////////////////////////////////////////////////////////////////

}


