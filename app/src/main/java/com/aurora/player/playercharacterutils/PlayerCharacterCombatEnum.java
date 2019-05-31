package com.aurora.player.playercharacterutils;

import com.aurora.core.R;

public enum PlayerCharacterCombatEnum implements PlayerCharacterEnumBase {
  HERO_COMBAT_HIT_POINTS("hero_combat_hp_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_hit_points;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_player_character_statistics_combat_hit_points_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_player_character_statistics_combat_hit_points_description;
    }
  },
  HERO_COMBAT_DAMAGE_REDUCTION("hero_combat_damage_reduction_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_damage_reduction;
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
  HERO_COMBAT_ARMOUR_CLASS("hero_combat_armour_class_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_armour_class;
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
  HERO_COMBAT_ARMOUR_CLASS_TOUCH("hero_combat_armour_class_touch_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_armour_class_touch;
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
  HERO_COMBAT_ARMOUR_CLASS_FLATFOOTED("hero_combat_armour_class_flatfooted_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_armour_class_flatfooted;
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
  HERO_COMBAT_SPEED("hero_combat_speed_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_speed;
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
  HERO_COMBAT_INITIATIVE("hero_combat_initiative_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_initiative;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_ATTACK("hero_combat_attack_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_attack;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_ATTACK_MELEE("hero_combat_attack_melee_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_attack_melee;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_ATTACK_RANGED("hero_combat_attack_ranged_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_attack_ranged;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_GRAPPLE("hero_combat_grapple_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_grapple;
    }

    @Override
    public int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_SPELL_RESISTANCE("hero_combat_spell_resistance_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_spell_resistance;
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

  PlayerCharacterCombatEnum(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }

  public abstract int getValueId();

  public abstract int getDescriptionId();

}
