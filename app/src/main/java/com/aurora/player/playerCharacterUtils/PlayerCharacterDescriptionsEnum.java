package com.aurora.player.playerCharacterUtils;

import com.aurora.core.R;

public enum PlayerCharacterDescriptionsEnum implements PlayerCharacterEnumBase {

  HERO_NAME("hero_name_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_name;
    }
  },
  HERO_PLAYER("hero_player_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_player;
    }
  },
  HERO_CLASS_AND_LEVEL("hero_class_and_level_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_class_and_level;
    }
  },
  HERO_RACE("hero_race_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_race;
    }
  },
  HERO_ALIGNMENT("hero_alignment_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_alignment;
    }
  },
  HERO_DEITY("hero_deity_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_deity;
    }
  },
  HERO_SIZE("hero_size_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_size;
    }
  },
  HERO_AGE("hero_age_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_age;
    }
  },
  HERO_GENDER("hero_gender_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_gender;
    }
  },
  HERO_HEIGHT("hero_height_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_height;
    }
  },
  HERO_WEIGHT("hero_weight_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_weight;
    }
  },
  HERO_EYES("hero_eyes_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_eyes;
    }
  },
  HERO_HAIR("hero_hair_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_hair;
    }
  },
  HERO_SKIN("hero_skin_description") {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_description_appearance_entry_skin;
    }
  };

  String description;

  PlayerCharacterDescriptionsEnum(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }
}
