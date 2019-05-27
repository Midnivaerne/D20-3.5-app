package com.aurora.player.playerCharacterUtils;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.R;

public enum PlayerCharacterCombatEnum {
  HERO_COMBAT_HIT_POINTS_NAME_DESCRIPTION("hero_combat_hp_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_hit_points;
    }

    @Override
    int getValueId() {
      return R.id.fragment_player_character_statistics_combat_hit_points_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_player_character_statistics_combat_hit_points_description;
    }
  },
  HERO_COMBAT_DAMAGE_REDUCTION_NAME_DESCRIPTION("hero_combat_damage_reduction_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_damage_reduction;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  HERO_COMBAT_ARMOUR_CLASS_NAME_DESCRIPTION("hero_combat_armour_class_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_armour_class;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  HERO_COMBAT_ARMOUR_CLASS_TOUCH_NAME_DESCRIPTION("hero_combat_armour_class_touch_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_armour_class_touch;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  HERO_COMBAT_ARMOUR_CLASS_FLATFOOTED_NAME_DESCRIPTION("hero_combat_armour_class_flatfooted_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_armour_class_flatfooted;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  HERO_COMBAT_SPEED_NAME_DESCRIPTION("hero_combat_speed_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_speed;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_box_description;
    }
  },
  HERO_COMBAT_INITIATIVE_NAME_DESCRIPTION("hero_combat_initiative_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_initiative;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_ATTACK_NAME_DESCRIPTION("hero_combat_attack_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_attack;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_ATTACK_MELEE_NAME_DESCRIPTION("hero_combat_attack_melee_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_attack_melee;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_ATTACK_RANGED_NAME_DESCRIPTION("hero_combat_attack_ranged_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_attack_ranged;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_GRAPPLE_NAME_DESCRIPTION("hero_combat_grapple_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_grapple;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_rollable_box_value;
    }

    @Override
    int getDescriptionId() {
      return R.id.fragment_number_value_with_description_rollable_box_description;
    }
  },
  HERO_COMBAT_SPELL_RESISTANCE_NAME_DESCRIPTION("hero_combat_spell_resistance_name_description") {
    @Override
    int getFieldId() {
      return R.id.fragment_player_character_statistics_combat_spell_resistance;
    }

    @Override
    int getValueId() {
      return R.id.fragment_number_value_with_description_box_value;
    }

    @Override
    int getDescriptionId() {
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

  abstract int getFieldId();

  abstract int getValueId();

  abstract int getDescriptionId();

}
