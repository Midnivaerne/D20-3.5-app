package com.aurora.core.models.constants;

import static com.aurora.core.database.DbTableNames.RULES_SKILLS;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.aurora.core.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = RULES_SKILLS, inheritSuperIndices = true)
public class RulesSkills extends Rules {

  @Ignore
  public RulesSkills() {
    super();
  }

  public RulesSkills(String name) {
    super(name);
  }

  public RulesSkills clone() {
    return new RulesSkills(getName());
  }
}
