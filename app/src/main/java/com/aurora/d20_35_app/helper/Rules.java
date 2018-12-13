package com.aurora.d20_35_app.helper;

import com.aurora.d20_35_app.enums.RulesType;
import com.aurora.d20_35_app.models.RulesCombat;
import com.aurora.d20_35_app.models.RulesSkills;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Rules {

    @Getter
    @Setter
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Item_ID")
    private int itemID;

    @Getter
    @Setter
    @ColumnInfo(name = "Name")
    private String name;

    @Getter
    @Setter
    private String content;

    public Rules(String name) {
        this.name = name;
    }

    public Rules createRulesGroup(RulesType rulesType) {
        switch (rulesType) {
            case RulesCombat:
                return new RulesCombat();
            case RulesSkills:
                return new RulesSkills();
        }
        throw new IllegalArgumentException("ItemType" + rulesType + " is not recognized.");
    }
}
