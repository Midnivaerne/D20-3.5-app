package com.aurora.core.models.userdata;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Embedded;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Hero extends Item {

  @Ignore
  @Embedded
  private HeroValues heroValues;

  @Ignore
  public Hero() {
    super();
    this.heroValues = new HeroValues(this.getBackupNames());
  }

  public Hero(String name,
      String source,
      String idAsNameBackup) {
    new Hero(name, source, idAsNameBackup, null);
  }


  public Hero(String name,
      String source,
      String idAsNameBackup,
      HeroValues heroValues) {
    super(name, source, idAsNameBackup);
    this.heroValues = heroValues == null ? new HeroValues(this.getBackupNames()) : heroValues.clone();
  }

  public Hero clone() {
    return new Hero(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        getHeroValues());
  }

  public void generateAll(DatabaseHolder databaseHolder) {
    getHeroValues().generateAll(databaseHolder);
  }
}
