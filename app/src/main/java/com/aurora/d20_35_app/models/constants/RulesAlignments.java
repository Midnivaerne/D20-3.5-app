package com.aurora.d20_35_app.models.constants;

import com.aurora.d20_35_app.models.helpers.Rules;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "RulesAlignments", inheritSuperIndices = true)
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
