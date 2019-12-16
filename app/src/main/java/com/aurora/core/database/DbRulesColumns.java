package com.aurora.core.database;

import lombok.Getter;
import lombok.Setter;

import com.aurora.core.database.models.helpers.Rules;

public enum DbRulesColumns implements DbColumnNamesMethods<DbRulesColumns, Rules> {

  /**
   * Core rules data.
   */
  //////////////////////////////////////////////////////////////
  //////////////////////////  RULES  //////////////////////////
  COL_RULES_ID(DbColumnNames.ITEM_ID_COLUMN_NAME, false) {
    @Override
    public void setParameter(Rules item, String data) {
      item.setItemID(Integer.parseInt(data));
    }
  },
  COL_RULES_NAME(DbColumnNames.ITEM_NAME_COLUMN_NAME, false) {
    @Override
    public void setParameter(Rules item, String data) {
      item.setName(data);
    }

  };

  @Getter
  private String columnName;

  @Getter
  @Setter
  private Boolean columnIsUsed;

  DbRulesColumns(String columnName, boolean colBool) {
    this.columnName = columnName;
    this.columnIsUsed = colBool;
  }

  @Override
  public String toString() {
    return this.columnName;
  }

}
