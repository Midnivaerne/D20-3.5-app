package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_EQUIPMENT_CONTAINER_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_EQUIPMENT_WORN_PLACE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_EQUIPMENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_EQUIPMENT;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.typehelpers.ItemType;
import com.aurora.core.models.usables.Equipment;
import com.aurora.core.models.usables.Weapons;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_EQUIPMENT, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME), @Index(HERO_PARENT_EQUIPMENT_ID_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Weapons.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_EQUIPMENT_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroEquipment extends Item {

  @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
  private Integer heroParentItemID;

  @ColumnInfo(name = HERO_PARENT_EQUIPMENT_ID_COLUMN_NAME)
  private Integer equipmentId;

  @Ignore
  private Equipment equipment;

  @ColumnInfo(name = HERO_EQUIPMENT_WORN_PLACE_COLUMN_NAME)
  private PlayerCharacterWornEquipmentPlacesEnum wornPlace;

  @ColumnInfo(name = HERO_EQUIPMENT_CONTAINER_COLUMN_NAME)
  private Integer parentContainer;

  public HeroEquipment() {
    super();
  }

  @Ignore
  public HeroEquipment(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  @Ignore
  public HeroEquipment(String name,
      String source,
      String idAsNameBackup) {
    new HeroEquipment(name, source, idAsNameBackup, null, null);
  }

  public HeroEquipment(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentItemID,
      Integer weaponId) {
    super(name, source, idAsNameBackup);
    this.setHeroParentItemID(heroParentItemID);
    this.setEquipmentId(weaponId);
  }

  @Ignore
  public HeroEquipment(HeroEquipment source) {
    new HeroEquipment(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentItemID(),
        source.getEquipmentId()
    );
  }

  HeroEquipment generateAll(DatabaseHolder databaseHolder) {
    setEquipment(databaseHolder.equipmentMap.get(getEquipmentId()));
    return this;
  }
}
