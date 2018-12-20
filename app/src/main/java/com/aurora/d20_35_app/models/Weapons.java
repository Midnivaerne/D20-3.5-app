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
@Entity(tableName = "Weapons", inheritSuperIndices = true,
        indices = {@Index(value = {"Source"})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source",onDelete = ForeignKey.CASCADE))
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
