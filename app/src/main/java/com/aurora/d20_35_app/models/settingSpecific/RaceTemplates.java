package com.aurora.d20_35_app.models.settingSpecific;

import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.RACE_TEMPLATES;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.helpers.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = RACE_TEMPLATES, inheritSuperIndices = true,
        indices = {@Index(value = {SOURCE_COLUMN_NAME})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME,onDelete = ForeignKey.CASCADE))
public class RaceTemplates extends Item {

    @Ignore
    public RaceTemplates() {
        super();
    }

    public RaceTemplates(String name,
                         String source,
                         String idAsNameBackup) {
        super(name, source, idAsNameBackup);
    }

    public RaceTemplates clone() {
        return new RaceTemplates(
                getName(),
                getSource(),
                getIdAsNameBackup());
    }
}
