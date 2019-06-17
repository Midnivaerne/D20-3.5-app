package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_AGE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_EYES_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_HAIR_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_HEIGHT_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PLAYER_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_SKIN_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_WEIGHT_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_DESCRIPTION;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.typehelpers.ItemType;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_DESCRIPTION, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroDescription extends Item {

  @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
  private Integer heroParentItemID;

  @ColumnInfo(name = HERO_PLAYER_COLUMN_NAME)
  private String heroPlayer;

  @ColumnInfo(name = HERO_AGE_COLUMN_NAME)
  private Integer heroAge;

  @ColumnInfo(name = HERO_HEIGHT_COLUMN_NAME)
  private String heroHeight;

  @ColumnInfo(name = HERO_WEIGHT_COLUMN_NAME)
  private String heroWeight;

  @ColumnInfo(name = HERO_EYES_COLUMN_NAME)
  private String heroEyes;

  @ColumnInfo(name = HERO_HAIR_COLUMN_NAME)
  private String heroHair;

  @ColumnInfo(name = HERO_SKIN_COLUMN_NAME)
  private String heroSkin;

  @Ignore
  public HeroDescription() {
    super();
  }

  @Ignore
  public HeroDescription(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  public HeroDescription(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentItemID,
      String heroPlayer,
      Integer heroAge,
      String heroHeight,
      String heroWeight,
      String heroEyes,
      String heroHair,
      String heroSkin) {
    super(name, source, idAsNameBackup);
    this.heroParentItemID = heroParentItemID;
    this.heroPlayer = heroPlayer;
    this.heroAge = heroAge;
    this.heroHeight = heroHeight;
    this.heroWeight = heroWeight;
    this.heroEyes = heroEyes;
    this.heroHair = heroHair;
    this.heroSkin = heroSkin;
  }

  @Ignore
  public HeroDescription(HeroDescription source) {
    new HeroDescription(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentItemID(),
        source.getHeroPlayer(),
        source.getHeroAge(),
        source.getHeroHeight(),
        source.getHeroWeight(),
        source.getHeroEyes(),
        source.getHeroHair(),
        source.getHeroSkin());
  }
}
