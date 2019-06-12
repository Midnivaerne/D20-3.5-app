package com.aurora.core.models.userdata;

import static com.aurora.core.models.userdata.HeroValues.getStatisticModifier;

import androidx.room.Embedded;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.models.helpers.Item;
import com.aurora.player.playercharacterutils.PlayerCharacterAbilityScoresEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Hero extends Item {

  @Ignore
  @Embedded
  private HeroValues heroValues;

  @Ignore
  @Embedded
  private HeroSkills heroSkills;

  @Ignore
  public Hero() {
    super();
    this.heroValues = new HeroValues(this.getBackupNames());
    this.heroSkills = new HeroSkills(this.getBackupNames());
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
    this.heroValues = heroValues == null ? new HeroValues(this.getBackupNames()) : heroValues.clone();
    this.heroSkills = heroSkills == null ? new HeroSkills(this.getBackupNames()) : heroSkills.clone();
  }

  public Hero clone() {
    return new Hero(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        getHeroValues(),
        getHeroSkills());
  }

  public void generateAll(DatabaseHolder databaseHolder) {
    getHeroValues().generateAll(databaseHolder);
    getHeroSkills().generateSkillListAsSkillAndRank(databaseHolder);
    for (PlayerCharacterAbilityScoresEnum attr : getHeroValues().getAbilityScoreValues().keySet()) {
      getHeroSkills().getAttributeModifiers().put(attr, getStatisticModifier(getHeroValues().getAbilityScoreValues().get(attr)));
    }
    getHeroSkills().loadAllSettingSkills(databaseHolder,getHeroValues().getRace().getRaceSkills());
  }
}
