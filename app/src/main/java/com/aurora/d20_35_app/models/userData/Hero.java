package com.aurora.d20_35_app.models.userData;

import com.aurora.d20_35_app.models.helpers.Item;

import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Item {

    @Ignore
    public Hero() {
        super();
    }

    public Hero(String name,
                String source,
                String idAsNameBackup) {
        super(name, source, idAsNameBackup);

    }

    public Hero clone() {
        return new Hero(
                getName(),
                getSource(),
                getIdAsNameBackup());
    }
}
