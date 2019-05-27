package com.aurora.core.models.userdata;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Embedded;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.ValuesConverter;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Hero extends Item implements ValuesConverter {

  @Ignore
  @Embedded
  private HeroValues heroValues;

  @Ignore
  public Hero() {
    super();
    this.heroValues = new HeroValues();
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
    this.heroValues = heroValues == null ? new HeroValues() : heroValues.clone();
  }

  public Hero clone() {
    return new Hero(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        this.getHeroValues());
  }

  public String getDamageReduction() {
    StringBuilder out = new StringBuilder();
    //getHeroValues().getRace().getSpecialQualities().iterator()
    //    .forEachRemaining((SpecQ) -> (SpecQ.getName().equals(SpecialQualities.DamageReduction)) ? out.append("") : out.append(SpecQ));
    //getHeroValues().getRaceTemplate() == null ? "" : getHeroValues().getRaceTemplate().getSpecialQualities().getDamageReduction());
    // + getItemDamageReduction()
    // + getEffectDamageReduction();//todo proper value, multiple DR possible
    return String.valueOf(out);
  }

  public String getArmourClass() {
    int out = 10; //todo proper value
    return String.valueOf(out);
  }

  public String getArmourClassTouch() {
    int out = 10;//todo proper value
    return String.valueOf(out);
  }

  public String getArmourClassFlatfooted() {
    int out = 10;//todo proper value
    return String.valueOf(out);
  }

  public String getSpeed() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getInitiative() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getAttack() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getAttackMelee() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getAttackRanged() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getGrapple() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getSpellResistance() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getFortitude() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getReflex() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getWill() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }
}
