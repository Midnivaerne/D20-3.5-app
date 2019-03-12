package com.aurora.core.models;

import static com.aurora.core.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DBTableNames.DATABASES;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import com.aurora.core.models.helpers.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

