package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Spells", inheritSuperIndices = true)
public class Spells extends Item {

    @Ignore
    public Spells() {
        super();
    }

    public Spells(String name, String source) {
        super(name, source);
    }

    public Spells clone() {
        return new Spells(getName(), getSource());
    }
}
