package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.helpers.Rules;

import lombok.Getter;

public enum DBRulesColumnNames implements DBColumnNamesMethods<DBRulesColumnNames, Rules> {

    /**
     * Core rules data
     */
    //////////////////////////////////////////////////////////////
    //////////////////////////  RULES  //////////////////////////
    COL_RULES_ID(Item.ITEM_ID_COLUMN_NAME, false) {
        @Override
        public void setParameter(Rules item, String data) {
            item.setItemID(Integer.parseInt(data));
        }
    },
    COL_RULES_NAME(Item.ITEM_NAME_COLUMN_NAME, false) {
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
