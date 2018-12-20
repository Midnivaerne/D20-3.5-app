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
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.Weapons;
import com.aurora.d20_35_app.utilsDatabase.DatabaseHolder;

import java.util.List;
import java.util.Map;

public enum ItemType {
    /**
     * Databases a.k.a Sources
     */
    Databases("Databases") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.databasesDAO();
        }

        @Override
        public List<Databases> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.DATABASES_LIST;
        }

        @Override
        public Map<Integer, Databases> getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.DATABASES_MAP;
        }

        @Override
        public Item getNewObject() {
            return new Databases();
        }

        @Override
        public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.databasesDAO().insertAll(databaseHolder.DATABASES_LIST);
            for (Databases databases : databaseHolder.DATABASES_LIST) {
                databaseHolder.DATABASES_MAP.put(databases.getItemID(), databases);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.DATABASES_LIST.addAll(databaseHolder.databasesDAO().getItems());
        }
    },
    /**
     * Races
     */
    Races("Races") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.racesDAO();
        }

        @Override
        public List<Races> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_LIST;
        }

        @Override
        public Map<Integer, Races> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.RACES_MAP.put(races.getItemID(), races);
            }
        }
    },
    /**
     * Classes
     */
    Classes("Classes") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.classesDAO();
        }

        @Override
        public List<Classes> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_LIST;
        }

        @Override
        public Map<Integer, Classes> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.CLASSES_MAP.put(classes.getItemID(), classes);
            }
        }
    },
    /**
     * Skills
     */
    Skills("Skills") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.skillsDAO();
        }

        @Override
        public List<Skills> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_LIST;
        }

        @Override
        public Map<Integer, Skills> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.SKILLS_MAP.put(skills.getItemID(), skills);
            }
        }
    },
    /**
     * Feats
     */
    Feats("Feats") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.featsDAO();
        }

        @Override
        public List<Feats> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_LIST;
        }

        @Override
        public Map<Integer, Feats> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.FEATS_MAP.put(feats.getItemID(), feats);
            }
        }
    },
    /**
     * Weapons
     */
    Weapons("Weapons") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.weaponsDAO();
        }

        @Override
        public List<Weapons> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_LIST;
        }

        @Override
        public Map<Integer, Weapons> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.WEAPONS_MAP.put(weapons.getItemID(), weapons);
            }
        }

    },
    /**
     * Armour
     */
    Armour("Armour") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.armourDAO();
        }

        @Override
        public List<Armour> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_LIST;
        }

        @Override
        public Map<Integer, Armour> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.ARMOUR_MAP.put(armour.getItemID(), armour);
            }
        }
    },
    /**
     * Equipment
     */
    Equipment("Equipment") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.equipmentDAO();
        }

        @Override
        public List<Equipment> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_LIST;
        }

        @Override
        public Map<Integer, Equipment> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.EQUIPMENT_MAP.put(equipment.getItemID(), equipment);
            }
        }
    },
    /**
     * Spells
     */
    Spells("Spells") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.spellsDAO();
        }

        @Override
        public List<Spells> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_LIST;
        }

        @Override
        public Map<Integer, Spells> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.SPELLS_MAP.put(spells.getItemID(), spells);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SPELLS_LIST.addAll(databaseHolder.spellsDAO().getItems());
        }

    },
    /**
     * Monsters
     */
    Monsters("Monsters") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.monstersDAO();
        }

        @Override
        public List<Monsters> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_LIST;
        }

        @Override
        public Map<Integer, Monsters> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.MONSTERS_MAP.put(monsters.getItemID(), monsters);
            }
        }
    },
    /**
     * RaceTemplates
     */
    RaceTemplates("RaceTemplates") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.raceTemplatesDAO();
        }

        @Override
        public List<RaceTemplates> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_LIST;
        }

        @Override
        public Map<Integer, RaceTemplates> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.RACE_TEMPLATES_MAP.put(raceTemplates.getItemID(), raceTemplates);
            }
        }
    },
    /**
     * Hero
     */
    Hero("Hero") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.heroDAO();
        }

        @Override
        public List<Hero> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_LIST;
        }

        @Override
        public Map<Integer, Hero> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.HEROES_MAP.put(hero.getItemID(), hero);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.HEROES_LIST.addAll(databaseHolder.heroDAO().getItems());
        }
    },
    /**
     * Translations
     */
    Translations("Translations") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.translationsDAO();
        }

        @Override
        public List<Translations> getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_LIST;
        }

        @Override
        public Map<Integer, Translations> getDatabaseMap(DatabaseHolder databaseHolder) {
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
                databaseHolder.TRANSLATIONS_MAP.put(translations.getItemID(), translations);
            }
        }

        @Override
        public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
            databaseHolder.TRANSLATIONS_LIST.addAll(databaseHolder.translationsDAO().getItems());
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

    public abstract BaseDAO<Item> getDAO(DatabaseHolder databaseHolder);

    public abstract List<? extends Item> getDatabaseList(DatabaseHolder databaseHolder);

    public abstract Map<Integer, ? extends Item> getDatabaseMap(DatabaseHolder databaseHolder);

    public List<Item> getAllFromDatabase(DatabaseHolder databaseHolder) {
        return getDAO(databaseHolder).getItems();
    }

    public abstract Item getNewObject();

    public abstract void fromHolderToDatabase(DatabaseHolder databaseHolder);

    public abstract void fromDatabaseToHolder(DatabaseHolder databaseHolder);

    public void deleteAll(DatabaseHolder databaseHolder) {
        deleteAllFromDatabase(databaseHolder);
        deleteAllFromHolder(databaseHolder);
    }

    public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
        getDatabaseList(databaseHolder).clear();
        getDatabaseMap(databaseHolder).clear();
    }

    public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
        getDAO(databaseHolder).deleteAll();
    }

}
