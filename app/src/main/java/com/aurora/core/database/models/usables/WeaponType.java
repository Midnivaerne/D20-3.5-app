package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.WEAPON_TYPE_CAN_HAVE_AMMO_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.WEAPON_TYPE_IS_AMMO_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.WEAPON_TYPE;

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
@Entity(tableName = WEAPON_TYPE, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE)
)
public class WeaponType extends Item {

  @ColumnInfo(name = WEAPON_TYPE_CAN_HAVE_AMMO_COLUMN_NAME)
  private Boolean canHaveAmmo;

  @ColumnInfo(name = WEAPON_TYPE_IS_AMMO_COLUMN_NAME)
  private Boolean isAmmo;

  public WeaponType() {
    super();
  }

  public WeaponType(String name,
      String source,
      String idAsNameBackup) {
    new WeaponType(name, source, idAsNameBackup, null, null);
  }

  public WeaponType(String name,
      String source,
      String idAsNameBackup,
      Boolean canHaveAmmo,
      Boolean isAmmo) {
    super(name, source, idAsNameBackup);
    this.setCanHaveAmmo(canHaveAmmo);
    this.setIsAmmo(isAmmo);
  }

  public WeaponType(WeaponType source) {
    new WeaponType(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getCanHaveAmmo(),
        source.getIsAmmo());
  }
}
