package com.aurora.d20_35_app.utils.database;


import android.annotation.SuppressLint;
import android.content.Context;

import com.aurora.d20_35_app.dao.DatabasesDAO;
import com.aurora.d20_35_app.dao.TranslationsDAO;
import com.aurora.d20_35_app.dao.constants.RulesCombatDAO;
import com.aurora.d20_35_app.dao.constants.RulesSkillsDAO;
import com.aurora.d20_35_app.dao.settingSpecific.ClassesDAO;
import com.aurora.d20_35_app.dao.settingSpecific.DeitiesDAO;
import com.aurora.d20_35_app.dao.settingSpecific.FeatsDAO;
import com.aurora.d20_35_app.dao.settingSpecific.HeroNPCDAO;
import com.aurora.d20_35_app.dao.settingSpecific.MonstersDAO;
import com.aurora.d20_35_app.dao.settingSpecific.RaceTemplatesDAO;
import com.aurora.d20_35_app.dao.settingSpecific.RacesDAO;
import com.aurora.d20_35_app.dao.settingSpecific.SkillsDAO;
import com.aurora.d20_35_app.dao.settingSpecific.SpellsDAO;
import com.aurora.d20_35_app.dao.usables.ArmourDAO;
import com.aurora.d20_35_app.dao.usables.EquipmentDAO;
import com.aurora.d20_35_app.dao.usables.WeaponsDAO;
import com.aurora.d20_35_app.dao.userData.HeroDescriptionDAO;
import com.aurora.d20_35_app.dao.userData.HeroPlayerDAO;
import com.aurora.d20_35_app.helper.Rules;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.Translations;
import com.aurora.d20_35_app.models.constants.Alignments;
import com.aurora.d20_35_app.models.constants.RulesCombat;
import com.aurora.d20_35_app.models.constants.RulesSkills;
import com.aurora.d20_35_app.models.constants.Sizes;
import com.aurora.d20_35_app.models.settingSpecific.Classes;
import com.aurora.d20_35_app.models.settingSpecific.Deities;
import com.aurora.d20_35_app.models.settingSpecific.Feats;
import com.aurora.d20_35_app.models.settingSpecific.HeroNPC;
import com.aurora.d20_35_app.models.settingSpecific.Monsters;
import com.aurora.d20_35_app.models.settingSpecific.RaceTemplates;
import com.aurora.d20_35_app.models.settingSpecific.Races;
import com.aurora.d20_35_app.models.settingSpecific.Skills;
import com.aurora.d20_35_app.models.settingSpecific.Spells;
import com.aurora.d20_35_app.models.usables.Armour;
import com.aurora.d20_35_app.models.usables.Equipment;
import com.aurora.d20_35_app.models.usables.Weapons;
import com.aurora.d20_35_app.models.userData.HeroDescription;
import com.aurora.d20_35_app.models.userData.HeroPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Singleton
@Database(entities = {
        Armour.class, Classes.class, Equipment.class, Feats.class,
        HeroPlayer.class, HeroDescription.class, HeroNPC.class,
        Monsters.class, Races.class, RaceTemplates.class, Skills.class, Spells.class, Weapons.class,
        Deities.class,
        Translations.class, Databases.class,
        RulesCombat.class, RulesSkills.class, Alignments.class, Sizes.class
}, version = 1, exportSchema = false)
@TypeConverters(DataTypeConverters.class)
@SuppressLint("UseSparseArrays")
public abstract class DatabaseHolder extends RoomDatabase {

    private static DatabaseHolder INSTANCE;
    private static final String DATABASE_NAME = "database.db";

    public abstract ArmourDAO armourDAO();

    public abstract ClassesDAO classesDAO();

    public abstract EquipmentDAO equipmentDAO();

    public abstract FeatsDAO featsDAO();

    public abstract HeroPlayerDAO heroPlayerDAO();

    public abstract HeroDescriptionDAO heroDescriptionDAO();

    public abstract HeroNPCDAO heroNPCDAO();

    public abstract MonstersDAO monstersDAO();

    public abstract RacesDAO racesDAO();

    public abstract RaceTemplatesDAO raceTemplatesDAO();

    public abstract SkillsDAO skillsDAO();

    public abstract SpellsDAO spellsDAO();

