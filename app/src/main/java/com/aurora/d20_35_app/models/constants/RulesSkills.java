package com.aurora.d20_35_app.models.constants;

import com.aurora.d20_35_app.models.helpers.Rules;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.aurora.d20_35_app.database.DBTableNames.RULES_SKILLS;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = RULES_SKILLS, inheritSuperIndices = true)
public class RulesSkills extends Rules {

    @Ignore
    public RulesSkills() {
        super();
    }

    public RulesSkills(String name) {
        super(name);
    }

    public RulesSkills clone() {
        return new RulesSkills(getName());
    }
}
