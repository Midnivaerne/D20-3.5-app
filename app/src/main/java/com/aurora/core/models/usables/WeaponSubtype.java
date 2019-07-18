package com.aurora.core.models.usables;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.WEAPON_SUBTYPE_USED_AMMO_TYPE_ID_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.WEAPON_SUBTYPE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = WEAPON_SUBTYPE, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class WeaponSubtype extends Item {

  @ColumnInfo(name = WEAPON_SUBTYPE_USED_AMMO_TYPE_ID_COLUMN_NAME)
  private Integer usedAmmoTypeId;

  @Ignore
  private WeaponSubtype usedAmmoType;

  @Ignore
  public WeaponSubtype() {
    super();
  }

  @Ignore
  public WeaponSubtype(String name,
      String source,
      String idAsNameBackup) {
    new WeaponSubtype(name, source, idAsNameBackup, null);
  }

  public WeaponSubtype(String name,
      String source,
      String idAsNameBackup,
      Integer usedAmmoTypeId) {
    super(name, source, idAsNameBackup);
    this.setUsedAmmoTypeId(usedAmmoTypeId);
  }

  @Ignore
  public WeaponSubtype(WeaponSubtype source) {
    new WeaponSubtype(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getUsedAmmoTypeId());
  }

  public WeaponSubtype generateAll(DatabaseHolder databaseHolder, boolean canHaveAmmo) {
    if (canHaveAmmo) {
      setUsedAmmoType(databaseHolder.weaponSubtypeMap.get(getUsedAmmoTypeId()));
    }
    return this;
  }
}
