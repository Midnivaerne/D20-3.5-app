package com.aurora.core.database.models.constants;

import static com.aurora.core.database.DbTableNames.RULES_SKILLS;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = RULES_SKILLS, inheritSuperIndices = true)
public class RulesSkills extends Rules {

  public RulesSkills() {
    super();
  }

  public RulesSkills(String name) {
    super(name);
  }

  public RulesSkills(RulesSkills source) {
    new RulesSkills(
        source.getName());
  }
}
