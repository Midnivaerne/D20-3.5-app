package com.aurora.core.database;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.aurora.core.dao.DatabasesDAO;
import com.aurora.core.dao.TranslationsDAO;
import com.aurora.core.dao.constants.AlignmentsDAO;
import com.aurora.core.dao.constants.BaseQualitiesDAO;
import com.aurora.core.dao.constants.CoreStatesDAO;
import com.aurora.core.dao.constants.RulesCombatDAO;
import com.aurora.core.dao.constants.RulesSkillsDAO;
import com.aurora.core.dao.constants.SizesDAO;
import com.aurora.core.dao.settingSpecific.ClassesDAO;
import com.aurora.core.dao.settingSpecific.DeitiesDAO;
import com.aurora.core.dao.settingSpecific.EnergyTypesDAO;
import com.aurora.core.dao.settingSpecific.FeatsDAO;
import com.aurora.core.dao.settingSpecific.HeroNPCDAO;
import com.aurora.core.dao.settingSpecific.MaterialTypesDAO;
import com.aurora.core.dao.settingSpecific.MonstersDAO;
import com.aurora.core.dao.settingSpecific.RaceTemplatesDAO;
import com.aurora.core.dao.settingSpecific.RacesDAO;
import com.aurora.core.dao.settingSpecific.SkillsDAO;
import com.aurora.core.dao.settingSpecific.SpecialAttacksDAO;
import com.aurora.core.dao.settingSpecific.SpecialQualitiesDAO;
import com.aurora.core.dao.settingSpecific.SpellsDAO;
import com.aurora.core.dao.usables.ArmourDAO;
import com.aurora.core.dao.usables.EquipmentDAO;
import com.aurora.core.dao.usables.WeaponsDAO;
import com.aurora.core.dao.userData.HeroDescriptionDAO;
import com.aurora.core.dao.userData.HeroPlayerDAO;
import com.aurora.core.dao.userData.HeroValuesDAO;
import com.aurora.core.models.Databases;
import com.aurora.core.models.Translations;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.constants.BaseQualities;
import com.aurora.core.models.constants.CoreStates;
import com.aurora.core.models.constants.RulesCombat;
import com.aurora.core.models.constants.RulesSkills;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.helpers.Rules;
import com.aurora.core.models.settingSpecific.Classes;
import com.aurora.core.models.settingSpecific.Deities;
import com.aurora.core.models.settingSpecific.EnergyTypes;
import com.aurora.core.models.settingSpecific.Feats;
import com.aurora.core.models.settingSpecific.HeroNPC;
import com.aurora.core.models.settingSpecific.MaterialTypes;
import com.aurora.core.models.settingSpecific.Monsters;
import com.aurora.core.models.settingSpecific.RaceTemplates;
import com.aurora.core.models.settingSpecific.Races;
import com.aurora.core.models.settingSpecific.Skills;
import com.aurora.core.models.settingSpecific.SpecialAttacks;
import com.aurora.core.models.settingSpecific.SpecialQualities;
import com.aurora.core.models.settingSpecific.Spells;
import com.aurora.core.models.typeHelpers.RulesType;
import com.aurora.core.models.usables.Armour;
import com.aurora.core.models.usables.Equipment;
import com.aurora.core.models.usables.Weapons;
import com.aurora.core.models.userData.HeroDescription;
import com.aurora.core.models.userData.HeroPlayer;
import com.aurora.core.models.userData.HeroValues;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
@Database(entities = {
    Databases.class,
    CoreStates.class, BaseQualities.class,
    RulesCombat.class, RulesSkills.class, Alignments.class, Sizes.class,
    EnergyTypes.class, MaterialTypes.class,
    SpecialAttacks.class, SpecialQualities.class,
    Armour.class, Equipment.class, Feats.class, Skills.class, Spells.class, Weapons.class,
    Classes.class, Monsters.class, Races.class, RaceTemplates.class, Deities.class,
    HeroNPC.class, HeroPlayer.class, HeroDescription.class, HeroValues.class,
    Translations.class
}, version = 1, exportSchema = false)
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
   * Lists to hold database items
   */
  public final List<Databases> DATABASES_LIST = new ArrayList<>();
  public final List<Sizes> SIZES_LIST = new ArrayList<>();
  public final List<Alignments> ALIGNMENTS_LIST = new ArrayList<>();
  public final List<RulesCombat> RULES_COMBAT_LIST = new ArrayList<>();
  public final List<RulesSkills> RULES_SKILLS_LIST = new ArrayList<>();
  public final List<BaseQualities> BASE_QUALITIES_LIST = new ArrayList<>();
  public final List<CoreStates> CORE_STATES_LIST = new ArrayList<>();
  public final List<EnergyTypes> ENERGY_TYPES_LIST = new ArrayList<>();
  public final List<MaterialTypes> MATERIAL_TYPES_LIST = new ArrayList<>();
  public final List<SpecialAttacks> SPECIAL_ATTACKS_LIST = new ArrayList<>();
  public final List<SpecialQualities> SPECIAL_QUALITIES_LIST = new ArrayList<>();
  /**
   * An array of races available for heroPlayer.
   */
  public final List<Races> RACES_LIST = new ArrayList<>();
  public final List<Classes> CLASSES_LIST = new ArrayList<>();
  public final List<Skills> SKILLS_LIST = new ArrayList<>();
  public final List<Feats> FEATS_LIST = new ArrayList<>();
  public final List<Weapons> WEAPONS_LIST = new ArrayList<>();
  public final List<Armour> ARMOUR_LIST = new ArrayList<>();
  public final List<Equipment> EQUIPMENT_LIST = new ArrayList<>();
  public final List<Spells> SPELLS_LIST = new ArrayList<>();
  public final List<Monsters> MONSTERS_LIST = new ArrayList<>();
  public final List<RaceTemplates> RACE_TEMPLATES_LIST = new ArrayList<>();
  public final List<HeroPlayer> HEROES_PLAYER_LIST = new ArrayList<>();
  public final List<HeroNPC> HEROES_NPC_LIST = new ArrayList<>();
  public final List<Deities> DEITIES_LIST = new ArrayList<>();
  public final List<Translations> TRANSLATIONS_LIST = new ArrayList<>();
  /**
   * Maps to hold database items with ids
   */
  public final Map<Integer, Databases> DATABASES_MAP = new HashMap<>();
  ///////////////////////MAPS FOR RULES///////////////////////////
  public final Map<Integer, Sizes> SIZES_MAP = new HashMap<>();
  public final Map<Integer, Alignments> ALIGNMENTS_MAP = new HashMap<>();
  public final Map<Integer, RulesCombat> RULES_COMBAT_MAP = new HashMap<>();
  public final Map<Integer, RulesSkills> RULES_SKILLS_MAP = new HashMap<>();
  public final Map<Integer, BaseQualities> BASE_QUALITIES_MAP = new HashMap<>();
  public final Map<Integer, CoreStates> CORE_STATES_MAP = new HashMap<>();
  public final Map<Integer, EnergyTypes> ENERGY_TYPES_MAP = new HashMap<>();
  public final Map<Integer, MaterialTypes> MATERIAL_TYPES_MAP = new HashMap<>();
  public final Map<Integer, SpecialAttacks> SPECIAL_ATTACKS_MAP = new HashMap<>();
  public final Map<Integer, SpecialQualities> SPECIAL_QUALITIES_MAP = new HashMap<>();
  /**
   * A map of races, by ID.
   */
  public final Map<Integer, Races> RACES_MAP = new HashMap<>();
  public final Map<Integer, Classes> CLASSES_MAP = new HashMap<>();
  public final Map<Integer, Skills> SKILLS_MAP = new HashMap<>();
  public final Map<Integer, Feats> FEATS_MAP = new HashMap<>();
  public final Map<Integer, Weapons> WEAPONS_MAP = new HashMap<>();
  public final Map<Integer, Armour> ARMOUR_MAP = new HashMap<>();

  ///////////////////////LISTS FOR SETTING///////////////////////////
  public final Map<Integer, Equipment> EQUIPMENT_MAP = new HashMap<>();
  public final Map<Integer, Spells> SPELLS_MAP = new HashMap<>();
  public final Map<Integer, Monsters> MONSTERS_MAP = new HashMap<>();
  public final Map<Integer, RaceTemplates> RACE_TEMPLATES_MAP = new HashMap<>();
  public final Map<Integer, HeroPlayer> HEROES_PLAYER_MAP = new HashMap<>();
  public final Map<Integer, HeroNPC> HEROES_NPC_MAP = new HashMap<>();
  public final Map<Integer, Deities> DEITIES_MAP = new HashMap<>();
  public final Map<Integer, Translations> TRANSLATIONS_MAP = new HashMap<>();
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

  public abstract DatabasesDAO databasesDAO();

  ////////////////////////////////////////////////////////////////////////////
  public abstract SizesDAO rulesSizesDAO();

  public abstract AlignmentsDAO rulesAlignmentsDAO();

  public abstract RulesSkillsDAO rulesSkillsDAO();

  public abstract RulesCombatDAO rulesCombatDAO();

  public abstract BaseQualitiesDAO baseQualitiesDAO();

  public abstract CoreStatesDAO coreStatesDAO();

  ////////////////////////////////////////////////////////////////////////////
  ///////////////////////MAPS FOR SETTING///////////////////////////
  public abstract EnergyTypesDAO energyTypesDAO();

  public abstract MaterialTypesDAO materialTypesDAO();

  public abstract SpecialAttacksDAO specialAttacksDAO();

  public abstract SpecialQualitiesDAO specialQualitiesDAO();

  public abstract ClassesDAO classesDAO();

  public abstract FeatsDAO featsDAO();

  public abstract HeroPlayerDAO heroPlayerDAO();

  public abstract HeroDescriptionDAO heroDescriptionDAO();

  public abstract HeroValuesDAO heroStatisticsAbilityScoresDAO();

  public abstract HeroNPCDAO heroNPCDAO();

  public abstract MonstersDAO monstersDAO();

  public abstract RacesDAO racesDAO();

  public abstract RaceTemplatesDAO raceTemplatesDAO();

  public abstract SkillsDAO skillsDAO();

  public abstract SpellsDAO spellsDAO();

  public abstract DeitiesDAO deitiesDAO();

  ///////////////////////MAPS FOR USABLES///////////////////////////
  public abstract EquipmentDAO equipmentDAO();

  public abstract WeaponsDAO weaponsDAO();

  public abstract ArmourDAO armourDAO();

  ////////////////////////////////////////////////////////////////////////////
  public abstract TranslationsDAO translationsDAO();

  private void onCreateRulesList() {
    rulesList.clear();
    for (RulesType rt : RulesType.values()) {
      rulesList.add((Rules) rt.getNewObject());
    }
  }
  ////////////////////////////////////////////////////////////////////////////

}


