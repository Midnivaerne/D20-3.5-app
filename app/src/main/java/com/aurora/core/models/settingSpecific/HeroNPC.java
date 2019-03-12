package com.aurora.core.models.settingSpecific;

import static com.aurora.core.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DBTableNames.HERO_NPC;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import com.aurora.core.models.Databases;
import com.aurora.core.models.userData.Hero;
import com.aurora.core.models.userData.HeroValues;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = HERO_NPC, inheritSuperIndices = true,
    indices = {@Index(value = SOURCE_COLUMN_NAME)},
    foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class HeroNPC extends Hero {

  @Ignore
  public HeroNPC() {
    super();
  }

  public HeroNPC(String name,
      String source,
      String idAsNameBackup) {
    new HeroNPC(name, source, idAsNameBackup, null);
  }

  public HeroNPC(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues) {
    super(name, source, idAsNameBackup, heroValues);
  }

  public HeroNPC clone() {
    return new HeroNPC(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        this.getHeroValues());
  }
}
