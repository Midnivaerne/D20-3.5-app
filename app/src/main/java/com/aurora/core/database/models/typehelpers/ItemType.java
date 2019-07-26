package com.aurora.core.database.models.typehelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DbTableNames;
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.Translations;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.Classes;
import com.aurora.core.database.models.settingspecific.Deities;
import com.aurora.core.database.models.settingspecific.EnergyTypes;
import com.aurora.core.database.models.settingspecific.Feats;
import com.aurora.core.database.models.settingspecific.HeroNpc;
import com.aurora.core.database.models.settingspecific.MaterialTypes;
import com.aurora.core.database.models.settingspecific.Monsters;
import com.aurora.core.database.models.settingspecific.RaceTemplates;
import com.aurora.core.database.models.settingspecific.Races;
import com.aurora.core.database.models.settingspecific.Skills;
import com.aurora.core.database.models.settingspecific.SpecialAttacks;
import com.aurora.core.database.models.settingspecific.SpecialQualities;
import com.aurora.core.database.models.settingspecific.Spells;
import com.aurora.core.database.models.usables.Armour;
import com.aurora.core.database.models.usables.ArmourSpecifics;
import com.aurora.core.database.models.usables.ArmourSubtype;
import com.aurora.core.database.models.usables.ArmourType;
import com.aurora.core.database.models.usables.Equipment;
import com.aurora.core.database.models.usables.Price;
import com.aurora.core.database.models.usables.WeaponSpecifics;
import com.aurora.core.database.models.usables.WeaponSubtype;
import com.aurora.core.database.models.usables.WeaponType;
import com.aurora.core.database.models.usables.Weapons;
import com.aurora.core.database.models.userdata.HeroArmour;
import com.aurora.core.database.models.userdata.HeroDescription;
import com.aurora.core.database.models.userdata.HeroEquipment;
import com.aurora.core.database.models.userdata.HeroPlayer;
import com.aurora.core.database.models.userdata.HeroSkills;
import com.aurora.core.database.models.userdata.HeroValues;
import com.aurora.core.database.models.userdata.HeroWeapons;
import com.aurora.core.helper.BaseDaO;

public enum ItemType implements CoreTypeHelper<ItemType, Item> {
  /**
   * Databases a.k.a Sources
   */
  DATABASES(DbTableNames.DATABASES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.databasesDaO();
    }

