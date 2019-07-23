package com.aurora.core.database.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.DEITIES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = DEITIES, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME,
        childColumns = SOURCE_COLUMN_NAME,
        onDelete = ForeignKey.CASCADE))
public class Deities extends Item {

  @Ignore
  public Deities() {
    super();
  }

  public Deities(String name,
      String source,
      String idAsNameBackup) {
    super(name, source, idAsNameBackup);
  }

  @Ignore
  public Deities(Deities source) {
    new Deities(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup());
  }
}
