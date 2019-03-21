package com.aurora.core.models.helpers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.aurora.core.database.DBColumnNames;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.constants.RulesCombat;
import com.aurora.core.models.constants.RulesSkills;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.typeHelpers.RulesType;

@EqualsAndHashCode(callSuper = false)
@Data
public class Rules implements CoreHelper {

  @Getter
  @Setter
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = DBColumnNames.RULE_ID_COLUMN_NAME)
  private int itemID;

  @Getter
  @Setter
  @ColumnInfo(name = DBColumnNames.RULE_NAME_COLUMN_NAME)
  private String name;

  @Getter
  @Setter
  private String content;

  @Ignore
  public Rules() {
  }

  public Rules(String name) {
    this.name = name;
  }

  public Rules createRulesGroup(RulesType rulesType) {
    switch (rulesType) {
      case ALIGNMENTS:
        return new Alignments();
      case SIZES:
        return new Sizes();
      case RULES_COMBAT:
        return new RulesCombat();
      case RULES_SKILLS:
        return new RulesSkills();
    }
    throw new IllegalArgumentException("ItemType" + rulesType + " is not recognized.");
  }
}
