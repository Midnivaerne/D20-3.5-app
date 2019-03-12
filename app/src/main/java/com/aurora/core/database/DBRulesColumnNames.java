package com.aurora.core.database;

import com.aurora.core.models.helpers.Rules;
import lombok.Getter;

public enum DBRulesColumnNames implements DBColumnNamesMethods<DBRulesColumnNames, Rules> {

  /**
   * Core rules data
   */
  //////////////////////////////////////////////////////////////
  //////////////////////////  RULES  //////////////////////////
  COL_RULES_ID(DBColumnNames.ITEM_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Rules item, String data) {
      item.setItemID(Integer.parseInt(data));
    }
  },
  COL_RULES_NAME(DBColumnNames.ITEM_NAME_COLUMN_NAME, false) {
    @Override
    public void setParameter(Rules item, String data) {
      item.setName(data);
    }

  };

  @Getter
  private String columnName;

  @Getter
  private Boolean columnIsUsed;

  @Override
  public void setColumnIsUsed(Boolean columnIsUsed) {
    this.columnIsUsed = columnIsUsed;
  }

  DBRulesColumnNames(String columnName, boolean colBool) {
    this.columnName = columnName;
    this.columnIsUsed = colBool;
  }

  @Override
  public String toString() {
    return this.columnName;
  }

}
