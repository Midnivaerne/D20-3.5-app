package com.aurora.core.models.userdata;

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
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.HeroChild;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.typehelpers.ItemType;
import com.aurora.core.models.usables.Armour;
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
    new HeroArmour(name, source, idAsNameBackup, null, null);
  }

  public HeroArmour(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentHeroId,
      Integer armourId) {
    super(name, source, idAsNameBackup);
    this.setHeroParentHeroId(heroParentHeroId);
    this.setArmourId(armourId);
  }

  @Ignore
  public HeroArmour(HeroArmour source) {
    new HeroArmour(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentHeroId(),
        source.getArmourId()
    );
  }

  HeroArmour generateAll(DatabaseHolder databaseHolder) {
    setArmour(databaseHolder.armourMap.get(getArmourId()));
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_NAME, getName());
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_AC, getArmour().getArmourDeflection());
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_DEX, getArmour().getArmourMaxDexterityBonus());
    this.armourValues.put(PlayerCharacterArmourEnum.ARMOUR_PROPERTIES, getArmour().getArmourSpecialProperties());
    return this;
  }
}
