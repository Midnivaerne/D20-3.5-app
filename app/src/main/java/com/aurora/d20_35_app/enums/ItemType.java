package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.helper.BaseDAO;
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
        public BaseDAO<Races> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.racesDAO();
        }

        @Override
        public List<Races> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_LIST;
        }

        @Override
        public Map<String, Races> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_MAP;
        }

        @Override
        public Races getNewObject() {
            return new Races();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.racesDAO().insertAll(databaseHolder.RACES_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RACES_LIST.addAll(databaseHolder.racesDAO().getItems());
            for (Races races : databaseHolder.RACES_LIST) {
                databaseHolder.RACES_MAP.put(String.valueOf(races.getItemID()), races);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RACES_LIST.clear();
            databaseHolder.RACES_MAP.clear();
        }
    },
    /**
     * Classes
     */
    Classes("Classes") {
        @Override
        public BaseDAO<Classes> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.classesDAO();
        }

        @Override
        public List<Classes> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_LIST;
        }

        @Override
        public Map<String, Classes> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Classes();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.classesDAO().insertAll(databaseHolder.CLASSES_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.CLASSES_LIST.addAll(databaseHolder.classesDAO().getItems());
            for (Classes classes : databaseHolder.CLASSES_LIST) {
                databaseHolder.CLASSES_MAP.put(String.valueOf(classes.getItemID()), classes);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.CLASSES_LIST.clear();
            databaseHolder.CLASSES_MAP.clear();
        }
    },
    /**
     * Skills
     */
    Skills("Skills") {
        @Override
        public BaseDAO<Skills> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.skillsDAO();
        }

        @Override
        public List<Skills> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_LIST;
        }

        @Override
        public Map<String, Skills> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Skills();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.skillsDAO().insertAll(databaseHolder.SKILLS_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SKILLS_LIST.addAll(databaseHolder.skillsDAO().getItems());
            for (Skills skills : databaseHolder.SKILLS_LIST) {
                databaseHolder.SKILLS_MAP.put(String.valueOf(skills.getItemID()), skills);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SKILLS_LIST.clear();
            databaseHolder.SKILLS_MAP.clear();
        }

    },
    /**
     * Feats
     */
    Feats("Feats") {
        @Override
        public BaseDAO<Feats> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.featsDAO();
        }

        @Override
        public List<Feats> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_LIST;
        }

        @Override
        public Map<String, Feats> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Feats();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.featsDAO().insertAll(databaseHolder.FEATS_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.FEATS_LIST.addAll(databaseHolder.featsDAO().getItems());
            for (Feats feats : databaseHolder.FEATS_LIST) {
                databaseHolder.FEATS_MAP.put(String.valueOf(feats.getItemID()), feats);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.FEATS_LIST.clear();
            databaseHolder.FEATS_MAP.clear();
        }
    },
    /**
     * Weapons
     */
    Weapons("Weapons") {
        @Override
        public BaseDAO<Weapons> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.weaponsDAO();
        }

        @Override
        public List<Weapons> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_LIST;
        }

        @Override
        public Map<String, Weapons> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Weapons();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.weaponsDAO().insertAll(databaseHolder.WEAPONS_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.WEAPONS_LIST.addAll(databaseHolder.weaponsDAO().getItems());
            for (Weapons weapons : databaseHolder.WEAPONS_LIST) {
                databaseHolder.WEAPONS_MAP.put(String.valueOf(weapons.getItemID()), weapons);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.WEAPONS_LIST.clear();
            databaseHolder.WEAPONS_MAP.clear();
        }
    },
    /**
     * Armour
     */
    Armour("Armour") {
        @Override
        public BaseDAO<Armour> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.armourDAO();
        }

        @Override
        public List<Armour> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_LIST;
        }

        @Override
        public Map<String, Armour> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Armour();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.armourDAO().insertAll(databaseHolder.ARMOUR_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.ARMOUR_LIST.addAll(databaseHolder.armourDAO().getItems());
            for (Armour armour : databaseHolder.ARMOUR_LIST) {
                databaseHolder.ARMOUR_MAP.put(String.valueOf(armour.getItemID()), armour);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.ARMOUR_LIST.clear();
            databaseHolder.ARMOUR_MAP.clear();
        }
    },
    /**
     * Equipment
     */
    Equipment("Equipment") {
        @Override
        public BaseDAO<Equipment> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.equipmentDAO();
        }

        @Override
        public List<Equipment> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_LIST;
        }

        @Override
        public Map<String, Equipment> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Equipment();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.equipmentDAO().insertAll(databaseHolder.EQUIPMENT_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.EQUIPMENT_LIST.addAll(databaseHolder.equipmentDAO().getItems());
            for (Equipment equipment : databaseHolder.EQUIPMENT_LIST) {
                databaseHolder.EQUIPMENT_MAP.put(String.valueOf(equipment.getItemID()), equipment);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.EQUIPMENT_LIST.clear();
            databaseHolder.EQUIPMENT_MAP.clear();
        }
    },
    /**
     * Spells
     */
    Spells("Spells") {
        @Override
        public BaseDAO<Spells> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.spellsDAO();
        }

        @Override
        public List<Spells> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_LIST;
        }

        @Override
        public Map<String, Spells> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Spells();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.spellsDAO().insertAll(databaseHolder.SPELLS_LIST);
            for (Spells spells : databaseHolder.SPELLS_LIST) {
                databaseHolder.SPELLS_MAP.put(String.valueOf(spells.getItemID()), spells);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SPELLS_LIST.addAll(databaseHolder.spellsDAO().getItems());
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SPELLS_LIST.clear();
            databaseHolder.SPELLS_MAP.clear();
        }
    },
    /**
     * Monsters
     */
    Monsters("Monsters") {
        @Override
        public BaseDAO<Monsters> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.monstersDAO();
        }

        @Override
        public List<Monsters> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_LIST;
        }

        @Override
        public Map<String, Monsters> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Monsters();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.monstersDAO().insertAll(databaseHolder.MONSTERS_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.MONSTERS_LIST.addAll(databaseHolder.monstersDAO().getItems());
            for (Monsters monsters : databaseHolder.MONSTERS_LIST) {
                databaseHolder.MONSTERS_MAP.put(String.valueOf(monsters.getItemID()), monsters);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.MONSTERS_LIST.clear();
            databaseHolder.MONSTERS_MAP.clear();
        }
    },
    /**
     * RaceTemplates
     */
    RaceTemplates("RaceTemplates") {
        @Override
        public BaseDAO<RaceTemplates> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.raceTemplatesDAO();
        }

        @Override
        public List<RaceTemplates> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_LIST;
        }

        @Override
        public Map<String, RaceTemplates> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new RaceTemplates();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.raceTemplatesDAO().insertAll(databaseHolder.RACE_TEMPLATES_LIST);
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RACE_TEMPLATES_LIST.addAll(databaseHolder.raceTemplatesDAO().getItems());
            for (RaceTemplates raceTemplates : databaseHolder.RACE_TEMPLATES_LIST) {
                databaseHolder.RACE_TEMPLATES_MAP.put(String.valueOf(raceTemplates.getItemID()), raceTemplates);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RACE_TEMPLATES_LIST.clear();
            databaseHolder.RACE_TEMPLATES_MAP.clear();
        }
    },
    /**
     * Hero
     */
    Hero("Hero") {
        @Override
        public BaseDAO<Hero> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.heroDAO();
        }

        @Override
        public List<Hero> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_LIST;
        }

        @Override
        public Map<String, Hero> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Hero();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.heroDAO().insertAll(databaseHolder.HEROES_LIST);
            for (Hero hero : databaseHolder.HEROES_LIST) {
                databaseHolder.HEROES_MAP.put(String.valueOf(hero.getItemID()), hero);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.HEROES_LIST.addAll(databaseHolder.heroDAO().getItems());
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.HEROES_LIST.clear();
            databaseHolder.HEROES_MAP.clear();
        }
    },
    /**
     * Hero
     */
    Translations("Translations") {
        @Override
        public BaseDAO<Translations> getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.translationsDAO();
        }

        @Override
        public List<Translations> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_LIST;
        }

        @Override
        public Map<String, Translations> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Translations();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.translationsDAO().insertAll(databaseHolder.TRANSLATIONS_LIST);
            for (Translations translations : databaseHolder.TRANSLATIONS_LIST) {
                databaseHolder.TRANSLATIONS_MAP.put(String.valueOf(translations.getItemID()), translations);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.TRANSLATIONS_LIST.addAll(databaseHolder.translationsDAO().getItems());
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.TRANSLATIONS_LIST.clear();
            databaseHolder.TRANSLATIONS_MAP.clear();
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

    public abstract BaseDAO<? extends Item> getDAO(DatabaseHolder databaseHolder);

    public abstract List<? extends Item> getDatabaseList(DatabaseHolder databaseHolder);

    public abstract Map<String, ? extends Item> getDatabaseMap(DatabaseHolder databaseHolder);

    public List<? extends Item> getAllFromDatabase(DatabaseHolder databaseHolder) {
        return getDAO(databaseHolder).getItems();
    }

    public abstract Item getNewObject();

    public abstract void fromHolderToDatabase(DatabaseHolder databaseHolder);

    public abstract void fromDatabaseToHolder(DatabaseHolder databaseHolder);

    public void deleteAll(DatabaseHolder databaseHolder) {
        deleteAllFromDatabase(databaseHolder);
        deleteAllFromHolder(databaseHolder);
    }

    public abstract void deleteAllFromHolder(DatabaseHolder databaseHolder);

    public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
        getDAO(databaseHolder).deleteAll();
    }

}
