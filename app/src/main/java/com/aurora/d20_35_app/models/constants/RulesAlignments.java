package com.aurora.d20_35_app.models.constants;

import static com.aurora.d20_35_app.database.DBTableNames.RULES_ALIGNMENTS;

import androidx.room.Entity;
import androidx.room.Ignore;
import com.aurora.d20_35_app.models.helpers.Rules;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = RULES_ALIGNMENTS, inheritSuperIndices = true)
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
