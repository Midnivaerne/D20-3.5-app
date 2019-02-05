package com.aurora.d20_35_app.models.settingSpecific;

import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.Databases;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Feats", inheritSuperIndices = true,
        indices = {@Index(value = {"Source"})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source",onDelete = ForeignKey.CASCADE))
public class Feats extends Item {

    @Ignore
    public Feats() {
        super();
    }

    public Feats(String name,
                 String source,
                 String idAsNameBackup) {
        super(name, source, idAsNameBackup);
    }

    public Feats clone() {
        return new Feats(
                getName(),
                getSource(),
                getIdAsNameBackup());
    }
}
