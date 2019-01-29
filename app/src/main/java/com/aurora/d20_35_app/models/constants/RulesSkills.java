package com.aurora.d20_35_app.models.constants;

import com.aurora.d20_35_app.helper.Rules;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "RulesSkills", inheritSuperIndices = true)
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
