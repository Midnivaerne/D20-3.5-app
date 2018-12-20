package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Hero", inheritSuperIndices = true,
        indices = {@Index(value = {"Source"})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source",onDelete = ForeignKey.CASCADE))
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
