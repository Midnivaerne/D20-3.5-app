package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_PLAYER;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.models.Databases;
import com.aurora.player.playercharacterutils.PlayerCharacterDescriptionsEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_PLAYER, inheritSuperIndices = true,
    indices = {@Index(value = SOURCE_COLUMN_NAME)},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class HeroPlayer extends Hero {

  @Ignore
  @Embedded
  private HeroDescription heroDescription;

  @Ignore
  private Map<PlayerCharacterDescriptionsEnum, String> textDescriptions = new HashMap<>();

  @Ignore
  public HeroPlayer() {
    super();
    this.heroDescription = new HeroDescription(this.getBackupNames());
  }

  public HeroPlayer(String name,
      String source,
      String idAsNameBackup) {
    new HeroPlayer(name, source, idAsNameBackup, null, null, null);
  }

  public HeroPlayer(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues,
      HeroSkills heroSkills,
      HeroDescription heroDescription) {
    super(name, source, idAsNameBackup, heroValues, heroSkills);
    this.heroDescription = heroDescription == null ? new HeroDescription(this.getBackupNames()) : new HeroDescription(heroDescription);
  }

  @Override
  public void generateAll(DatabaseHolder databaseHolder) {
    super.generateAll(databaseHolder);
    populateTextDescriptions();
  }

  private void populateTextDescriptions() {
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_NAME, getName());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_PLAYER, getHeroDescription().getHeroPlayer());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_CLASS_AND_LEVEL, getHeroValues().getClassListFromMap());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_RACE, getHeroValues().getRaceAndTemplateNamesFromObjects());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_ALIGNMENT, getHeroValues().getAlignmentString());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_DEITY, getHeroValues().getDeityString());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_SIZE, getHeroValues().getSizeString());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_AGE, String.valueOf(getHeroDescription().getHeroAge()));
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_GENDER, getHeroValues().getHeroGender());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_HEIGHT, getHeroDescription().getHeroHeight());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_WEIGHT, getHeroDescription().getHeroWeight());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_EYES, getHeroDescription().getHeroEyes());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_HAIR, getHeroDescription().getHeroHair());
    textDescriptions.put(PlayerCharacterDescriptionsEnum.HERO_SKIN, getHeroDescription().getHeroSkin());
  }

  @Ignore
  public HeroPlayer(HeroPlayer source) {
    new HeroPlayer(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroValues(),
        source.getHeroSkills(),
        source.getHeroDescription());
  }
}
