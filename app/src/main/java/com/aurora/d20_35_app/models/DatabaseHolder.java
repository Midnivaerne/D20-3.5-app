package com.aurora.d20_35_app.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class DatabaseHolder {

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

    public enum ItemType {
        /**
         * Races
         */
        Races("Races"),
        /**
         * Classes
         */
        Classes("Classes"),
        /**
         * Skills
         */
        Skills("Skills"),
        /**
         * Feats
         */
        Feats("Feats"),
        /**
         * Weapons
         */
        Weapons("Weapons"),
        /**
         * Armour
         */
        Armour("Armour"),
        /**
         * Equipment
         */
        Equipment("Equipment"),
        /**
         * Spells
         */
        Spells("Spells"),
        /**
         * Monsters
         */
        Monsters("Monsters"),
        /**
         * RaceTemplates
         */
        RaceTemplates("RaceTemplates"),
        /**
         * Hero
         */
        Hero("Hero");

        private String itemType;

        ItemType(String itemType) {
            this.itemType = itemType;
        }

        @Override
        public String toString() {
            return this.itemType;
        }

    }

    @Data
    @NoArgsConstructor
    public static class Item {
        public String id;
        public String content;
        public String details;

        private String name;

        public Item(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        protected boolean canEqual(Object other) {
            return other instanceof Races;
        }

        /**
         * @param itemType
         * @return
         */
        public static Item createAnItem(ItemType itemType) {
            switch (itemType) {
                case Races:
                    return new Races();
                case Classes:
                    return new Classes();
                case Skills:
                    return new Skills();
                case Feats:
                    return new Feats();
                case Weapons:
                    return new Weapons();
                case Armour:
                    return new Armour();
                case Equipment:
                    return new Equipment();
                case Spells:
                    return new Spells();
                case Monsters:
                    return new Monsters();
                case RaceTemplates:
                    return new RaceTemplates();
                case Hero:
                    return new Hero();
            }
            throw new IllegalArgumentException("ItemType" + itemType + " is not recognized.");
        }

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Races extends Item {


    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Classes extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Feats extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Weapons extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Armour extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Equipment extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Spells extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Monsters extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class RaceTemplates extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    private static class Skills extends Item {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    private static class Hero extends Item {

    }

}
