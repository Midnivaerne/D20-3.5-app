package com.aurora.core.models.usables;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.EQUIPMENT;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = EQUIPMENT, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
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
