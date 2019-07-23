package com.aurora.core.database.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_ARMOUR_MATERIAL_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ARMOUR_ARMOUR_SPECIFICS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ARMOUR_PARENT_ARMOUR_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_HERO_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_ARMOUR;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.HeroChild;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.MaterialTypes;
import com.aurora.core.database.models.typehelpers.ItemType;
import com.aurora.core.database.models.usables.Armour;
import com.aurora.player.playercharacterutils.PlayerCharacterArmourEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_ARMOUR, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_HERO_ID_COLUMN_NAME), @Index(HERO_ARMOUR_PARENT_ARMOUR_ID_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_HERO_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Armour.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_ARMOUR_PARENT_ARMOUR_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroArmour extends Item implements HeroChild {

  @ColumnInfo(name = HERO_PARENT_HERO_ID_COLUMN_NAME)
  private Integer heroParentHeroId;

  @ColumnInfo(name = HERO_ARMOUR_PARENT_ARMOUR_ID_COLUMN_NAME)
  private Integer armourId;

  @Ignore
  private Armour armour;

  @ColumnInfo(name = HERO_ARMOUR_ARMOUR_SPECIFICS_COLUMN_NAME)
  private String armourSpecificIds;

  @ColumnInfo(name = HERO_ARMOUR_MATERIAL_ID_COLUMN_NAME)
  private Integer armourMaterialId;

  @Ignore
  private MaterialTypes armourMaterial;

  @Ignore
  private Map<PlayerCharacterArmourEnum, String> armourValues = new HashMap<>();

  @Ignore
  public HeroArmour() {
    super();
  }

  @Ignore
  public HeroArmour(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  @Ignore
  public HeroArmour(String name,
      String source,
      String idAsNameBackup) {
    new HeroArmour(name, source, idAsNameBackup, null, null, null, null);
  }

  public HeroArmour(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentHeroId,
      Integer armourId,
      String armourSpecificIds,
      Integer armourMaterialId) {
    super(name, source, idAsNameBackup);
    this.setHeroParentHeroId(heroParentHeroId);
    this.setArmourId(armourId);
    this.setArmourSpecificIds(armourSpecificIds);
    this.setArmourMaterialId(armourMaterialId);
  }

  @Ignore
  public HeroArmour(HeroArmour source) {
    new HeroArmour(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentHeroId(),
        source.getArmourId(),
        source.getArmourSpecificIds(),
        source.getArmourMaterialId()
    );
  }

  HeroArmour generateAll(DatabaseHolder databaseHolder) {
    setArmour(databaseHolder.armourMap.get(getArmourId()));
    if (getArmourMaterialId() != null) {
      setArmourMaterial(databaseHolder.materialTypesMap.get(getArmourMaterialId()));
    }
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_NAME, getName());
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_AC, String.valueOf(getArmour().getArmourSubtype().getArmourValue()));
    this.armourValues
        .put(PlayerCharacterArmourEnum.ARMOUR_DEX, String.valueOf(getArmour().getArmourSubtype().getArmourMaxDexterityBonus()));
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_PROPERTIES, getArmour().getArmourSubtype().getArmourSpecialProperties());
    return this;
  }
}