    @Override
    public List<Databases> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.databasesList;
    }

    @Override
    public Map<Integer, Databases> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.databasesMap;
    }

    @Override
    public Item getNewObject() {
      return new Databases();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.databasesDaO().insertAll(databaseHolder.databasesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.databasesList.addAll(databaseHolder.databasesDaO().getAllObjectsAsObject());
      for (Databases databases : databaseHolder.databasesList) {
        databaseHolder.databasesMap.put(databases.getItemID(), databases);
      }
    }
  },
  MATERIAL_TYPES(DbTableNames.MATERIAL_TYPES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.materialTypesDaO();
    }

    @Override
    public List<MaterialTypes> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.materialTypesList;
    }

    @Override
    public Map<Integer, MaterialTypes> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.materialTypesMap;
    }

    @Override
    public Item getNewObject() {
      return new MaterialTypes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.materialTypesDaO().insertAll(databaseHolder.materialTypesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.materialTypesList.addAll(databaseHolder.materialTypesDaO().getAllObjectsAsObject());
      for (MaterialTypes materialTypes : databaseHolder.materialTypesList) {
        databaseHolder.materialTypesMap.put(materialTypes.getItemID(), materialTypes);
      }
    }
  },
  ENERGY_TYPES(DbTableNames.ENERGY_TYPES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.energyTypesDaO();
    }

    @Override
    public List<EnergyTypes> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.energyTypesList;
    }

    @Override
    public Map<Integer, EnergyTypes> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.energyTypesMap;
    }

    @Override
    public Item getNewObject() {
      return new EnergyTypes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.energyTypesDaO().insertAll(databaseHolder.energyTypesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.energyTypesList.addAll(databaseHolder.energyTypesDaO().getAllObjectsAsObject());
      for (EnergyTypes energyTypes : databaseHolder.energyTypesList) {
        databaseHolder.energyTypesMap.put(energyTypes.getItemID(), energyTypes);
      }
    }
  },
  SPECIAL_ATTACKS(DbTableNames.SPECIAL_ATTACKS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.specialAttacksDaO();
    }

    @Override
    public List<SpecialAttacks> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.specialAttacksList;
    }

    @Override
    public Map<Integer, SpecialAttacks> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.specialAttacksMap;
    }

    @Override
    public Item getNewObject() {
      return new SpecialAttacks();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.specialAttacksDaO().insertAll(databaseHolder.specialAttacksList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.specialAttacksList.addAll(databaseHolder.specialAttacksDaO().getAllObjectsAsObject());
      for (SpecialAttacks specialAttacks : databaseHolder.specialAttacksList) {
        databaseHolder.specialAttacksMap.put(specialAttacks.getItemID(), specialAttacks);
      }
    }
  },
  SPECIAL_QUALITIES(DbTableNames.SPECIAL_QUALITIES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.specialQualitiesDaO();
    }

    @Override
    public List<SpecialQualities> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.specialQualitiesList;
    }

    @Override
    public Map<Integer, SpecialQualities> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.specialQualitiesMap;
    }

    @Override
    public Item getNewObject() {
      return new SpecialQualities();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.specialQualitiesDaO().insertAll(databaseHolder.specialQualitiesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.specialQualitiesList.addAll(databaseHolder.specialQualitiesDaO().getAllObjectsAsObject());
      for (SpecialQualities specialQualities : databaseHolder.specialQualitiesList) {
        databaseHolder.specialQualitiesMap.put(specialQualities.getItemID(), specialQualities);
      }
    }
  },
  /**
   * Skills.
   */
  SKILLS(DbTableNames.SKILLS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.skillsDaO();
    }

    @Override
    public List<Skills> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.skillsList;
    }

    @Override
    public Map<Integer, Skills> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.skillsMap;
    }

    @Override
    public Item getNewObject() {
      return new Skills();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.skillsDaO().insertAll(databaseHolder.skillsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.skillsList.addAll(databaseHolder.skillsDaO().getAllObjectsAsObject());
      for (Skills skills : databaseHolder.skillsList) {
        databaseHolder.skillsMap.put(skills.getItemID(), skills);
      }
    }
  },
  /**
   * Feats.
   */
  FEATS(DbTableNames.FEATS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.featsDaO();
    }

    @Override
    public List<Feats> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.featsList;
    }

    @Override
    public Map<Integer, Feats> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.featsMap;
    }

    @Override
    public Item getNewObject() {
      return new Feats();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.featsDaO().insertAll(databaseHolder.featsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.featsList.addAll(databaseHolder.featsDaO().getAllObjectsAsObject());
      for (Feats feats : databaseHolder.featsList) {
        databaseHolder.featsMap.put(feats.getItemID(), feats);
      }
    }
  },
  /**
   * Races.
   */
  RACES(DbTableNames.RACES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.racesDaO();
    }

    @Override
    public List<Races> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.racesList;
    }

    @Override
    public Map<Integer, Races> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.racesMap;
    }

    @Override
    public Races getNewObject() {
      return new Races();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.racesDaO().insertAll(databaseHolder.racesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.racesList.addAll(databaseHolder.racesDaO().getAllObjectsAsObject());
      for (Races races : databaseHolder.racesList) {
        databaseHolder.racesMap.put(races.getItemID(), races);
      }
    }
  },
  /**
   * Classes.
   */
  CLASSES(DbTableNames.CLASSES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.classesDaO();
    }

    @Override
    public List<Classes> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.classesList;
    }

    @Override
    public Map<Integer, Classes> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.classesMap;
    }

    @Override
    public Item getNewObject() {
      return new Classes();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.classesDaO().insertAll(databaseHolder.classesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.classesList.addAll(databaseHolder.classesDaO().getAllObjectsAsObject());
      for (Classes classes : databaseHolder.classesList) {
        databaseHolder.classesMap.put(classes.getItemID(), classes);
      }
    }
  },
  /**
   * Price.
   */
  PRICE(DbTableNames.PRICE) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.priceDaO();
    }

    @Override
    public List<Price> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.priceList;
    }

    @Override
    public Map<Integer, Price> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.priceMap;
    }

    @Override
    public Item getNewObject() {
      return new Price();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.priceDaO().insertAll(databaseHolder.priceList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.priceList.addAll(databaseHolder.priceDaO().getAllObjectsAsObject());
      for (Price price : databaseHolder.priceList) {
        databaseHolder.priceMap.put(price.getItemID(), price);
      }
    }
  },
  /**
   * Weapon types.
   */
  WEAPON_TYPE(DbTableNames.WEAPON_TYPE) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponTypeDaO();
    }

    @Override
    public List<WeaponType> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponTypeList;
    }

    @Override
    public Map<Integer, WeaponType> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponTypeMap;
    }

    @Override
    public Item getNewObject() {
      return new WeaponType();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.weaponTypeDaO().insertAll(databaseHolder.weaponTypeList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.weaponTypeList.addAll(databaseHolder.weaponTypeDaO().getAllObjectsAsObject());
      for (WeaponType weaponType : databaseHolder.weaponTypeList) {
        databaseHolder.weaponTypeMap.put(weaponType.getItemID(), weaponType);
      }
    }
  },
  /**
   * Weapons sub-types.
   */
  WEAPON_SUBTYPE(DbTableNames.WEAPON_SUBTYPE) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponSubtypeDaO();
    }

    @Override
    public List<WeaponSubtype> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponSubtypeList;
    }

    @Override
    public Map<Integer, WeaponSubtype> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponSubtypeMap;
    }

    @Override
    public Item getNewObject() {
      return new WeaponSubtype();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.weaponSubtypeDaO().insertAll(databaseHolder.weaponSubtypeList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.weaponSubtypeList.addAll(databaseHolder.weaponSubtypeDaO().getAllObjectsAsObject());
      for (WeaponSubtype weaponSubtype : databaseHolder.weaponSubtypeList) {
        databaseHolder.weaponSubtypeMap.put(weaponSubtype.getItemID(), weaponSubtype);
      }
    }
  },
  /**
   * Weapons specific construction.
   */
  WEAPON_SPECIFICS(DbTableNames.WEAPON_SPECIFICS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponSpecificsDaO();
    }

    @Override
    public List<WeaponSpecifics> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponSpecificsList;
    }

    @Override
    public Map<Integer, WeaponSpecifics> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponSpecificsMap;
    }

    @Override
    public Item getNewObject() {
      return new WeaponSpecifics();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.weaponSpecificsDaO().insertAll(databaseHolder.weaponSpecificsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.weaponSpecificsList.addAll(databaseHolder.weaponSpecificsDaO().getAllObjectsAsObject());
      for (WeaponSpecifics weaponSpecifics : databaseHolder.weaponSpecificsList) {
        databaseHolder.weaponSpecificsMap.put(weaponSpecifics.getItemID(), weaponSpecifics);
      }
    }
  },
  /**
   * Weapons.
   */
  WEAPONS(DbTableNames.WEAPONS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponsDaO();
    }

    @Override
    public List<Weapons> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponsList;
    }

    @Override
    public Map<Integer, Weapons> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.weaponsMap;
    }

    @Override
    public Item getNewObject() {
      return new Weapons();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.weaponsDaO().insertAll(databaseHolder.weaponsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.weaponsList.addAll(databaseHolder.weaponsDaO().getAllObjectsAsObject());
      for (Weapons weapons : databaseHolder.weaponsList) {
        databaseHolder.weaponsMap.put(weapons.getItemID(), weapons);
      }
    }
  },
  /**
   * Weapon types.
   */
  ARMOUR_TYPE(DbTableNames.ARMOUR_TYPE) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.armourTypeDaO();
    }

    @Override
    public List<ArmourType> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.armourTypeList;
    }

    @Override
    public Map<Integer, ArmourType> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.armourTypeMap;
    }

    @Override
    public Item getNewObject() {
      return new ArmourType();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.armourTypeDaO().insertAll(databaseHolder.armourTypeList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.armourTypeList.addAll(databaseHolder.armourTypeDaO().getAllObjectsAsObject());
      for (ArmourType armourType : databaseHolder.armourTypeList) {
        databaseHolder.armourTypeMap.put(armourType.getItemID(), armourType);
      }
    }
  },
  /**
   * Weapons sub-types.
   */
  ARMOUR_SUBTYPE(DbTableNames.ARMOUR_SUBTYPE) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.armourSubtypeDaO();
    }

    @Override
    public List<ArmourSubtype> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.armourSubtypeList;
    }

    @Override
    public Map<Integer, ArmourSubtype> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.armourSubtypeMap;
    }

    @Override
    public Item getNewObject() {
      return new ArmourSubtype();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.armourSubtypeDaO().insertAll(databaseHolder.armourSubtypeList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.armourSubtypeList.addAll(databaseHolder.armourSubtypeDaO().getAllObjectsAsObject());
      for (ArmourSubtype armourSubtype : databaseHolder.armourSubtypeList) {
        databaseHolder.armourSubtypeMap.put(armourSubtype.getItemID(), armourSubtype);
      }
    }
  },
  /**
   * Weapons specific construction.
   */
  ARMOUR_SPECIFICS(DbTableNames.ARMOUR_SPECIFICS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.armourSpecificsDaO();
    }

    @Override
    public List<ArmourSpecifics> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.armourSpecificsList;
    }

    @Override
    public Map<Integer, ArmourSpecifics> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.armourSpecificsMap;
    }

    @Override
    public Item getNewObject() {
      return new ArmourSpecifics();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.armourSpecificsDaO().insertAll(databaseHolder.armourSpecificsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.armourSpecificsList.addAll(databaseHolder.armourSpecificsDaO().getAllObjectsAsObject());
      for (ArmourSpecifics armourSpecifics : databaseHolder.armourSpecificsList) {
        databaseHolder.armourSpecificsMap.put(armourSpecifics.getItemID(), armourSpecifics);
      }
    }
  },
  /**
   * Armour.
   */
  ARMOUR(DbTableNames.ARMOUR) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.armourDaO();
    }

    @Override
    public List<Armour> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.armourList;
    }

    @Override
    public Map<Integer, Armour> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.armourMap;
    }

    @Override
    public Item getNewObject() {
      return new Armour();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.armourDaO().insertAll(databaseHolder.armourList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.armourList.addAll(databaseHolder.armourDaO().getAllObjectsAsObject());
      for (Armour armour : databaseHolder.armourList) {
        databaseHolder.armourMap.put(armour.getItemID(), armour);
      }
    }
  },
  /**
   * Equipment.
   */
  EQUIPMENT(DbTableNames.EQUIPMENT) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.equipmentDaO();
    }

    @Override
    public List<Equipment> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.equipmentList;
    }

    @Override
    public Map<Integer, Equipment> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.equipmentMap;
    }

    @Override
    public Item getNewObject() {
      return new Equipment();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.equipmentDaO().insertAll(databaseHolder.equipmentList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.equipmentList.addAll(databaseHolder.equipmentDaO().getAllObjectsAsObject());
      for (Equipment equipment : databaseHolder.equipmentList) {
        databaseHolder.equipmentMap.put(equipment.getItemID(), equipment);
      }
    }
  },
  /**
   * Spells.
   */
  SPELLS(DbTableNames.SPELLS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.spellsDaO();
    }

    @Override
    public List<Spells> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.spellsList;
    }

    @Override
    public Map<Integer, Spells> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.spellsMap;
    }

    @Override
    public Item getNewObject() {
      return new Spells();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.spellsDaO().insertAll(databaseHolder.spellsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.spellsList.addAll(databaseHolder.spellsDaO().getAllObjectsAsObject());
      for (Spells spells : databaseHolder.spellsList) {
        databaseHolder.spellsMap.put(spells.getItemID(), spells);
      }
    }
  },
  /**
   * Monsters.
   */
  MONSTERS(DbTableNames.MONSTERS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.monstersDaO();
    }

    @Override
    public List<Monsters> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.monstersList;
    }

    @Override
    public Map<Integer, Monsters> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.monstersMap;
    }

    @Override
    public Item getNewObject() {
      return new Monsters();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.monstersDaO().insertAll(databaseHolder.monstersList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.monstersList.addAll(databaseHolder.monstersDaO().getAllObjectsAsObject());
      for (Monsters monsters : databaseHolder.monstersList) {
        databaseHolder.monstersMap.put(monsters.getItemID(), monsters);
      }
    }
  },
  /**
   * RaceTemplates.
   */
  RACE_TEMPLATES(DbTableNames.RACE_TEMPLATES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.raceTemplatesDaO();
    }

    @Override
    public List<RaceTemplates> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.raceTemplatesList;
    }

    @Override
    public Map<Integer, RaceTemplates> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.raceTemplatesMap;
    }

    @Override
    public Item getNewObject() {
      return new RaceTemplates();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.raceTemplatesDaO().insertAll(databaseHolder.raceTemplatesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.raceTemplatesList.addAll(databaseHolder.raceTemplatesDaO().getAllObjectsAsObject());
      for (RaceTemplates raceTemplates : databaseHolder.raceTemplatesList) {
        databaseHolder.raceTemplatesMap.put(raceTemplates.getItemID(), raceTemplates);
      }
    }
  },
  /**
   * Deities.
   */
  DEITIES(DbTableNames.DEITIES) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.deitiesDaO();
    }

    @Override
    public List<Deities> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.deitiesList;
    }

    @Override
    public Map<Integer, Deities> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.deitiesMap;
    }

    @Override
    public Item getNewObject() {
      return new Deities();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.deitiesDaO().insertAll(databaseHolder.deitiesList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.deitiesList.addAll(databaseHolder.deitiesDaO().getAllObjectsAsObject());
      for (Deities deity : databaseHolder.deitiesList) {
        databaseHolder.deitiesMap.put(deity.getItemID(), deity);
      }
    }
  },
  /**
   * HeroNpc.
   */
  HERO_NPC(DbTableNames.HERO_NPC) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroNpcDaO();
    }

    @Override
    public List<HeroNpc> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesNpcList;
    }

    @Override
    public Map<Integer, HeroNpc> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesNpcMap;
    }

    @Override
    public Item getNewObject() {
      return new HeroNpc();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.heroNpcDaO().insertAll(databaseHolder.heroesNpcList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.heroesNpcList.addAll(databaseHolder.heroNpcDaO().getAllObjectsAsObject());
      for (HeroNpc heroNpc : databaseHolder.heroesNpcList) {
        databaseHolder.heroesNpcMap.put(heroNpc.getItemID(), heroNpc);
      }
    }
  },
  /**
   * HeroPlayer.
   */
  HERO_PLAYER(DbTableNames.HERO_PLAYER) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroPlayerDaO();
    }

    @Override
    public List<HeroPlayer> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesPlayerList;
    }

    @Override
    public Map<Integer, HeroPlayer> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesPlayerMap;
    }

    @Override
    public Item getNewObject() {
      return new HeroPlayer();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      List<Long> baseHeroIds = databaseHolder.heroPlayerDaO().insertAllWithHeroUpdate(databaseHolder.heroesPlayerList);
      List<HeroDescription> descriptionsSet = new ArrayList<>();
      List<HeroValues> statisticsSet = new ArrayList<>();
      List<HeroSkills> skillsSet = new ArrayList<>();
      for (int i = 0; i < baseHeroIds.size(); i++) {
        Integer heroId = baseHeroIds.get(i) != null ? baseHeroIds.get(i).intValue() : null;
        databaseHolder.heroesPlayerList.get(i).setItemID(heroId);

        HeroDescription description = databaseHolder.heroesPlayerList.get(i).getHeroDescription();
        description.setHeroParentHeroId(heroId);
        descriptionsSet.add(description);

        HeroValues statistic = databaseHolder.heroesPlayerList.get(i).getHeroValues();
        statistic.setHeroParentHeroId(heroId);
        statisticsSet.add(statistic);

        HeroSkills skills = databaseHolder.heroesPlayerList.get(i).getHeroSkills();
        skills.setHeroParentHeroId(heroId);
        skillsSet.add(skills);
      }
      databaseHolder.heroDescriptionDaO().insertAll(descriptionsSet);
      databaseHolder.heroStatisticsAbilityScoresDaO().insertAll(statisticsSet);
      databaseHolder.heroSkillsDaO().insertAll(skillsSet);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      Map<Integer, HeroDescription> descriptions = (Map<Integer, HeroDescription>) databaseHolder.heroDescriptionDaO()
          .getAllObjectsAsMergedObjectItem();
      Map<Integer, Integer> parentToDescriptionId = new HashMap<>();
      for (Integer descrId : descriptions.keySet()) {
        parentToDescriptionId.put(descriptions.get(descrId) != null ? descrId : descriptions.get(descrId).getHeroParentHeroId(), descrId);
      }

      Map<Integer, HeroValues> statistics = (Map<Integer, HeroValues>) databaseHolder.heroStatisticsAbilityScoresDaO()
          .getAllObjectsAsMergedObjectItem();
      Map<Integer, Integer> parentToStatisticsId = new HashMap<>();
      for (Integer statsId : statistics.keySet()) {
        parentToStatisticsId.put(statistics.get(statsId) != null ? statsId : statistics.get(statsId).getHeroParentHeroId(), statsId);
      }

      Map<Integer, HeroSkills> skills = (Map<Integer, HeroSkills>) databaseHolder.heroSkillsDaO().getAllObjectsAsMergedObjectItem();
      Map<Integer, Integer> parentToSkillsId = new HashMap<>();
      for (Integer skillsId : skills.keySet()) {
        parentToSkillsId.put(skills.get(skillsId) != null ? skillsId : skills.get(skillsId).getHeroParentHeroId(), skillsId);
      }
      List<HeroPlayer> heroes = new ArrayList<>(databaseHolder.heroPlayerDaO().getHeroPlayerObjectsWithHeroAndItemFields().values());
      heroes.forEach((h) -> h.setHeroDescription(descriptions.get(parentToDescriptionId.get(h.getItemID()))));
      heroes.forEach((h) -> h.setHeroValues(statistics.get(parentToStatisticsId.get(h.getItemID()))));
      heroes.forEach((h) -> h.setHeroSkills(skills.get(parentToSkillsId.get(h.getItemID()))));

      for (HeroPlayer heroPlayer : heroes) {
        heroPlayer.backupNamesFromIdCreator();
        heroPlayer.getHeroValues().setIdAsNameBackup(heroPlayer.getIdAsNameBackup());
        heroPlayer.getHeroValues().backupNamesFromIdCreator();
        heroPlayer.getHeroDescription().setIdAsNameBackup(heroPlayer.getIdAsNameBackup());
        heroPlayer.getHeroDescription().backupNamesFromIdCreator();
        heroPlayer.getHeroSkills().setIdAsNameBackup(heroPlayer.getIdAsNameBackup());
        heroPlayer.getHeroSkills().backupNamesFromIdCreator();
        databaseHolder.heroesPlayerMap.put(heroPlayer.getItemID(), heroPlayer);
      }
      databaseHolder.heroesPlayerList.addAll(heroes);
    }
  },
  /**
   * HeroWeapons.
   */
  HERO_PLAYER_WEAPONS(DbTableNames.HERO_WEAPONS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroWeaponsDaO();
    }

    @Override
    public List<HeroWeapons> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesWeaponsList;
    }

    @Override
    public Map<Integer, HeroWeapons> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesWeaponsMap;
    }

    @Override
    public Item getNewObject() {
      return new HeroWeapons();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.heroWeaponsDaO().insertAll(databaseHolder.heroesWeaponsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.heroesWeaponsList.addAll(databaseHolder.heroWeaponsDaO().getAllObjectsAsObject());
      for (HeroWeapons heroWeapons : databaseHolder.heroesWeaponsList) {
        databaseHolder.heroesWeaponsMap.put(heroWeapons.getItemID(), heroWeapons);
      }
    }
  },
  /**
   * HeroArmour.
   */
  HERO_PLAYER_ARMOUR(DbTableNames.HERO_ARMOUR) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroArmourDaO();
    }

    @Override
    public List<HeroArmour> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesArmourList;
    }

    @Override
    public Map<Integer, HeroArmour> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesArmourMap;
    }

    @Override
    public Item getNewObject() {
      return new HeroArmour();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.heroArmourDaO().insertAll(databaseHolder.heroesArmourList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.heroesArmourList.addAll(databaseHolder.heroArmourDaO().getAllObjectsAsObject());
      for (HeroArmour heroArmour : databaseHolder.heroesArmourList) {
        databaseHolder.heroesArmourMap.put(heroArmour.getItemID(), heroArmour);
      }
    }
  },
  /**
   * HeroEquipment.
   */
  HERO_PLAYER_EQUIPMENT(DbTableNames.HERO_EQUIPMENT) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.heroEquipmentDaO();
    }

    @Override
    public List<HeroEquipment> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesEquipmentList;
    }

    @Override
    public Map<Integer, HeroEquipment> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.heroesEquipmentMap;
    }

    @Override
    public Item getNewObject() {
      return new HeroEquipment();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.heroEquipmentDaO().insertAll(databaseHolder.heroesEquipmentList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.heroesEquipmentList.addAll(databaseHolder.heroEquipmentDaO().getAllObjectsAsObject());
      for (HeroEquipment heroEquipment : databaseHolder.heroesEquipmentList) {
        databaseHolder.heroesEquipmentMap.put(heroEquipment.getItemID(), heroEquipment);
      }
    }
  },
  /**
   * Translations.
   */
  TRANSLATIONS(DbTableNames.TRANSLATIONS) {
    @Override
    public BaseDaO getDaO(DatabaseHolder databaseHolder) {
      return databaseHolder.translationsDaO();
    }

    @Override
    public List<Translations> getDatabaseList(DatabaseHolder databaseHolder) {
      return databaseHolder.translationsList;
    }

    @Override
    public Map<Integer, Translations> getDatabaseMap(DatabaseHolder databaseHolder) {
      return databaseHolder.translationsMap;
    }

    @Override
    public Item getNewObject() {
      return new Translations();
    }

    @Override
    public void fromHolderToDatabase(DatabaseHolder databaseHolder) {
      databaseHolder.translationsDaO().insertAll(databaseHolder.translationsList);
    }

    @Override
    public void fromDatabaseToHolder(DatabaseHolder databaseHolder) {
      databaseHolder.translationsList.addAll(databaseHolder.translationsDaO().getAllObjectsAsObject());
      for (Translations translations : databaseHolder.translationsList) {
        databaseHolder.translationsMap.put(translations.getItemID(), translations);
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
