package com.aurora.player.playercharacterutils;

import com.aurora.core.R;

public enum PlayerCharacterWornEquipmentPlacesEnum implements PlayerCharacterEnumBase {
  WORN_EYES("equipment_worn_eyes_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_eyes;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_HEAD("equipment_worn_head_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_head;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_NECK("equipment_worn_neck_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_neck;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_SHOULDERS("equipment_worn_shoulders_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_shoulders;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_RING_RIGHT("equipment_worn_ring_right_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_ring_right;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_RING_LEFT("equipment_worn_ring_left_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_ring_left;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_HANDS("equipment_worn_hands_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_hands;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_WRISTS("equipment_worn_wrists_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_wrists;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_TORSO("equipment_worn_torso_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_torso;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_BODY("equipment_worn_body_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_body;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_WAIST("equipment_worn_waist_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_waist;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  },
  WORN_FEET("equipment_worn_feet_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_equipment_worn_feet;
    }

    @Override
    public int getNameId() {
      return R.id.fragment_text_value_with_description_box_text;
    }

    @Override
    public int getDescriptionId() {
      return R.id.fragment_text_value_with_description_box_description;
    }
  };

  String description;

  PlayerCharacterWornEquipmentPlacesEnum(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }

  public abstract int getNameId();

  public abstract int getDescriptionId();

}
