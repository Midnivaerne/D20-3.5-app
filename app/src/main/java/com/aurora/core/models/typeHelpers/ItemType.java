package com.aurora.core.models.typeHelpers;

import com.aurora.core.database.DBTableNames;
import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.Databases;
import com.aurora.core.models.Translations;
import com.aurora.core.models.helpers.Item;
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
import com.aurora.core.models.usables.Armour;
import com.aurora.core.models.usables.Equipment;
import com.aurora.core.models.usables.Weapons;
import com.aurora.core.models.userData.HeroDescription;
import com.aurora.core.models.userData.HeroPlayer;
import com.aurora.core.models.userData.HeroValues;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum ItemType implements CoreTypeHelper<ItemType, Item> {
  /**
   * Databases a.k.a Sources
   */
  DATABASES(DBTableNames.DATABASES) {
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
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.DATABASES_LIST.addAll(databaseHolder.databasesDAO().getAllObjectsAsObject());
      for (Databases databases : databaseHolder.DATABASES_LIST) {
        databaseHolder.DATABASES_MAP.put(databases.getItemID(), databases);
      }
    }
  },
  MATERIAL_TYPES(DBTableNames.MATERIAL_TYPES) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.materialTypesDAO();
    }

    @Override
    public List<MaterialTypes> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.MATERIAL_TYPES_LIST;
    }

    @Override
    public Map<Integer, MaterialTypes> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.MATERIAL_TYPES_MAP;
    }

    @Override
    public Item getNewObject() {
      return new MaterialTypes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.materialTypesDAO().insertAll(databaseHolder.MATERIAL_TYPES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.MATERIAL_TYPES_LIST.addAll(databaseHolder.materialTypesDAO().getAllObjectsAsObject());
      for (MaterialTypes materialTypes : databaseHolder.MATERIAL_TYPES_LIST) {
        databaseHolder.MATERIAL_TYPES_MAP.put(materialTypes.getItemID(), materialTypes);
      }
    }
  },
  ENERGY_TYPES(DBTableNames.ENERGY_TYPES) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.energyTypesDAO();
    }

    @Override
    public List<EnergyTypes> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.ENERGY_TYPES_LIST;
    }

    @Override
    public Map<Integer, EnergyTypes> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.ENERGY_TYPES_MAP;
    }

    @Override
    public Item getNewObject() {
      return new EnergyTypes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.energyTypesDAO().insertAll(databaseHolder.ENERGY_TYPES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.ENERGY_TYPES_LIST.addAll(databaseHolder.energyTypesDAO().getAllObjectsAsObject());
      for (EnergyTypes energyTypes : databaseHolder.ENERGY_TYPES_LIST) {
        databaseHolder.ENERGY_TYPES_MAP.put(energyTypes.getItemID(), energyTypes);
      }
    }
  },
  SPECIAL_ATTACKS(DBTableNames.SPECIAL_ATTACKS) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.specialAttacksDAO();
    }

    @Override
    public List<SpecialAttacks> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.SPECIAL_ATTACKS_LIST;
    }

    @Override
    public Map<Integer, SpecialAttacks> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.SPECIAL_ATTACKS_MAP;
    }

    @Override
    public Item getNewObject() {
      return new SpecialAttacks();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.specialAttacksDAO().insertAll(databaseHolder.SPECIAL_ATTACKS_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.SPECIAL_ATTACKS_LIST.addAll(databaseHolder.specialAttacksDAO().getAllObjectsAsObject());
      for (SpecialAttacks specialAttacks : databaseHolder.SPECIAL_ATTACKS_LIST) {
        databaseHolder.SPECIAL_ATTACKS_MAP.put(specialAttacks.getItemID(), specialAttacks);
      }
    }
  },
  SPECIAL_QUALITIES(DBTableNames.SPECIAL_QUALITIES) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.specialQualitiesDAO();
    }

    @Override
    public List<SpecialQualities> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.SPECIAL_QUALITIES_LIST;
    }

    @Override
    public Map<Integer, SpecialQualities> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.SPECIAL_QUALITIES_MAP;
    }

    @Override
    public Item getNewObject() {
      return new SpecialQualities();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.specialQualitiesDAO().insertAll(databaseHolder.SPECIAL_QUALITIES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.SPECIAL_QUALITIES_LIST.addAll(databaseHolder.specialQualitiesDAO().getAllObjectsAsObject());
      for (SpecialQualities specialQualities : databaseHolder.SPECIAL_QUALITIES_LIST) {
        databaseHolder.SPECIAL_QUALITIES_MAP.put(specialQualities.getItemID(), specialQualities);
      }
    }
  },
  /**
   * Skills
   */
  SKILLS(DBTableNames.SKILLS) {
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
      databaseHolder.SKILLS_LIST.addAll(databaseHolder.skillsDAO().getAllObjectsAsObject());
      for (Skills skills : databaseHolder.SKILLS_LIST) {
        databaseHolder.SKILLS_MAP.put(skills.getItemID(), skills);
      }
    }
  },
  /**
   * Feats
   */
  FEATS(DBTableNames.FEATS) {
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
      databaseHolder.FEATS_LIST.addAll(databaseHolder.featsDAO().getAllObjectsAsObject());
      for (Feats feats : databaseHolder.FEATS_LIST) {
        databaseHolder.FEATS_MAP.put(feats.getItemID(), feats);
      }
    }
  },
  /**
   * Races
   */
  RACES(DBTableNames.RACES) {
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
      databaseHolder.RACES_LIST.addAll(databaseHolder.racesDAO().getAllObjectsAsObject());
      for (Races races : databaseHolder.RACES_LIST) {
        databaseHolder.RACES_MAP.put(races.getItemID(), races);
      }
    }
  },
  /**
   * Classes
   */
  CLASSES(DBTableNames.CLASSES) {
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
      databaseHolder.CLASSES_LIST.addAll(databaseHolder.classesDAO().getAllObjectsAsObject());
      for (Classes classes : databaseHolder.CLASSES_LIST) {
        databaseHolder.CLASSES_MAP.put(classes.getItemID(), classes);
      }
    }
  },
  /**
   * Weapons
   */
  WEAPONS(DBTableNames.WEAPONS) {
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
      databaseHolder.WEAPONS_LIST.addAll(databaseHolder.weaponsDAO().getAllObjectsAsObject());
      for (Weapons weapons : databaseHolder.WEAPONS_LIST) {
        databaseHolder.WEAPONS_MAP.put(weapons.getItemID(), weapons);
      }
    }

  },
  /**
   * Armour
   */
  ARMOUR(DBTableNames.ARMOUR) {
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
      databaseHolder.ARMOUR_LIST.addAll(databaseHolder.armourDAO().getAllObjectsAsObject());
      for (Armour armour : databaseHolder.ARMOUR_LIST) {
        databaseHolder.ARMOUR_MAP.put(armour.getItemID(), armour);
      }
    }
  },
  /**
   * Equipment
   */
  EQUIPMENT(DBTableNames.EQUIPMENT) {
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
      databaseHolder.EQUIPMENT_LIST.addAll(databaseHolder.equipmentDAO().getAllObjectsAsObject());
      for (Equipment equipment : databaseHolder.EQUIPMENT_LIST) {
        databaseHolder.EQUIPMENT_MAP.put(equipment.getItemID(), equipment);
      }
    }
  },
  /**
   * Spells
   */
  SPELLS(DBTableNames.SPELLS) {
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
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.SPELLS_LIST.addAll(databaseHolder.spellsDAO().getAllObjectsAsObject());
      for (Spells spells : databaseHolder.SPELLS_LIST) {
        databaseHolder.SPELLS_MAP.put(spells.getItemID(), spells);
      }
    }
  },
  /**
   * Monsters
   */
  MONSTERS(DBTableNames.MONSTERS) {
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
      databaseHolder.MONSTERS_LIST.addAll(databaseHolder.monstersDAO().getAllObjectsAsObject());
      for (Monsters monsters : databaseHolder.MONSTERS_LIST) {
        databaseHolder.MONSTERS_MAP.put(monsters.getItemID(), monsters);
      }
    }
  },
  /**
   * RaceTemplates
   */
  RACE_TEMPLATES(DBTableNames.RACE_TEMPLATES) {
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
      databaseHolder.RACE_TEMPLATES_LIST.addAll(databaseHolder.raceTemplatesDAO().getAllObjectsAsObject());
      for (RaceTemplates raceTemplates : databaseHolder.RACE_TEMPLATES_LIST) {
        databaseHolder.RACE_TEMPLATES_MAP.put(raceTemplates.getItemID(), raceTemplates);
      }
    }
  },
  /**
   * Deities
   */
  DEITIES(DBTableNames.DEITIES) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.deitiesDAO();
    }

    @Override
    public List<Deities> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.DEITIES_LIST;
    }

    @Override
    public Map<Integer, Deities> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.DEITIES_MAP;
    }

    @Override
    public Item getNewObject() {
      return new Deities();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.deitiesDAO().insertAll(databaseHolder.DEITIES_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.DEITIES_LIST.addAll(databaseHolder.deitiesDAO().getAllObjectsAsObject());
      for (Deities deity : databaseHolder.DEITIES_LIST) {
        databaseHolder.DEITIES_MAP.put(deity.getItemID(), deity);
      }
    }
  },
  /**
   * HeroNPC
   */
  HERO_NPC(DBTableNames.HERO_NPC) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroNPCDAO();
    }

    @Override
    public List<HeroNPC> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.HEROES_NPC_LIST;
    }

    @Override
    public Map<Integer, HeroNPC> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.HEROES_NPC_MAP;
    }

    @Override
    public Item getNewObject() {
      return new HeroNPC();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.heroNPCDAO().insertAll(databaseHolder.HEROES_NPC_LIST);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.HEROES_NPC_LIST.addAll(databaseHolder.heroNPCDAO().getAllObjectsAsObject());
      for (HeroNPC heroNPC : databaseHolder.HEROES_NPC_LIST) {
        databaseHolder.HEROES_NPC_MAP.put(heroNPC.getItemID(), heroNPC);
      }
    }
  },
  /**
   * HeroPlayer
   */
  HERO_PLAYER(DBTableNames.HERO_PLAYER) {
    @Override
    public BaseDAO getDAO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroPlayerDAO();
    }

    @Override
    public List<HeroPlayer> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.HEROES_PLAYER_LIST;
    }

    @Override
    public Map<Integer, HeroPlayer> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.HEROES_PLAYER_MAP;
    }

    @Override
    public Item getNewObject() {
      return new HeroPlayer();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      List<Long> baseHeroIds = databaseHolder.heroPlayerDAO().insertAll(databaseHolder.HEROES_PLAYER_LIST);
      List<HeroDescription> descriptions = new ArrayList<>();
      List<HeroValues> statistics = new ArrayList<>();
      for (int i = 0; i < baseHeroIds.size(); i++) {
        Integer heroId = baseHeroIds.get(i) != null ? baseHeroIds.get(i).intValue() : null;
        databaseHolder.HEROES_PLAYER_LIST.get(i).setItemID(heroId);
        HeroDescription description = databaseHolder.HEROES_PLAYER_LIST.get(i).getHeroDescription();
        description.setHeroParentItemID(heroId);
        descriptions.add(description);
        HeroValues statistic = databaseHolder.HEROES_PLAYER_LIST.get(i).getHeroValues();
        statistic.setHeroParentItemID(heroId);
        statistics.add(statistic);
      }
      databaseHolder.heroDescriptionDAO().insertAll(descriptions);
      databaseHolder.heroStatisticsAbilityScoresDAO().insertAll(statistics);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      List<HeroPlayer> heroes = databaseHolder.heroPlayerDAO().getAllObjectsAsMergedObjectItem();
      List<Integer> heroesIds = new ArrayList<>();
      databaseHolder.HEROES_PLAYER_LIST.addAll(heroes);
      for (HeroPlayer heroPlayer : databaseHolder.HEROES_PLAYER_LIST) {
        databaseHolder.HEROES_PLAYER_MAP.put(heroPlayer.getItemID(), heroPlayer);
        heroesIds.add(heroPlayer.getItemID());
        heroPlayer.backupNamesFromIdCreator();
      }
      List<HeroDescription> descriptions = databaseHolder.heroDescriptionDAO().getObjectsWithIdsAsMergedObjectItem(heroesIds);
      List<HeroValues> statistics = databaseHolder.heroStatisticsAbilityScoresDAO()
          .getObjectsWithIdsAsMergedObjectItem(heroesIds);
      for (int i = 0; i < heroes.size(); i++) {
        descriptions.get(i).backupNamesFromIdCreator();
        databaseHolder.HEROES_PLAYER_LIST.get(i).setHeroDescription(descriptions.get(i));
        databaseHolder.HEROES_PLAYER_LIST.get(i).setHeroValues(statistics.get(i));
        for (HeroDescription desc : descriptions) {
          if (desc.getHeroParentItemID().equals(heroesIds.get(i))) {
            databaseHolder.HEROES_PLAYER_MAP.get(heroesIds.get(i)).setHeroDescription(desc);
          }
        }
        for (HeroValues stat : statistics) {
          if (stat.getHeroParentItemID().equals(heroesIds.get(i))) {
            databaseHolder.HEROES_PLAYER_MAP.get(heroesIds.get(i)).setHeroValues(stat);
          }
        }
      }
    }
  },
  /**
   * Translations
   */
  TRANSLATIONS(DBTableNames.TRANSLATIONS) {
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
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.TRANSLATIONS_LIST.addAll(databaseHolder.translationsDAO().getAllObjectsAsObject());
      for (Translations translations : databaseHolder.TRANSLATIONS_LIST) {
        databaseHolder.TRANSLATIONS_MAP.put(translations.getItemID(), translations);
      }
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
}
