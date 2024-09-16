package com.aurora.player.playercharacterutils;

import com.aurora.core.R;

public enum PlayerCharacterArmourEnum implements PlayerCharacterEnumBase {
  ARMOUR_NAME("equipment_armour_name_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_armour_name;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  ARMOUR_AC("equipment_armour_ac_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_armour_ac;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  ARMOUR_DEX("equipment_armour_dex_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_armour_dex;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  ARMOUR_PROPERTIES("equipment_armour_properties_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_armour_properties;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  };

  String description;

  PlayerCharacterArmourEnum(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }

  public abstract int getValueId();

  public abstract int getDescriptionId();
}
