package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.EQUIPMENT_IS_CONTAINER_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.EQUIPMENT;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = EQUIPMENT, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Equipment extends Item {

  @ColumnInfo(name = EQUIPMENT_IS_CONTAINER_COLUMN_NAME)
  private String isContainer;

  @Ignore
  public Equipment() {
    super();
  }

  @Ignore
  public Equipment(String name,
      String source,
      String idAsNameBackup) {
    new Equipment(name, source, idAsNameBackup, null);
  }

  public Equipment(String name,
      String source,
      String idAsNameBackup,
      String isContainer) {
    super(name, source, idAsNameBackup);
    this.setIsContainer(isContainer);
  }

  @Ignore
  public Equipment(Equipment source) {
    new Equipment(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getIsContainer());
  }
}
