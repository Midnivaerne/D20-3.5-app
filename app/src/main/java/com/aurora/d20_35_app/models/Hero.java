package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Hero", inheritSuperIndices = true)
public class Hero extends Item {

    @Ignore
    public Hero() {
        super();
    }

    public Hero(String name, String source) {
        super(name, source);
    }

    public Hero clone() {
        return new Hero(getName(), getSource());
    }
}
