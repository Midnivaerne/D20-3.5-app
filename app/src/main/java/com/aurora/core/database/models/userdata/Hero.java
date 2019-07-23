package com.aurora.core.database.models.userdata;

import static com.aurora.core.database.models.userdata.HeroValues.getStatisticModifier;

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
import com.aurora.player.playercharacterutils.PlayerCharacterArmourEnum;
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
  private ArrayList<HeroArmour> heroArmour;

  @Ignore
  private Map<PlayerCharacterArmourEnum, HeroArmour> heroArmourMap = new HashMap<>();

  @Ignore
  private ArrayList<HeroEquipment> heroEquipment;

  @Ignore
  private Map<PlayerCharacterWornEquipmentPlacesEnum, HeroEquipment> heroPlaceEquipmentMap = new HashMap<>();

  @Ignore
  private LinkedHashMap<HeroEquipment, List<HeroEquipment>> heroContainerEquipmentMap = new LinkedHashMap<>();

  @Ignore
  public Hero() {
    super();
    this.setHeroValues(new HeroValues(this.getBackupNames()));
    this.setHeroSkills(new HeroSkills(this.getBackupNames()));
  }

  @Ignore
  public Hero(String name,
      String source,
      String idAsNameBackup) {
    new Hero(name, source, idAsNameBackup, null, null);
  }


  public Hero(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues,
      HeroSkills heroSkills) {
    super(name, source, idAsNameBackup);
    this.setHeroValues(heroValues == null ? new HeroValues(this.getBackupNames()) : new HeroValues(heroValues));
    this.setHeroSkills(heroSkills == null ? new HeroSkills(this.getBackupNames()) : new HeroSkills(heroSkills));
  }

  @Ignore
  public Hero(Hero source) {
    new Hero(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroValues(),
        source.getHeroSkills());
  }

  public void generateAll(DatabaseHolder databaseHolder) {
    getHeroValues().generateAll(databaseHolder);
    generateSkills(databaseHolder);
    generateWeapons(databaseHolder);
    generateArmour(databaseHolder);
    generateEquipment(databaseHolder);
  }

  private void generateSkills(DatabaseHolder databaseHolder) {
    getHeroSkills().generateSkillListAsSkillAndRank(databaseHolder);
    for (PlayerCharacterAbilityScoresEnum attr : getHeroValues().getAbilityScoreValues().keySet()) {
      getHeroSkills().getAttributeModifiers().put(attr, getStatisticModifier(getHeroValues().getAbilityScoreValues().get(attr)));
    }
    getHeroSkills().loadAllSettingSkills(databaseHolder, getHeroValues().getRace().getRaceSkills(),
        getHeroValues().getRaceTemplate() != null ? getHeroValues().getRaceTemplate().getRaceTemplateSkills() : null);
  }

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

  private void generateArmour(DatabaseHolder databaseHolder) {
    setHeroArmour(new ArrayList<HeroArmour>());
    for (HeroArmour heroArmour : databaseHolder.heroesArmourList) {
      if (heroArmour.getHeroParentHeroId().equals(this.getItemID())) {
        heroArmour.generateAll(databaseHolder);
        getHeroArmour().add(heroArmour);
      }
    }
  }

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
