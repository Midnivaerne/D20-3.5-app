package com.aurora.d20_35_app.models.constants;

import com.aurora.d20_35_app.models.helpers.Rules;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.aurora.d20_35_app.database.DBTableNames.RULES_SIZES;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = RULES_SIZES, inheritSuperIndices = true)
public class RulesSizes extends Rules {

    @Ignore
    public RulesSizes() {
        super();
    }

    public RulesSizes(String name) {
        super(name);
    }

    public RulesSizes clone() {
        return new RulesSizes(getName());
    }
}
