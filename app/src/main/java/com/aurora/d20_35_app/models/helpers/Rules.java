package com.aurora.d20_35_app.models.helpers;

import com.aurora.d20_35_app.enums.RulesType;
import com.aurora.d20_35_app.models.constants.RulesAlignments;
import com.aurora.d20_35_app.models.constants.RulesCombat;
import com.aurora.d20_35_app.models.constants.RulesSizes;
import com.aurora.d20_35_app.models.constants.RulesSkills;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Data
public class Rules {

    @Ignore
    public static final String RULE_ID_COLUMN_NAME = "Item_ID";
    @Ignore
    public static final String RULE_NAME_COLUMN_NAME = "Name";

    @Getter
    @Setter
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RULE_ID_COLUMN_NAME)
    private int itemID;

    @Getter
    @Setter
    @ColumnInfo(name = RULE_NAME_COLUMN_NAME)
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
            case RULES_ALIGNMENTS:
                return new RulesAlignments();
            case RULES_SIZES:
                return new RulesSizes();
            case RULES_COMBAT:
                return new RulesCombat();
            case RULES_SKILLS:
                return new RulesSkills();
        }
        throw new IllegalArgumentException("ItemType" + rulesType + " is not recognized.");
    }
}
