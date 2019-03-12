package com.aurora.core.models.userData;

import androidx.room.Embedded;
import androidx.room.Ignore;
import com.aurora.core.models.helpers.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Item {

  @Ignore
  public Hero() {
    super();
    this.heroStatistics = new HeroStatistics();
  }

  public Hero(String name,
      String source,
      String idAsNameBackup) {
    new Hero(name, source, idAsNameBackup, null);
  }

  public Hero(String name,
      String source,
      String idAsNameBackup,
      HeroStatistics heroStatistics) {
    super(name, source, idAsNameBackup);
    this.heroStatistics = heroStatistics == null ? new HeroStatistics() : heroStatistics.clone();
  }


  @Ignore
  @Getter
  @Setter
  @Embedded
  private HeroStatistics heroStatistics;

  public Hero clone() {
    return new Hero(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        getHeroStatistics());
  }
}
