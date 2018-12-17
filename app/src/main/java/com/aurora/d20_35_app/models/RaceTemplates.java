package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "RaceTemplates", inheritSuperIndices = true)
public class RaceTemplates extends Item {

    @Ignore
    public RaceTemplates() {
        super();
    }

    public RaceTemplates(String name, String source) {
        super(name, source);
    }

    public RaceTemplates clone() {
        return new RaceTemplates(getName(), getSource());
    }
}
