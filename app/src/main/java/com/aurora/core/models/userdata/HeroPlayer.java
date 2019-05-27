package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_PLAYER;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.models.Databases;

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
  public HeroPlayer() {
    super();
    this.heroDescription = new HeroDescription();
  }

  public HeroPlayer(String name,
      String source,
      String idAsNameBackup) {
    new HeroPlayer(name, source, idAsNameBackup, null, null);
  }

  public HeroPlayer(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues,
      HeroDescription heroDescription) {
    super(name, source, idAsNameBackup, heroValues);
    this.heroDescription = heroDescription == null ? new HeroDescription() : heroDescription.clone();
  }

  public HeroPlayer clone() {
    return new HeroPlayer(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        this.getHeroValues(),
        getHeroDescription());
  }
}
