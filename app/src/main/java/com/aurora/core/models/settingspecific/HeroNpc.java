package com.aurora.core.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_NPC;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.models.Databases;
import com.aurora.core.models.userdata.Hero;
import com.aurora.core.models.userdata.HeroValues;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_NPC, inheritSuperIndices = true,
    indices = {@Index(value = SOURCE_COLUMN_NAME)},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class HeroNpc extends Hero {

  @Ignore
  public HeroNpc() {
    super();
  }

  public HeroNpc(String name,
      String source,
      String idAsNameBackup) {
    new HeroNpc(name, source, idAsNameBackup, null);
  }

  public HeroNpc(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues) {
    super(name, source, idAsNameBackup, heroValues);
  }

  public HeroNpc clone() {
    return new HeroNpc(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        this.getHeroValues());
  }
}
