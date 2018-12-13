package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Rules;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "RulesCombat", inheritSuperIndices = true)
public class RulesCombat extends Rules {

    @Ignore
    public RulesCombat() {
        super();
    }

    public RulesCombat(String name) {
        super(name);
    }

    public RulesCombat clone() {
        return new RulesCombat(getName());
    }
}
