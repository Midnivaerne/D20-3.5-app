package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_ABILITY_SCORE_CHA_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ABILITY_SCORE_CON_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ABILITY_SCORE_DEX_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ABILITY_SCORE_INT_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ABILITY_SCORE_STR_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ABILITY_SCORE_WIS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_ALIGNMENT_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_CLASS_ID_LIST_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_DEITY_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_GENDER_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_HIT_POINTS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_RACE_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_SIZE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_STATISTICS;
import static com.aurora.core.database.TranslationsHolder.translate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import android.util.Log;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Objects;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.models.Databases;
import com.aurora.core.models.constants.Alignments;
import com.aurora.core.models.constants.Sizes;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Classes;
import com.aurora.core.models.settingspecific.Deities;
import com.aurora.core.models.settingspecific.RaceTemplates;
import com.aurora.core.models.settingspecific.Races;
import com.aurora.core.models.typehelpers.ItemType;
import com.aurora.core.utils.CustomStringParsers;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_STATISTICS, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME), @Index(HERO_ALIGNMENT_ID_COLUMN_NAME),
        @Index(HERO_DEITY_ID_COLUMN_NAME), @Index(HERO_SIZE_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Alignments.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_ALIGNMENT_ID_COLUMN_NAME),
        @ForeignKey(entity = Sizes.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_SIZE_COLUMN_NAME),
        @ForeignKey(entity = Deities.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_DEITY_ID_COLUMN_NAME)}
)
public class HeroValues extends Item {

  @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
  private Integer heroParentItemID;

  @ColumnInfo(name = HERO_HIT_POINTS_COLUMN_NAME)
  private String heroHitPoints;

  @ColumnInfo(name = HERO_ABILITY_SCORE_STR_COLUMN_NAME)
  private Integer heroAbilityScoreStr;

  @ColumnInfo(name = HERO_ABILITY_SCORE_DEX_COLUMN_NAME)
  private Integer heroAbilityScoreDex;

  @ColumnInfo(name = HERO_ABILITY_SCORE_CON_COLUMN_NAME)
  private Integer heroAbilityScoreCon;

  @ColumnInfo(name = HERO_ABILITY_SCORE_INT_COLUMN_NAME)
  private Integer heroAbilityScoreInt;

  @ColumnInfo(name = HERO_ABILITY_SCORE_WIS_COLUMN_NAME)
  private Integer heroAbilityScoreWis;

  @ColumnInfo(name = HERO_ABILITY_SCORE_CHA_COLUMN_NAME)
  private Integer heroAbilityScoreCha;

  @ColumnInfo(name = HERO_CLASS_ID_LIST_COLUMN_NAME)
  private String heroClassIdList;

  @Ignore
  private HashMap<Classes, Integer> classes;

  @ColumnInfo(name = HERO_RACE_ID_COLUMN_NAME)
  private String heroRaceId;

  @Ignore
  private Races race;

  @Ignore
  private RaceTemplates raceTemplate;

  @ColumnInfo(name = HERO_ALIGNMENT_ID_COLUMN_NAME)
  private Integer heroAlignmentId;

  @ColumnInfo(name = HERO_DEITY_ID_COLUMN_NAME)
  private Integer heroDeityId;

  @ColumnInfo(name = HERO_SIZE_COLUMN_NAME)
  private Integer heroSizeId;

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
      String heroClassIdList,
      String heroRaceId,
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
    this.heroClassIdList = heroClassIdList;
    this.heroRaceId = heroRaceId;
    this.heroAlignmentId = heroAlignmentId;
    this.heroDeityId = heroDeityId;
    this.heroSizeId = heroSizeId;
    this.heroGender = heroGender;
  }

  @Ignore
  public static int getStatisticModifier(int stat) {
    return ((stat / 2) - 5);
  }

  @Ignore
  public HeroValues generateRaceFromId(DatabaseHolder databaseHolder) {
    race = databaseHolder.racesMap.get(Integer.parseInt(heroRaceId.split("\\+")[0])).generateSpecials(databaseHolder);
    if (heroRaceId.split("\\+").length == 2) {
      raceTemplate = databaseHolder.raceTemplatesMap.get(Integer.parseInt(heroRaceId.split("\\+")[1])).generateSpecials(databaseHolder);
    }
    return this;
  }

  @Ignore
  public String getRaceAndTemplateNamesFromObjects() {
    return translate(getRace().getName()) + (getRaceTemplate() == null ? "" : " " + translate(getRaceTemplate().getName()));
  }

  @Ignore
  public HeroValues generateClassListFromId(DatabaseHolder databaseHolder) {
    setClasses(new HashMap<>());
    String[] heroClasses = CustomStringParsers.stringWithCommaToTable(heroClassIdList);
    for (String clasId : heroClasses) {
      String classNameFromBackup = getBackupNames().get(ItemType.CLASSES).get(Integer.parseInt(clasId));
      Classes heroClass = databaseHolder.classesMap.get(Integer.parseInt(clasId));
      if (heroClass == null && classNameFromBackup == null) {
        Log.e("Database contents:", "missing class: " + clasId + " and backup names");
      } else if (heroClass == null) {
        int count = 0;
        for (Classes cl : databaseHolder.classesMap.values()) {
          if (cl.getName().equals(classNameFromBackup)) {
            heroClass = cl;
            count++;
          }
        }
        if (count > 1) {
          Log.e("Database contents:", "more than one class with name: " + classNameFromBackup);
        }
        if (heroClass == null) {
          Log.e("Database contents:", "missing class: " + clasId);
        }
        DatabaseManager.dbCheckup();
      }
      if (!Objects.requireNonNull(heroClass).getName().equals(classNameFromBackup)) {
        DatabaseManager.dbCheckup();
      }

      if (getClasses().containsKey(heroClass) && getClasses().get(heroClass) != null) {
        getClasses().put(heroClass, getClasses().get(heroClass) + 1);
      } else {
        Objects.requireNonNull(getClasses()).put(heroClass, 1);
      }
    }
    return this;
  }

  @Ignore
  public String getClassListFromMap() {
    StringBuilder sb = new StringBuilder();
    getClasses().forEach((cls, lvl) -> sb.append(translate(cls.getName())).append(" ").append(lvl).append(" "));
    return String.valueOf(sb);
  }

  @Ignore
  public String getAlignmentStringFromId(DatabaseHolder databaseHolder) {
    return translate(databaseHolder.alignmentsMap.get(heroAlignmentId).getName());
  }

  @Ignore
  public String getDeityStringFromId(DatabaseHolder databaseHolder) {
    return translate(databaseHolder.deitiesMap.get(heroDeityId).getName());
  }

  @Ignore
  public String getSizeStringFromId(DatabaseHolder databaseHolder) {
    return translate(databaseHolder.sizesMap.get(heroSizeId).getName());
  }

  @Ignore
  public String getHeroHitPointsStringFromList() {
    return CustomStringParsers.stringWithCommaToSum(heroHitPoints);
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
        getHeroClassIdList(),
        getHeroRaceId(),
        getHeroAlignmentId(),
        getHeroDeityId(),
        getHeroSizeId(),
        getHeroGender());
  }
}