    public abstract WeaponsDAO weaponsDAO();

    public abstract DeitiesDAO deitiesDAO();

    public abstract TranslationsDAO translationsDAO();

    public abstract DatabasesDAO databasesDAO();

    public abstract RulesSkillsDAO rulesSkillsDAO();

    public abstract RulesCombatDAO rulesCombatDAO();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

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

    private void onCreateRulesList() {
        String[] rulesTable = {"Combat", "Skills"};
        rulesList.clear();
        for (int i = 0; i < rulesTable.length; i++) {
            rulesList.add(new Rules(rulesTable[i]));
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * An array of races available for heroPlayer.
     */
    public final List<Races> RACES_LIST = new ArrayList<Races>();
    public final List<Classes> CLASSES_LIST = new ArrayList<Classes>();
    public final List<Skills> SKILLS_LIST = new ArrayList<Skills>();
    public final List<Feats> FEATS_LIST = new ArrayList<Feats>();
    public final List<Weapons> WEAPONS_LIST = new ArrayList<Weapons>();
    public final List<Armour> ARMOUR_LIST = new ArrayList<Armour>();
    public final List<Equipment> EQUIPMENT_LIST = new ArrayList<Equipment>();
    public final List<Spells> SPELLS_LIST = new ArrayList<Spells>();
    public final List<Monsters> MONSTERS_LIST = new ArrayList<Monsters>();
    public final List<RaceTemplates> RACE_TEMPLATES_LIST = new ArrayList<RaceTemplates>();
    public final List<HeroPlayer> HEROES_PLAYER_LIST = new ArrayList<HeroPlayer>();
    public final List<HeroNPC> HEROES_NPC_LIST = new ArrayList<HeroNPC>();
    public final List<Deities> DEITIES_LIST = new ArrayList<Deities>();

    public final List<Translations> TRANSLATIONS_LIST = new ArrayList<Translations>();
    public final List<Databases> DATABASES_LIST = new ArrayList<Databases>();


    /**
     * A map of races, by ID.
     */
    public final Map<Integer, Races> RACES_MAP = new HashMap<Integer, Races>();
    public final Map<Integer, Classes> CLASSES_MAP = new HashMap<Integer, Classes>();
    public final Map<Integer, Skills> SKILLS_MAP = new HashMap<Integer, Skills>();
    public final Map<Integer, Feats> FEATS_MAP = new HashMap<Integer, Feats>();
    public final Map<Integer, Weapons> WEAPONS_MAP = new HashMap<Integer, Weapons>();
    public final Map<Integer, Armour> ARMOUR_MAP = new HashMap<Integer, Armour>();
    public final Map<Integer, Equipment> EQUIPMENT_MAP = new HashMap<Integer, Equipment>();
    public final Map<Integer, Spells> SPELLS_MAP = new HashMap<Integer, Spells>();
    public final Map<Integer, Monsters> MONSTERS_MAP = new HashMap<Integer, Monsters>();
    public final Map<Integer, RaceTemplates> RACE_TEMPLATES_MAP = new HashMap<Integer, RaceTemplates>();
    public final Map<Integer, HeroPlayer> HEROES_PLAYER_MAP = new HashMap<Integer, HeroPlayer>();
    public final Map<Integer, HeroNPC> HEROES_NPC_MAP = new HashMap<Integer, HeroNPC>();
    public final Map<Integer, Deities> DEITIES_MAP = new HashMap<Integer, Deities>();

    public final Map<Integer, Translations> TRANSLATIONS_MAP = new HashMap<Integer, Translations>();
    public final Map<Integer, Databases> DATABASES_MAP = new HashMap<Integer, Databases>();


    @Getter
    @Setter
    private List<Rules> rulesList = new ArrayList<Rules>();

    public final List<RulesCombat> RULES_COMBAT_LIST = new ArrayList<RulesCombat>();
    public final List<RulesSkills> RULES_SKILLS_LIST = new ArrayList<RulesSkills>();

    public final Map<Integer, RulesCombat> RULES_COMBAT_MAP = new HashMap<Integer, RulesCombat>();
    public final Map<Integer, RulesSkills> RULES_SKILLS_MAP = new HashMap<Integer, RulesSkills>();

}


