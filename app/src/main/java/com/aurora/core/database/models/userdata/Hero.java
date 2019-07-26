package com.aurora.core.database.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_LEFT_HAND_HELD_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_RIGHT_HAND_HELD_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_WORN_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.models.userdata.HeroValues.getStatisticModifier;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.player.playercharacterutils.PlayerCharacterAbilityScoresEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Hero extends Item {

  @Ignore
  private HeroValues heroValues;

  @Ignore
  private HeroSkills heroSkills;

  @Ignore
  private Map<Integer, HeroWeapons> heroWeaponsFromIdMap;

  @Ignore
  private LinkedHashMap<Integer, List<Integer>> heroWeaponsWithAmmoMap;

  @Ignore
  private Map<Integer, HeroArmour> heroArmourFromIdMap;

  @ColumnInfo(name = HERO_RIGHT_HAND_HELD_ITEM_ID_COLUMN_NAME)
  private Integer rightHandHeldItemId;

  @ColumnInfo(name = HERO_LEFT_HAND_HELD_ITEM_ID_COLUMN_NAME)
  private Integer leftHandHeldItemId;

  @ColumnInfo(name = HERO_WORN_ITEM_ID_COLUMN_NAME)
  private Integer wornItemId;

  @Ignore
  private ArrayList<HeroEquipment> heroEquipment;

  @Ignore
  private Map<PlayerCharacterWornEquipmentPlacesEnum, HeroEquipment> heroPlaceEquipmentMap = new HashMap<>();

  @Ignore
  private LinkedHashMap<HeroEquipment, List<HeroEquipment>> heroContainerEquipmentMap = new LinkedHashMap<>();

  public Hero() {
    super();
    this.setHeroValues(new HeroValues(this.getBackupNames()));
    this.setHeroSkills(new HeroSkills(this.getBackupNames()));
  }

  public Hero(String name,
      String source,
      String idAsNameBackup) {
    new Hero(name, source, idAsNameBackup, null, null, null, null, null);
  }

  public Hero(String name,
      String source,
      String idAsNameBackup,
      Integer rightHandHeldItemId,
      Integer leftHandHeldItemId,
      Integer wornItemId) {
    new Hero(name, source, idAsNameBackup, null, null, rightHandHeldItemId, leftHandHeldItemId, wornItemId);
  }


  public Hero(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues,
      HeroSkills heroSkills,
      Integer rightHandHeldItemId,
      Integer leftHandHeldItemId,
      Integer wornItemId) {
    super(name, source, idAsNameBackup);
    this.setHeroValues(heroValues == null ? new HeroValues(this.getBackupNames()) : new HeroValues(heroValues));
    this.setHeroSkills(heroSkills == null ? new HeroSkills(this.getBackupNames()) : new HeroSkills(heroSkills));
    this.setRightHandHeldItemId(rightHandHeldItemId);
    this.setLeftHandHeldItemId(leftHandHeldItemId);
    this.setWornItemId(wornItemId);
  }

  public Hero(Hero source) {
    new Hero(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroValues(),
        source.getHeroSkills(),
        source.getRightHandHeldItemId(),
        source.getLeftHandHeldItemId(),
        source.getWornItemId());
  }

  @Ignore
  public void generateAll(DatabaseHolder databaseHolder) {
    getHeroValues().generateAll(databaseHolder);
    generateSkills(databaseHolder);
    generateWeapons(databaseHolder);
    generateArmour(databaseHolder);
    generateEquipment(databaseHolder);
  }

  @Ignore
  private void generateSkills(DatabaseHolder databaseHolder) {
    getHeroSkills().generateSkillListAsSkillAndRank(databaseHolder);
    for (PlayerCharacterAbilityScoresEnum attr : getHeroValues().getAbilityScoreValues().keySet()) {
      getHeroSkills().getAttributeModifiers().put(attr, getStatisticModifier(getHeroValues().getAbilityScoreValues().get(attr)));
    }
    getHeroSkills().loadAllSettingSkills(databaseHolder, getHeroValues().getRace().getRaceSkills(),
        getHeroValues().getRaceTemplate() != null ? getHeroValues().getRaceTemplate().getRaceTemplateSkills() : null);
  }

  @Ignore
  private void generateWeapons(DatabaseHolder databaseHolder) {
    setHeroWeaponsFromIdMap(new HashMap<Integer, HeroWeapons>());
    Map<Integer, List<Integer>> ammoTypeIdMap = new LinkedHashMap<Integer, List<Integer>>();
    for (HeroWeapons heroWeapon : databaseHolder.heroesWeaponsList) {
      if (heroWeapon.getHeroParentHeroId().equals(this.getItemID())) {
        heroWeapon.generateAll(databaseHolder);
        getHeroWeaponsFromIdMap().put(heroWeapon.getItemID(), heroWeapon);
        if (heroWeapon.getWeapon().getWeaponType().getIsAmmo()) {
          if (!ammoTypeIdMap.containsKey(heroWeapon.getWeapon().getWeaponSubtype().getItemID())) {
            ammoTypeIdMap.put(heroWeapon.getWeapon().getWeaponSubtypeId(), new ArrayList<Integer>());
          }
          ammoTypeIdMap.get(heroWeapon.getWeapon().getWeaponSubtypeId()).add(heroWeapon.getItemID());
        }
      }
    }
    setHeroWeaponsWithAmmoMap(new LinkedHashMap<Integer, List<Integer>>());
    for (HeroWeapons heroWeapon : getHeroWeaponsFromIdMap().values()) {
      if (heroWeapon.getWeapon().getWeaponType().getCanHaveAmmo()
          && heroWeapon.getWeapon().getWeaponSubtype().getUsedAmmoTypeId() != null) {
        heroWeapon.setSelectedAmmoId(ammoTypeIdMap.get(heroWeapon.getWeapon().getWeaponSubtype().getUsedAmmoTypeId()).stream()
            .filter(hwid -> hwid.equals(heroWeapon.getSelectedAmmoId())).findAny().orElse(null));
        getHeroWeaponsWithAmmoMap()
            .put(heroWeapon.getItemID(), ammoTypeIdMap.get(heroWeapon.getWeapon().getWeaponSubtype().getUsedAmmoTypeId()));
      } else if (!heroWeapon.getWeapon().getWeaponType().getIsAmmo()) {
        getHeroWeaponsWithAmmoMap().put(heroWeapon.getItemID(), null);
      }
    }
  }

  @Ignore
  private void generateArmour(DatabaseHolder databaseHolder) {
    setHeroArmourFromIdMap(new HashMap<Integer, HeroArmour>());
    for (HeroArmour heroArmour : databaseHolder.heroesArmourList) {
      if (heroArmour.getHeroParentHeroId().equals(this.getItemID())) {
        heroArmour.generateAll(databaseHolder);
        getHeroArmourFromIdMap().put(heroArmour.getItemID(), heroArmour);
      }
    }
  }

  @Ignore
  private void generateEquipment(DatabaseHolder databaseHolder) {
    setHeroEquipment(new ArrayList<HeroEquipment>());
    for (HeroEquipment heroEquipment : databaseHolder.heroesEquipmentList) {
      if (heroEquipment.getHeroParentHeroId().equals(this.getItemID())) {
        heroEquipment.generateAll(databaseHolder);
        getHeroEquipment().add(heroEquipment);
      }
    }
    if (getHeroEquipment() != null) {
      for (HeroEquipment heroEquipment : getHeroEquipment()) {
        if (heroEquipment.getWornPlace() != null) {
          getHeroPlaceEquipmentMap().put(heroEquipment.getWornPlace(), heroEquipment);
        } else {
          HeroEquipment parentContainer = databaseHolder.heroesEquipmentMap.get(heroEquipment.getParentContainer());
          if (parentContainer != null) {
            if (!getHeroContainerEquipmentMap().containsKey(parentContainer)) {
              getHeroContainerEquipmentMap()
                  .put(databaseHolder.heroesEquipmentMap.get(heroEquipment.getParentContainer()), new ArrayList<HeroEquipment>());
            }
            getHeroContainerEquipmentMap().get(parentContainer).add(heroEquipment);
          } else {
            //todo manage "lost" items
          }
        }
      }
    }
  }
}
