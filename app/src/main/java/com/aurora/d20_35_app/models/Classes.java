package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Classes", inheritSuperIndices = true)
public class Classes extends Item {

    @Ignore
    public Classes() {
        super();
    }

    public Classes(String name, String source) {
        super(name, source);
    }

    public Classes clone() {
        return new Classes(getName(), getSource());
    }
}
