package com.aurora.core.models.userData;

import static com.aurora.core.database.DBColumnNames.HERO_ABILITY_SCORE_CHA_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_ABILITY_SCORE_CON_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_ABILITY_SCORE_DEX_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_ABILITY_SCORE_INT_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_ABILITY_SCORE_STR_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_ABILITY_SCORE_WIS_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_ALIGNMENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_CLASS_AND_LEVEL_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_DEITY_ID_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_GENDER_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_HIT_POINTS_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_RACE_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.HERO_SIZE_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DBTableNames.HERO_STATISTICS;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.models.Databases;
import com.aurora.core.models.constants.RulesAlignments;
import com.aurora.core.models.constants.RulesSizes;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingSpecific.Deities;
import com.aurora.core.models.typeHelpers.ItemType;
import com.aurora.core.utils.CustomStringParsers;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = HERO_STATISTICS, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME), @Index(HERO_ALIGNMENT_ID_COLUMN_NAME),
        @Index(HERO_DEITY_ID_COLUMN_NAME), @Index(HERO_SIZE_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = RulesAlignments.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_ALIGNMENT_ID_COLUMN_NAME),
        @ForeignKey(entity = RulesSizes.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_SIZE_COLUMN_NAME),
        @ForeignKey(entity = Deities.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_DEITY_ID_COLUMN_NAME)}
)
public class HeroValues extends Item {

  @Getter
  @Setter
  @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
  private Integer heroParentItemID;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_HIT_POINTS_COLUMN_NAME)
  private String heroHitPoints;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ABILITY_SCORE_STR_COLUMN_NAME)
  private Integer heroAbilityScoreStr;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ABILITY_SCORE_DEX_COLUMN_NAME)
  private Integer heroAbilityScoreDex;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ABILITY_SCORE_CON_COLUMN_NAME)
  private Integer heroAbilityScoreCon;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ABILITY_SCORE_INT_COLUMN_NAME)
  private Integer heroAbilityScoreInt;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ABILITY_SCORE_WIS_COLUMN_NAME)
  private Integer heroAbilityScoreWis;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ABILITY_SCORE_CHA_COLUMN_NAME)
  private Integer heroAbilityScoreCha;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_CLASS_AND_LEVEL_COLUMN_NAME)
  private String heroClassAndLevel;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_RACE_COLUMN_NAME)
  private String heroRace;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_ALIGNMENT_ID_COLUMN_NAME)
  private Integer heroAlignmentId;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_DEITY_ID_COLUMN_NAME)
  private Integer heroDeityId;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_SIZE_COLUMN_NAME)
  private Integer heroSizeId;

  @Getter
  @Setter
  @ColumnInfo(name = HERO_GENDER_COLUMN_NAME)
  private String heroGender;

  @Ignore
  public HeroValues() {
    super();
  }

  public HeroValues(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentItemID,
      String heroHitPoints,
      Integer heroAbilityScoreStr,
      Integer heroAbilityScoreDex,
      Integer heroAbilityScoreCon,
      Integer heroAbilityScoreInt,
      Integer heroAbilityScoreWis,
      Integer heroAbilityScoreCha,
      String heroClassAndLevel,
      String heroRace,
      Integer heroAlignmentId,
      Integer heroDeityId,
      Integer heroSizeId,
      String heroGender) {
    super(name, source, idAsNameBackup);
    this.heroParentItemID = heroParentItemID;
    this.heroHitPoints = heroHitPoints;
    this.heroAbilityScoreStr = heroAbilityScoreStr;
    this.heroAbilityScoreDex = heroAbilityScoreDex;
    this.heroAbilityScoreCon = heroAbilityScoreCon;
    this.heroAbilityScoreInt = heroAbilityScoreInt;
    this.heroAbilityScoreWis = heroAbilityScoreWis;
    this.heroAbilityScoreCha = heroAbilityScoreCha;
    this.heroClassAndLevel = heroClassAndLevel;
    this.heroRace = heroRace;
    this.heroAlignmentId = heroAlignmentId;
    this.heroDeityId = heroDeityId;
    this.heroSizeId = heroSizeId;
    this.heroGender = heroGender;
  }

  public static int getStatisticModifier(int stat) {
    return ((stat / 2) - 5);
  }

  @Ignore
  public String getHeroClassAndLevelStringFromId(DatabaseHolder databaseHolder) {
    StringBuilder heroClassesOut = new StringBuilder();
    String[] heroClasses = CustomStringParsers.StringWithCommaToTable(heroClassAndLevel);
    Map<String, Integer> classAndLevels = new HashMap<>();
    for (String classes : heroClasses) {
      String className;
      String classNameFromBackup = getBackupNames().get(ItemType.CLASSES).get(Integer.getInteger(classes));
      Item aHeroClass = databaseHolder.CLASSES_MAP.get(Integer.getInteger(classes));
      if (aHeroClass != null) {
        className = aHeroClass.getName();
        if (!className.equals(classNameFromBackup)) {
          DatabaseManager.dbCheckup();
        }
      } else {
        className = classNameFromBackup;
        DatabaseManager.dbCheckup();
      }
      if (classAndLevels.containsKey(className)) {
        classAndLevels.put(className, classAndLevels.get(className) + 1);
      } else {
        classAndLevels.put(className, 1);
      }
      heroClassesOut.append(className).append(" ").append(classAndLevels.get(className)).append(" ");
    }
    return heroClassesOut.toString();
  }

  public String getRaceStringFromId(DatabaseHolder databaseHolder) {
    return heroRace; //todo get race (and template) from this id('s)
  }

  public String getAlignmentStringFromId(DatabaseHolder databaseHolder) {
    return databaseHolder.RULES_ALIGNMENTS_MAP.get(heroAlignmentId).getName();//todo translate
  }

  public String getDeityStringFromId(DatabaseHolder databaseHolder) {
    return databaseHolder.DEITIES_MAP.get(heroDeityId).getName();//todo translate
  }

  public String getSizeStringFromId(DatabaseHolder databaseHolder) {
    return databaseHolder.RULES_SIZES_MAP.get(heroSizeId).getName();//todo translate
  }

  public String getHeroHitPointsStringFromList() {
    return CustomStringParsers.StringWithCommaToSum(heroHitPoints);
  }

  public HeroValues clone() {
    return new HeroValues(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        getHeroParentItemID(),
        getHeroHitPoints(),
        getHeroAbilityScoreStr(),
        getHeroAbilityScoreDex(),
        getHeroAbilityScoreCon(),
        getHeroAbilityScoreInt(),
        getHeroAbilityScoreWis(),
        getHeroAbilityScoreCha(),
        getHeroClassAndLevel(),
        getHeroRace(),
        getHeroAlignmentId(),
        getHeroDeityId(),
        getHeroSizeId(),
        getHeroGender());
  }
}
