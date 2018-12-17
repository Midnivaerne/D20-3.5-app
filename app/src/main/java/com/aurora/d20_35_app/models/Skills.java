package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Skills", inheritSuperIndices = true)
public class Skills extends Item {

    @Ignore
    public Skills() {
        super();
    }

    public Skills(String name, String source) {
        super(name, source);
    }

    public Skills clone() {
        return new Skills(getName(), getSource());
    }
}
