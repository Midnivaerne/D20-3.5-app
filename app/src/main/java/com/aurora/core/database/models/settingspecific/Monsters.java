package com.aurora.core.database.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.MONSTERS;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.helpers.ValuesConverter;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = MONSTERS, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Monsters extends Item implements ValuesConverter {

  public Monsters() {
    super();
  }

  public Monsters(String name,
      String source,
      String idAsNameBackup) {
    super(name, source, idAsNameBackup);
  }

  public Monsters(Monsters source) {
    new Monsters(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup());
  }

  @Override
  public String getDamageReduction() {
    return null;
  }

  @Override
  public String getArmourClass() {
    return null;
  }

  @Override
  public String getArmourClassTouch() {
    return null;
  }

  @Override
  public String getArmourClassFlatfooted() {
    return null;
  }

  @Override
  public String getSpeed() {
    return null;
  }

  @Override
  public String getInitiative() {
    return null;
  }

  @Override
  public String getAttack() {
    return null;
  }

  @Override
  public String getAttackMelee() {
    return null;
  }

  @Override
  public String getAttackRanged() {
    return null;
  }

  @Override
  public String getGrapple() {
    return null;
  }

  @Override
  public String getSpellResistance() {
    return null;
  }

  @Override
  public Integer getFortitude() {
    return null;
  }

  @Override
  public Integer getReflex() {
    return null;
  }

  @Override
  public Integer getWill() {
    return null;
  }
}
