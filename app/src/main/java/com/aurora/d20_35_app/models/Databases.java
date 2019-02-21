package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.models.helpers.Item;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.DATABASES;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = DATABASES, inheritSuperIndices = true,
        indices = {@Index(value = {SOURCE_COLUMN_NAME}, unique = true)})
public class Databases extends Item {

    @Ignore
    public Databases() {
        super();
    }

    public Databases(String name,
                     String source,
                     String idAsNameBackup) {
        super(name, source, idAsNameBackup);
    }

    public Databases clone() {
        return new Databases(
                getName(),
                getSource(),
                getIdAsNameBackup());
    }
}

