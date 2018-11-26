package com.aurora.d20_35_app.utilsDatabase;


import android.content.Context;

import com.aurora.d20_35_app.dao.ArmourDAO;
import com.aurora.d20_35_app.dao.ClassesDAO;
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
import com.aurora.d20_35_app.dao.WeaponsDAO;
import com.aurora.d20_35_app.models.Armour;
import com.aurora.d20_35_app.models.Classes;
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
import com.aurora.d20_35_app.models.Weapons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import lombok.Getter;
import lombok.Setter;

@Singleton
@Database(entities = {
        Armour.class, Classes.class, Equipment.class, Feats.class, Hero.class, Monsters.class, Races.class, RaceTemplates.class, Skills.class, Spells.class, Weapons.class,
        RulesCombat.class, RulesSkills.class
}, version = 1, exportSchema = false)
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

    public abstract RulesSkillsDAO rulesSkillsDAO();

    public abstract RulesCombatDAO rulesCombatDAO();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    public static DatabaseHolder getDatabaseHolder(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseHolder.class, DATABASE_NAME).build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Getter
    @Setter
    private List<String> databasesList = new ArrayList<String>();

    /**
     * An array of races available for player.
     */
    public static final List<Races> RACES_LIST = new ArrayList<Races>();
    public static final List<Classes> CLASSES_LIST = new ArrayList<Classes>();
    public static final List<Skills> SKILLS_LIST = new ArrayList<Skills>();
    public static final List<Feats> FEATS_LIST = new ArrayList<Feats>();
    public static final List<Weapons> WEAPONS_LIST_LIST = new ArrayList<Weapons>();
    public static final List<Armour> ARMOUR_LIST = new ArrayList<Armour>();
    public static final List<Equipment> EQUIPMENT_LIST = new ArrayList<Equipment>();
    public static final List<Spells> SPELLS_LIST = new ArrayList<Spells>();
    public static final List<Monsters> MONSTERS_LIST = new ArrayList<Monsters>();
    public static final List<RaceTemplates> RACE_TEMPLATES_LIST = new ArrayList<RaceTemplates>();
    public static final List<Hero> HEROES_LIST = new ArrayList<Hero>();


    /**
     * A map of races, by ID.
     */
    public static final Map<String, Races> RACES_MAP = new HashMap<String, Races>();
    public static final Map<String, Classes> CLASSES_MAP = new HashMap<String, Classes>();
    public static final Map<String, Skills> SKILLS_MAP = new HashMap<String, Skills>();
    public static final Map<String, Feats> FEATS_MAP = new HashMap<String, Feats>();
    public static final Map<String, Weapons> WEAPONS_MAP_MAP = new HashMap<String, Weapons>();
    public static final Map<String, Armour> ARMOUR_MAP = new HashMap<String, Armour>();
    public static final Map<String, Equipment> EQUIPMENT_MAP = new HashMap<String, Equipment>();
    public static final Map<String, Spells> SPELLS_MAP = new HashMap<String, Spells>();
    public static final Map<String, Monsters> MONSTERS_MAP = new HashMap<String, Monsters>();
    public static final Map<String, RaceTemplates> RACE_TEMPLATES_MAP = new HashMap<String, RaceTemplates>();
    public static final Map<String, Hero> HEROES_MAP = new HashMap<String, Hero>();


    @Getter
    @Setter
    private List<String> rulesList = Arrays.asList("Combat", "Skills");

    public static final List<RulesCombat> RULES_COMBAT_LIST = new ArrayList<RulesCombat>();
    public static final List<RulesSkills> RULES_SKILLS_LIST = new ArrayList<RulesSkills>();

    public static final Map<String, RulesCombat> RULES_COMBAT_MAP = new HashMap<String, RulesCombat>();
    public static final Map<String, RulesSkills> RULES_SKILLS_MAP = new HashMap<String, RulesSkills>();
}


