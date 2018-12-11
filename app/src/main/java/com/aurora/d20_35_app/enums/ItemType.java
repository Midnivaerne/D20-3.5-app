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
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.racesDAO();
        }

        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RACES_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.racesDAO().getRaces();
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
            databaseHolder.RACES_LIST.addAll(databaseHolder.racesDAO().getRaces());
            for (Races races : databaseHolder.RACES_LIST) {
                databaseHolder.RACES_MAP.put(String.valueOf(races.getItemID()), races);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RACES_LIST.clear();
            databaseHolder.RACES_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.racesDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.CLASSES_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.classesDAO().getClasses();
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
            databaseHolder.CLASSES_LIST.addAll(databaseHolder.classesDAO().getClasses());
            for (Classes classes : databaseHolder.CLASSES_LIST) {
                databaseHolder.CLASSES_MAP.put(String.valueOf(classes.getItemID()), classes);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.CLASSES_LIST.clear();
            databaseHolder.CLASSES_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.classesDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.SKILLS_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.skillsDAO().getSkills();
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
            databaseHolder.SKILLS_LIST.addAll(databaseHolder.skillsDAO().getSkills());
            for (Skills skills : databaseHolder.SKILLS_LIST) {
                databaseHolder.SKILLS_MAP.put(String.valueOf(skills.getItemID()), skills);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SKILLS_LIST.clear();
            databaseHolder.SKILLS_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.skillsDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.FEATS_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.featsDAO().getFeats();
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
            databaseHolder.FEATS_LIST.addAll(databaseHolder.featsDAO().getFeats());
            for (Feats feats : databaseHolder.FEATS_LIST) {
                databaseHolder.FEATS_MAP.put(String.valueOf(feats.getItemID()), feats);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.FEATS_LIST.clear();
            databaseHolder.FEATS_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.featsDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.WEAPONS_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.weaponsDAO().getWeapons();
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
            databaseHolder.WEAPONS_LIST.addAll(databaseHolder.weaponsDAO().getWeapons());
            for (Weapons weapons : databaseHolder.WEAPONS_LIST) {
                databaseHolder.WEAPONS_MAP.put(String.valueOf(weapons.getItemID()), weapons);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.WEAPONS_LIST.clear();
            databaseHolder.WEAPONS_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.weaponsDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.ARMOUR_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.armourDAO().getArmour();
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
            databaseHolder.ARMOUR_LIST.addAll(databaseHolder.armourDAO().getArmour());
            for (Armour armour : databaseHolder.ARMOUR_LIST) {
                databaseHolder.ARMOUR_MAP.put(String.valueOf(armour.getItemID()), armour);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.ARMOUR_LIST.clear();
            databaseHolder.ARMOUR_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.armourDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.EQUIPMENT_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.equipmentDAO().getEquipment();
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
            databaseHolder.EQUIPMENT_LIST.addAll(databaseHolder.equipmentDAO().getEquipment());
            for (Equipment equipment : databaseHolder.EQUIPMENT_LIST) {
                databaseHolder.EQUIPMENT_MAP.put(String.valueOf(equipment.getItemID()), equipment);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.EQUIPMENT_LIST.clear();
            databaseHolder.EQUIPMENT_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.equipmentDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.SPELLS_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.spellsDAO().getSpells();
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
            databaseHolder.SPELLS_LIST.addAll(databaseHolder.spellsDAO().getSpells());
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.SPELLS_LIST.clear();
            databaseHolder.SPELLS_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.spellsDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.MONSTERS_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.monstersDAO().getMonsters();
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
            databaseHolder.MONSTERS_LIST.addAll(databaseHolder.monstersDAO().getMonsters());
            for (Monsters monsters : databaseHolder.MONSTERS_LIST) {
                databaseHolder.MONSTERS_MAP.put(String.valueOf(monsters.getItemID()), monsters);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.MONSTERS_LIST.clear();
            databaseHolder.MONSTERS_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.monstersDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.RACE_TEMPLATES_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.raceTemplatesDAO().getRaceTemplates();
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
            databaseHolder.RACE_TEMPLATES_LIST.addAll(databaseHolder.raceTemplatesDAO().getRaceTemplates());
            for (RaceTemplates raceTemplates : databaseHolder.RACE_TEMPLATES_LIST) {
                databaseHolder.RACE_TEMPLATES_MAP.put(String.valueOf(raceTemplates.getItemID()), raceTemplates);
            }
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.RACE_TEMPLATES_LIST.clear();
            databaseHolder.RACE_TEMPLATES_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.raceTemplatesDAO().deleteAll();
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
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.HEROES_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.heroDAO().getHero();
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
            databaseHolder.HEROES_LIST.addAll(databaseHolder.heroDAO().getHero());
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.HEROES_LIST.clear();
            databaseHolder.HEROES_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.heroDAO().deleteAll();
        }
    },
    /**
     * Hero
     */
    Translations("Translations") {
        @Override
        public BaseDAO getDAO(DatabaseHolder databaseHolder) {
            return databaseHolder.translationsDAO();
        }

        @Override
        public List getDatabaseList(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_LIST;
        }

        @Override
        public Map getDatabaseMap(DatabaseHolder databaseHolder) {
            return databaseHolder.TRANSLATIONS_MAP;
        }

        @Override
        public List getAllFromDatabase(DatabaseHolder databaseHolder) {
            return databaseHolder.translationsDAO().getTranslations();
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
            databaseHolder.TRANSLATIONS_LIST.addAll(databaseHolder.translationsDAO().getTranslations());
        }

        @Override
        public void deleteAllFromHolder(DatabaseHolder databaseHolder) {
            databaseHolder.TRANSLATIONS_LIST.clear();
            databaseHolder.TRANSLATIONS_MAP.clear();
        }

        @Override
        public void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
            databaseHolder.translationsDAO().deleteAll();
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

    public abstract BaseDAO getDAO(DatabaseHolder databaseHolder);

    public abstract List getDatabaseList(DatabaseHolder databaseHolder);

    public abstract Map getDatabaseMap(DatabaseHolder databaseHolder);

    public abstract List getAllFromDatabase(DatabaseHolder databaseHolder);

    public abstract Item getNewObject();

    public abstract void fromHolderToDatabase(DatabaseHolder databaseHolder);

    public abstract void fromDatabaseToHolder(DatabaseHolder databaseHolder);

    public void deleteAll(DatabaseHolder databaseHolder) {
        deleteAllFromDatabase(databaseHolder);
        deleteAllFromHolder(databaseHolder);
    }

    public abstract void deleteAllFromHolder(DatabaseHolder databaseHolder);

    public abstract void deleteAllFromDatabase(DatabaseHolder databaseHolder);

}
