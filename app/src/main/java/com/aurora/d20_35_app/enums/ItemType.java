package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Armour;
import com.aurora.d20_35_app.models.Classes;
import com.aurora.d20_35_app.models.Equipment;
import com.aurora.d20_35_app.models.Feats;
import com.aurora.d20_35_app.models.Hero;
import com.aurora.d20_35_app.models.Monsters;
import com.aurora.d20_35_app.models.RaceTemplates;
import com.aurora.d20_35_app.models.Races;
import com.aurora.d20_35_app.models.Skills;
import com.aurora.d20_35_app.models.Spells;
import com.aurora.d20_35_app.models.Translations;
import com.aurora.d20_35_app.models.Weapons;
import com.aurora.d20_35_app.utilsDatabase.DatabaseHolder;

import java.util.List;
import java.util.Map;

public enum ItemType {
    /**
     * Races
     */
    Races("Races") {
        @Override
        public List<Races> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_MAP;
        }

        @Override
        public Races getNewObject() {
            return new Races();
        }
    },
    /**
     * Classes
     */
    Classes("Classes") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Classes();
        }
    },
    /**
     * Skills
     */
    Skills("Skills") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Skills();
        }
    },
    /**
     * Feats
     */
    Feats("Feats") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Feats();
        }
    },
    /**
     * Weapons
     */
    Weapons("Weapons") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Weapons();
        }
    },
    /**
     * Armour
     */
    Armour("Armour") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Armour();
        }
    },
    /**
     * Equipment
     */
    Equipment("Equipment") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Equipment();
        }
    },
    /**
     * Spells
     */
    Spells("Spells") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Spells();
        }
    },
    /**
     * Monsters
     */
    Monsters("Monsters") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Monsters();
        }
    },
    /**
     * RaceTemplates
     */
    RaceTemplates("RaceTemplates") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new RaceTemplates();
        }
    },
    /**
     * Hero
     */
    Hero("Hero") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Hero();
        }
    },
    /**
     * Hero
     */
    Translations("Translations") {
        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Translations();
        }
    };

    private String itemType;

    ItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return this.itemType;
    }

    public static boolean contains(String name) {
        for (ItemType it : ItemType.values()) {
            if (it.toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public abstract List getDatabaseList(DatabaseHolder databaseHolder);

    public abstract Map getDatabaseMap(DatabaseHolder databaseHolder);

    public abstract Item getNewObject();

}
