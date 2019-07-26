package com.aurora.core.database.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_NPC;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.userdata.Hero;
import com.aurora.core.database.models.userdata.HeroSkills;
import com.aurora.core.database.models.userdata.HeroValues;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_NPC, inheritSuperIndices = true,
    indices = {@Index(value = SOURCE_COLUMN_NAME)},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class HeroNpc extends Hero {

  public HeroNpc() {
    super();
  }

  public HeroNpc(String name,
      String source,
      String idAsNameBackup) {
    new HeroNpc(name, source, idAsNameBackup, null, null, null, null, null);
  }

  public HeroNpc(String name,
      String source,
      String idAsNameBackup,
      Integer rightHandHeldItemId,
      Integer leftHandHeldItemId,
      Integer wornItemId) {
    new HeroNpc(name, source, idAsNameBackup, null, null, rightHandHeldItemId, leftHandHeldItemId, wornItemId);
  }

  public HeroNpc(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues,
      HeroSkills heroSkills,
      Integer rightHandHeldItemId,
      Integer leftHandHeldItemId,
      Integer wornItemId) {
    super(name, source, idAsNameBackup, heroValues, heroSkills, rightHandHeldItemId, leftHandHeldItemId, wornItemId);
  }

  @Ignore
  public HeroNpc(HeroNpc source) {
    new HeroNpc(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroValues(),
        source.getHeroSkills(),
        source.getRightHandHeldItemId(),
        source.getLeftHandHeldItemId(),
        source.getWornItemId());
  }
}
