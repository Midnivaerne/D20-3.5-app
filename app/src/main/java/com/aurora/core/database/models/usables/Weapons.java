package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.WEAPON_PRICE_ID_COLUMN_NAME;
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
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = WEAPONS, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME}), @Index(value = {WEAPON_TYPE_PARENT_ID_COLUMN_NAME}),
        @Index(value = {WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME}), @Index(value = {WEAPON_PRICE_ID_COLUMN_NAME})},
    foreignKeys = {
        @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = WeaponType.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = WEAPON_TYPE_PARENT_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = WeaponSubtype.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = WEAPON_SUBTYPE_PARENT_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Price.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = WEAPON_PRICE_ID_COLUMN_NAME, onDelete = ForeignKey.SET_NULL)}
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

  @ColumnInfo(name = WEAPON_PRICE_ID_COLUMN_NAME)
  private Integer priceId;

  @Ignore
  private Price price;

  public Weapons() {
    super();
  }

  public Weapons(String name,
      String source,
      String idAsNameBackup) {
    new Weapons(name, source, idAsNameBackup, null, null, null);
  }

  public Weapons(String name,
      String source,
      String idAsNameBackup,
      Integer weaponTypeId,
      Integer weaponSubtypeId,
      Integer priceId) {
    super(name, source, idAsNameBackup);
    this.setWeaponTypeId(weaponTypeId);
    this.setWeaponSubtypeId(weaponSubtypeId);
    this.setPriceId(priceId);
  }

  public Weapons(Weapons source) {
    new Weapons(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getWeaponTypeId(),
        source.getWeaponSubtypeId(),
        source.getPriceId());
  }

  public Weapons generateAll(DatabaseHolder databaseHolder) {
    setWeaponType(databaseHolder.weaponTypeMap.get(getWeaponTypeId()));
    setWeaponSubtype(databaseHolder.weaponSubtypeMap.get(getWeaponSubtypeId()));
    getWeaponSubtype().generateAll(databaseHolder, getWeaponType().getCanHaveAmmo());
    setPrice(databaseHolder.priceMap.get(getPriceId()));
    return this;
  }
}
