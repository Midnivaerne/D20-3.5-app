package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.ARMOUR_PRICE_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_SUBTYPE_PARENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_TYPE_PARENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.ARMOUR;

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
@Entity(tableName = ARMOUR, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME}), @Index(value = {ARMOUR_PRICE_ID_COLUMN_NAME})},
    foreignKeys = {
        @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Price.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = ARMOUR_PRICE_ID_COLUMN_NAME, onDelete = ForeignKey.SET_NULL)})
public class Armour extends Item {

  @ColumnInfo(name = ARMOUR_TYPE_PARENT_ID_COLUMN_NAME)
  private Integer armourTypeId;

  @Ignore
  private ArmourType armourType;

  @ColumnInfo(name = ARMOUR_SUBTYPE_PARENT_ID_COLUMN_NAME)
  private Integer armourSubtypeId;

  @Ignore
  private ArmourSubtype armourSubtype;

  @ColumnInfo(name = ARMOUR_PRICE_ID_COLUMN_NAME)
  private Integer priceId;

  @Ignore
  private Price price;


  @Ignore
  public Armour() {
    super();
  }

  @Ignore
  public Armour(String name,
      String source,
      String idAsNameBackup,
      Integer armourTypeId) {
    new Armour(name, source, idAsNameBackup, armourTypeId, null, null);
  }

  public Armour(String name,
      String source,
      String idAsNameBackup,
      Integer armourTypeId,
      Integer armourSubtypeId,
      Integer priceId) {
    super(name, source, idAsNameBackup);
    this.armourTypeId = armourTypeId;
    this.armourSubtypeId = armourSubtypeId;
    this.priceId = priceId;
  }

  @Ignore
  public Armour(Armour source) {
    new Armour(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getArmourTypeId(),
        source.getArmourSubtypeId(),
        source.getPriceId());
  }

  public Armour generateAll(DatabaseHolder databaseHolder) {
    setArmourType(databaseHolder.armourTypeMap.get(getArmourTypeId()));
    setArmourSubtype(databaseHolder.armourSubtypeMap.get(getArmourSubtypeId()));
    getArmourSubtype().generateAll(databaseHolder);
    setPrice(databaseHolder.priceMap.get(getPriceId()));
    return this;
  }
}
