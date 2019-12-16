package com.aurora.core.database.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_EQUIPMENT_CONTAINER_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_EQUIPMENT_PARENT_EQUIPMENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_EQUIPMENT_WORN_PLACE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_HERO_ID_COLUMN_NAME;
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
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.HeroChild;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.typehelpers.ItemType;
import com.aurora.core.database.models.usables.Equipment;
import com.aurora.core.database.models.usables.Weapons;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_EQUIPMENT, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_HERO_ID_COLUMN_NAME), @Index(HERO_EQUIPMENT_PARENT_EQUIPMENT_ID_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_HERO_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Weapons.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_EQUIPMENT_PARENT_EQUIPMENT_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroEquipment extends Item implements HeroChild {

  @ColumnInfo(name = HERO_PARENT_HERO_ID_COLUMN_NAME)
  private Integer heroParentHeroId;

  @ColumnInfo(name = HERO_EQUIPMENT_PARENT_EQUIPMENT_ID_COLUMN_NAME)
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

  public HeroEquipment(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  public HeroEquipment(String name,
      String source,
      String idAsNameBackup) {
    new HeroEquipment(name, source, idAsNameBackup, null, null);
  }

  public HeroEquipment(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentHeroId,
      Integer weaponId) {
    super(name, source, idAsNameBackup);
    this.setHeroParentHeroId(heroParentHeroId);
    this.setEquipmentId(weaponId);
  }

  public HeroEquipment(HeroEquipment source) {
    new HeroEquipment(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentHeroId(),
        source.getEquipmentId()
    );
  }

  @Ignore
  HeroEquipment generateAll(DatabaseHolder databaseHolder) {
    setEquipment(databaseHolder.equipmentMap.get(getEquipmentId()));
    return this;
  }
}
