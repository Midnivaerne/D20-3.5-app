package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_SKILLS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_SKILLS;

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

import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Skills;
import com.aurora.core.models.typehelpers.ItemType;
import com.aurora.player.playercharacterutils.PlayerCharacterSkillsValues;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_SKILLS, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroSkills extends Item {

  @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
  private Integer heroParentItemID;

  @ColumnInfo(name = HERO_SKILLS_COLUMN_NAME)
  private String heroSkills;

  @Ignore
  private Map<Skills, PlayerCharacterSkillsValues> skillListAsSkillAndValue = new HashMap<>();

  @Ignore
  public HeroSkills() {
    super();
  }

  @Ignore
  public HeroSkills(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  @Ignore
  public HeroSkills(String name,
      String source,
      String idAsNameBackup) {
    new HeroSkills(name, source, idAsNameBackup, null, null);
  }

  public HeroSkills(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentItemID,
      String heroSkills) {
    super(name, source, idAsNameBackup);
    this.heroParentItemID = heroParentItemID;
    this.heroSkills = heroSkills;
  }

  public HeroSkills clone() {
    return new HeroSkills(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        getHeroParentItemID(),
        getHeroSkills());
  }
}
