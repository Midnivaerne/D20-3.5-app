package com.aurora.core.models.userdata;

import static com.aurora.core.models.userdata.HeroValues.getStatisticModifier;

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
import com.aurora.core.models.helpers.Item;
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
  private ArrayList<HeroWeapons> heroWeapons;

  @Ignore
  private LinkedHashMap<HeroWeapons, List<HeroWeapons>> heroWeaponsMap;

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

    getHeroSkills().generateSkillListAsSkillAndRank(databaseHolder);
    for (PlayerCharacterAbilityScoresEnum attr : getHeroValues().getAbilityScoreValues().keySet()) {
      getHeroSkills().getAttributeModifiers().put(attr, getStatisticModifier(getHeroValues().getAbilityScoreValues().get(attr)));
    }
    getHeroSkills().loadAllSettingSkills(databaseHolder, getHeroValues().getRace().getRaceSkills(),
        getHeroValues().getRaceTemplate() != null ? getHeroValues().getRaceTemplate().getRaceTemplateSkills() : null);

    for (HeroWeapons heroWeapon : databaseHolder.heroesWeaponsList) {
      if (heroWeapon.getHeroParentItemID().equals(this.getItemID())) {
        heroWeapon.generateAll(databaseHolder);
        getHeroWeapons().add(heroWeapon);
      }
    }

    for (HeroArmour heroArmour : databaseHolder.heroesArmourList) {
      if (heroArmour.getHeroParentItemID().equals(this.getItemID())) {
        heroArmour.generateAll(databaseHolder);
        getHeroArmour().add(heroArmour);
      }
    }

    for (HeroEquipment heroEquipment : databaseHolder.heroesEquipmentList) {
      if (heroEquipment.getHeroParentItemID().equals(this.getItemID())) {
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
          if (!getHeroContainerEquipmentMap().containsKey(parentContainer)) {
            getHeroContainerEquipmentMap()
                .put(databaseHolder.heroesEquipmentMap.get(heroEquipment.getParentContainer()), new ArrayList<HeroEquipment>());
          }
          getHeroContainerEquipmentMap().get(parentContainer).add(heroEquipment);
        }
      }
    }

  }
}
