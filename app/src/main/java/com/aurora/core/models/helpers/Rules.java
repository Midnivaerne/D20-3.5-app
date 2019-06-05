package com.aurora.core.models.helpers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.DbColumnNames;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.constants.RulesCombat;
import com.aurora.core.models.constants.RulesSkills;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.typehelpers.RulesType;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class Rules implements CoreHelper {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = DbColumnNames.RULE_ID_COLUMN_NAME)
  private int itemID;

  @ColumnInfo(name = DbColumnNames.RULE_NAME_COLUMN_NAME)
  private String name;

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
      default:
        throw new IllegalArgumentException("ItemType" + rulesType + " is not recognized.");
    }
  }

  @Override
  public String showAllContentAsString() {
    return new StringBuilder().append("Rules [")
        .append(", itemID = ").append(getItemID())
        .append(", name = ").append(getName())
        .append(", content = ").append(getContent())
        .append("]").toString();
  }
}
