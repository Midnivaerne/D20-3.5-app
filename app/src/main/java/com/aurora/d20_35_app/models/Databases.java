package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Databases", inheritSuperIndices = true)
public class Databases extends Item {

    @Ignore
    public Databases() {
        super();
    }

    public Databases(String name, String source) {
        super(name, source);
    }

    public Databases clone() {
        return new Databases(getName(), getSource());
    }
}
