package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Monsters", inheritSuperIndices = true)
public class Monsters extends Item {

    @Ignore
    public Monsters() {
        super();
    }

    public Monsters(String name, String source) {
        super(name, source);
    }

    public Monsters clone() {
        return new Monsters(getName(), getSource());
    }
}
