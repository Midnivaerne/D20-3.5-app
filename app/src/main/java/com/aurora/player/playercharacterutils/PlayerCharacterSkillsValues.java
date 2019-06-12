package com.aurora.player.playercharacterutils;

import com.aurora.core.R;

public enum PlayerCharacterSkillsValues {
  PROFICIENCY {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_skills_skill_proficiency;
    }
  },
  NAME {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_skills_skill_name;
    }
  },
  EXCLUSIVITY {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_skills_skill_can_untrained;
    }
  },
  ATTRIBUTE_TYPE {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_skills_skill_attribute;
    }
  },
  ARMOUR_PENALTY {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_skills_skill_armour_penalty;
    }
  },
  VALUE {
    @Override
    public int getFieldId() {
      return R.id.fragment_player_character_skills_skill_value;
    }
  },
  ATTRIBUTE_MODIFIER {
    @Override
    public int getFieldId() {
      return 0;
    }
  },
  RANK {
    @Override
    public int getFieldId() {
      return 0;
    }
  },
  OTHER {
    @Override
    public int getFieldId() {
      return 0;
    }
  },
  RACIAL {
    @Override
    public int getFieldId() {
      return 0;
    }
  },
  CLASS {
    @Override
    public int getFieldId() {
      return 0;
    }
  },
  OTHER_RANKS {
    @Override
    public int getFieldId() {
      return 0;
    }
  },
  TEMPORARY {
    @Override
    public int getFieldId() {
      return 0;
    }
  };

  public abstract int getFieldId();
}
