package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.ARMOUR_ARCANE_FAILURE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_SUBTYPE_MAX_DEXTERITY_BONUS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_MAX_SPEED_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_PENALTY_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_SUBTYPE_VALUE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ARMOUR_WEIGHT_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.ARMOUR_SUBTYPE;

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
@Entity(tableName = ARMOUR_SUBTYPE, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class ArmourSubtype extends Item {

  @ColumnInfo(name = ARMOUR_SUBTYPE_VALUE_COLUMN_NAME)
  private Integer armourValue;

  @ColumnInfo(name = ARMOUR_SUBTYPE_MAX_DEXTERITY_BONUS_COLUMN_NAME)
  private Integer armourMaxDexterityBonus;

  @ColumnInfo(name = ARMOUR_PENALTY_COLUMN_NAME)
  private Integer armourPenalty;

  @ColumnInfo(name = ARMOUR_ARCANE_FAILURE_COLUMN_NAME)
  private Integer armourArcaneFailure;

  @ColumnInfo(name = ARMOUR_MAX_SPEED_COLUMN_NAME)
  private Integer armourMaxSpeed;

  @ColumnInfo(name = ARMOUR_WEIGHT_COLUMN_NAME)
  private Integer armourWeight;

  @ColumnInfo(name = ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME)
  private String armourSpecialProperties;

  @Ignore
  public ArmourSubtype() {
    super();
  }

  @Ignore
  public ArmourSubtype(String name,
      String source,
      String idAsNameBackup) {
    new ArmourSubtype(name, source, idAsNameBackup, null, null, null, null, null, null, null);
  }

  public ArmourSubtype(String name,
      String source,
      String idAsNameBackup,
      Integer armourValue,
      Integer armourMaxDexterityBonus,
      Integer armourPenalty,
      Integer armourArcaneFailure,
      Integer armourMaxSpeed,
      Integer armourWeight,
      String armourSpecialProperties) {
    super(name, source, idAsNameBackup);
    this.setArmourValue(armourValue);
    this.setArmourMaxDexterityBonus(armourMaxDexterityBonus);
    this.setArmourPenalty(armourPenalty);
    this.setArmourArcaneFailure(armourArcaneFailure);
    this.setArmourMaxSpeed(armourMaxSpeed);
    this.setArmourWeight(armourWeight);
    this.setArmourSpecialProperties(armourSpecialProperties);
  }

  @Ignore
  public ArmourSubtype(ArmourSubtype source) {
    new ArmourSubtype(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getArmourValue(),
        source.getArmourMaxDexterityBonus(),
        source.getArmourPenalty(),
        source.getArmourArcaneFailure(),
        source.getArmourMaxSpeed(),
        source.getArmourWeight(),
        source.getArmourSpecialProperties());
  }

  public ArmourSubtype generateAll(DatabaseHolder databaseHolder) {
    return this;
  }
}
