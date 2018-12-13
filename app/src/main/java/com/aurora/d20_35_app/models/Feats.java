package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Feats", inheritSuperIndices = true)
public class Feats extends Item {

    @Ignore
    public Feats() {
        super();
    }

    public Feats(String name, String source) {
        super(name, source);
    }

    public Feats clone() {
        return new Feats(getName(), getSource());
    }
}
