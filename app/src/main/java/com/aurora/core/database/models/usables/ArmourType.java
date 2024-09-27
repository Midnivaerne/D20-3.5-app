package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.ARMOUR_TYPE_IS_SHIELD_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.ARMOUR_TYPE;

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
@Entity(tableName = ARMOUR_TYPE, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE)
)
public class ArmourType extends Item {

  @ColumnInfo(name = ARMOUR_TYPE_IS_SHIELD_COLUMN_NAME)
  private Boolean isShield;

  public ArmourType() {
    super();
  }

  public ArmourType(String name,
      String source,
      String idAsNameBackup) {
    new ArmourType(name, source, idAsNameBackup, null);
  }

  public ArmourType(String name,
      String source,
      String idAsNameBackup,
      Boolean isShield) {
    super(name, source, idAsNameBackup);
    this.isShield = isShield;
  }

  public ArmourType(ArmourType source) {
    new ArmourType(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getIsShield());
  }

}
