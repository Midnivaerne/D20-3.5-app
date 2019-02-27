package com.aurora.d20_35_app.models.usables;

import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.EQUIPMENT;

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
@Entity(tableName = EQUIPMENT, inheritSuperIndices = true,
        indices = {@Index(value = {SOURCE_COLUMN_NAME})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Equipment extends Item {

    @Ignore
    public Equipment() {
        super();
    }

    public Equipment(String name,
                     String source,
                     String idAsNameBackup) {
        super(name, source, idAsNameBackup);
    }

    public Equipment clone() {
        return new Equipment(
                getName(),
                getSource(),
                getIdAsNameBackup());
    }
}
