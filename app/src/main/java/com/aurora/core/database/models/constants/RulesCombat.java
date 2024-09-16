package com.aurora.core.database.models.constants;

import static com.aurora.core.database.DbTableNames.RULES_COMBAT;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = RULES_COMBAT, inheritSuperIndices = true)
public class RulesCombat extends Rules {

  public RulesCombat() {
    super();
  }

  public RulesCombat(String name) {
    super(name);
  }

  public RulesCombat(RulesCombat source) {
    new RulesCombat(
        source.getName());
  }
}
