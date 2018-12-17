package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Weapons", inheritSuperIndices = true)
public class Weapons extends Item {

    @Ignore
    public Weapons() {
        super();
    }

    public Weapons(String name, String source) {
        super(name, source);
    }

    public Weapons clone() {
        return new Weapons(getName(), getSource());
    }
}
