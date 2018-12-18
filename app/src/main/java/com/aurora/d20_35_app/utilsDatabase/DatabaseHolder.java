package com.aurora.d20_35_app.utilsDatabase;


import android.annotation.SuppressLint;
import android.content.Context;

import com.aurora.d20_35_app.dao.ArmourDAO;
import com.aurora.d20_35_app.dao.ClassesDAO;
import com.aurora.d20_35_app.dao.DatabasesDAO;
import com.aurora.d20_35_app.dao.EquipmentDAO;
import com.aurora.d20_35_app.dao.FeatsDAO;
import com.aurora.d20_35_app.dao.HeroDAO;
import com.aurora.d20_35_app.dao.MonstersDAO;
import com.aurora.d20_35_app.dao.RaceTemplatesDAO;
import com.aurora.d20_35_app.dao.RacesDAO;
import com.aurora.d20_35_app.dao.RulesCombatDAO;
import com.aurora.d20_35_app.dao.RulesSkillsDAO;
import com.aurora.d20_35_app.dao.SkillsDAO;
import com.aurora.d20_35_app.dao.SpellsDAO;
import com.aurora.d20_35_app.dao.TranslationsDAO;
import com.aurora.d20_35_app.dao.WeaponsDAO;
import com.aurora.d20_35_app.helper.Rules;
import com.aurora.d20_35_app.models.Armour;
import com.aurora.d20_35_app.models.Classes;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.Equipment;
import com.aurora.d20_35_app.models.Feats;
import com.aurora.d20_35_app.models.Hero;
import com.aurora.d20_35_app.models.Monsters;
import com.aurora.d20_35_app.models.RaceTemplates;
import com.aurora.d20_35_app.models.Races;
import com.aurora.d20_35_app.models.RulesCombat;
import com.aurora.d20_35_app.models.RulesSkills;
import com.aurora.d20_35_app.models.Skills;
import com.aurora.d20_35_app.models.Spells;
import com.aurora.d20_35_app.models.Translations;
import com.aurora.d20_35_app.models.Weapons;

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
        Armour.class, Classes.class, Equipment.class, Feats.class, Hero.class, Monsters.class, Races.class, RaceTemplates.class, Skills.class, Spells.class, Weapons.class,
        Translations.class, Databases.class,
        RulesCombat.class, RulesSkills.class
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

    public abstract HeroDAO heroDAO();

    public abstract MonstersDAO monstersDAO();

    public abstract RacesDAO racesDAO();

    public abstract RaceTemplatesDAO raceTemplatesDAO();

    public abstract SkillsDAO skillsDAO();

    public abstract SpellsDAO spellsDAO();

    public abstract WeaponsDAO weaponsDAO();

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
     * An array of races available for player.
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
    public final List<Hero> HEROES_LIST = new ArrayList<Hero>();

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
    public final Map<Integer, Hero> HEROES_MAP = new HashMap<Integer, Hero>();

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


