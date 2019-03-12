package com.aurora.core.models.helpers;

public interface DataConverter<T extends Item> {

  default String getDamageReduction(T combatant) {
    int out = 0; //getRacialDamageReduction() + getItemDamageReduction() + getEffectDamageReduction();//todo proper value, multiple DR possible
    return String.valueOf(out);
  }

  default String getArmourClass(T combatant) {
    int out = 10; //todo proper value
    return String.valueOf(out);
  }

  default String getArmourClassTouch(T combatant) {
    int out = 10;//todo proper value
    return String.valueOf(out);
  }

  default String getArmourClassFlatfooted(T combatant) {
    int out = 10;//todo proper value
    return String.valueOf(out);
  }

  default String getSpeed(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getInitiative(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getAttack(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getAttackMelee(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getAttackRanged(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getGrapple(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getSpellResistance(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getFortitude(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getReflex(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  default String getWill(T combatant) {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }
}
