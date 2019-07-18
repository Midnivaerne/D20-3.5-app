package com.aurora.core.models.usables;

import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.WEAPON_TYPE_PARENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.WEAPONS;

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
@Entity(tableName = WEAPONS, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME}), @Index(value = {WEAPON_TYPE_PARENT_ID_COLUMN_NAME}),
        @Index(value = {WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME})},
    foreignKeys = {
        @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = WeaponType.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = WEAPON_TYPE_PARENT_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = WeaponSubtype.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class Weapons extends Item {

  @ColumnInfo(name = WEAPON_TYPE_PARENT_ID_COLUMN_NAME)
  private Integer weaponTypeId;

  @Ignore
  private WeaponType weaponType;

  @ColumnInfo(name = WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME)
  private Integer weaponSubtypeId;

  @Ignore
  private WeaponSubtype weaponSubtype;

  @Ignore
  public Weapons() {
    super();
  }

  public Weapons(String name,
      String source,
      String idAsNameBackup) {
    super(name, source, idAsNameBackup);
  }

  @Ignore
  public Weapons(Weapons source) {
    new Weapons(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup());
  }

  public Weapons generateAll(DatabaseHolder databaseHolder) {
    setWeaponType(databaseHolder.weaponTypeMap.get(getWeaponTypeId()));
    setWeaponSubtype(databaseHolder.weaponSubtypeMap.get(getWeaponSubtypeId()));
    getWeaponSubtype().generateAll(databaseHolder, getWeaponType().getCanHaveAmmo());
    return this;
  }
}
