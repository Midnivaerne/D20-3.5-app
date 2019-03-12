package com.aurora.core.models.constants;

import static com.aurora.core.database.DBTableNames.RULES_ALIGNMENTS;

import androidx.room.Entity;
import androidx.room.Ignore;
import com.aurora.core.models.helpers.Rules;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = RULES_ALIGNMENTS, inheritSuperIndices = true)
public class RulesAlignments extends Rules {

  @Ignore
  public RulesAlignments() {
    super();
  }

  public RulesAlignments(String name) {
    super(name);
  }

  public RulesAlignments clone() {
    return new RulesAlignments(getName());
  }
}
