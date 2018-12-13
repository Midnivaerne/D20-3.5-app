package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Equipment", inheritSuperIndices = true)
public class Equipment extends Item {

    @Ignore
    public Equipment() {
        super();
    }

    public Equipment(String name, String source) {
        super(name, source);
    }

    public Equipment clone() {
        return new Equipment(getName(), getSource());
    }
}
