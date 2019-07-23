package com.aurora.core.database.models.userdata;

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
import static com.aurora.core.database.DbColumnNames.HERO_PARENT_HERO_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_RACE_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_SIZE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_STATISTICS;
import static com.aurora.core.database.TranslationsHolder.translate;

import android.util.Log;
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
import java.util.Objects;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.constants.Alignments;
import com.aurora.core.database.models.constants.Sizes;
import com.aurora.core.database.models.helpers.HeroChild;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.helpers.ValuesConverter;
import com.aurora.core.database.models.settingspecific.Classes;
import com.aurora.core.database.models.settingspecific.Deities;
import com.aurora.core.database.models.settingspecific.RaceTemplates;
import com.aurora.core.database.models.settingspecific.Races;
import com.aurora.core.database.models.typehelpers.ItemType;
import com.aurora.core.utils.CustomStringParsers;
import com.aurora.player.playercharacterutils.PlayerCharacterAbilityScoresEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterCombatEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterSavingThrowsEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_STATISTICS, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_HERO_ID_COLUMN_NAME), @Index(HERO_ALIGNMENT_ID_COLUMN_NAME),
        @Index(HERO_DEITY_ID_COLUMN_NAME), @Index(HERO_SIZE_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_HERO_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Alignments.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_ALIGNMENT_ID_COLUMN_NAME),
        @ForeignKey(entity = Sizes.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_SIZE_COLUMN_NAME),
        @ForeignKey(entity = Deities.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_DEITY_ID_COLUMN_NAME)}
)
public class HeroValues extends Item implements ValuesConverter, HeroChild {

  @ColumnInfo(name = HERO_PARENT_HERO_ID_COLUMN_NAME)
  private Integer heroParentHeroId;

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
  private HashMap<Classes, Integer> classes = new HashMap<>();

  @ColumnInfo(name = HERO_RACE_ID_COLUMN_NAME)
  private String heroRaceId;

  @Ignore
  private Races race;

  @Ignore
  private RaceTemplates raceTemplate;

  @ColumnInfo(name = HERO_ALIGNMENT_ID_COLUMN_NAME)
  private Integer heroAlignmentId;

  @Ignore
  private String alignmentString;

  @ColumnInfo(name = HERO_DEITY_ID_COLUMN_NAME)
  private Integer heroDeityId;

  @Ignore
  private String deityString;

  @ColumnInfo(name = HERO_SIZE_COLUMN_NAME)
  private Integer heroSizeId;

  @Ignore
  private String sizeString;

  @ColumnInfo(name = HERO_GENDER_COLUMN_NAME)
  private String heroGender;

  @Ignore
  private Map<PlayerCharacterCombatEnum, String> combatTextValues = new HashMap<>();

  @Ignore
  private Map<PlayerCharacterAbilityScoresEnum, Integer> abilityScoreValues = new HashMap<>();

  @Ignore
  private Map<PlayerCharacterSavingThrowsEnum, Integer> savingThrowsValues = new HashMap<>();

  @Ignore
  public HeroValues() {
    super();
  }

  @Ignore
  public HeroValues(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  public HeroValues(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentHeroId,
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
    this.heroParentHeroId = heroParentHeroId;
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

  public void generateAll(DatabaseHolder databaseHolder) {
    this.generateRaceFromId(databaseHolder)
        .generateClassListFromId(databaseHolder)
        .generateAlignmentStringFromId(databaseHolder)
        .generateDeityStringFromId(databaseHolder)
        .generateSizeStringFromId(databaseHolder);
    populateCombatTextValues();
    populateAbilityScoreValues();
    populateSavingThrowsValues();
  }

  private void populateCombatTextValues() {
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_HIT_POINTS, getHeroHitPointsStringFromList());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_DAMAGE_REDUCTION, getDamageReduction());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_ARMOUR_CLASS, getArmourClass());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_ARMOUR_CLASS_TOUCH, getArmourClassTouch());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_ARMOUR_CLASS_FLATFOOTED, getArmourClassFlatfooted());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_SPEED, getSpeed());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_INITIATIVE, getInitiative());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_ATTACK, getAttack());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_ATTACK_MELEE, getAttackMelee());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_ATTACK_RANGED, getAttackRanged());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_GRAPPLE, getGrapple());
    combatTextValues.put(PlayerCharacterCombatEnum.HERO_COMBAT_SPELL_RESISTANCE, getSpellResistance());
  }

  private void populateAbilityScoreValues() {
    abilityScoreValues.put(PlayerCharacterAbilityScoresEnum.HERO_ABILITY_SCORES_STR, getHeroAbilityScoreStr());
    abilityScoreValues.put(PlayerCharacterAbilityScoresEnum.HERO_ABILITY_SCORES_DEX, getHeroAbilityScoreDex());
    abilityScoreValues.put(PlayerCharacterAbilityScoresEnum.HERO_ABILITY_SCORES_CON, getHeroAbilityScoreCon());
    abilityScoreValues.put(PlayerCharacterAbilityScoresEnum.HERO_ABILITY_SCORES_INT, getHeroAbilityScoreInt());
    abilityScoreValues.put(PlayerCharacterAbilityScoresEnum.HERO_ABILITY_SCORES_WIS, getHeroAbilityScoreInt());
    abilityScoreValues.put(PlayerCharacterAbilityScoresEnum.HERO_ABILITY_SCORES_CHA, getHeroAbilityScoreCha());
  }

  private void populateSavingThrowsValues() {
    savingThrowsValues.put(PlayerCharacterSavingThrowsEnum.HERO_SAVING_THROWS_FORTITUDE, getFortitude());
    savingThrowsValues.put(PlayerCharacterSavingThrowsEnum.HERO_SAVING_THROWS_REFLEX, getReflex());
    savingThrowsValues.put(PlayerCharacterSavingThrowsEnum.HERO_SAVING_THROWS_WILL, getWill());
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
  public HeroValues generateAlignmentStringFromId(DatabaseHolder databaseHolder) {
    setAlignmentString(translate(databaseHolder.alignmentsMap.get(heroAlignmentId).getName()));
    return this;
  }

  @Ignore
  public HeroValues generateDeityStringFromId(DatabaseHolder databaseHolder) {
    setDeityString(translate(databaseHolder.deitiesMap.get(heroDeityId).getName()));
    return this;
  }

  @Ignore
  public HeroValues generateSizeStringFromId(DatabaseHolder databaseHolder) {
    setSizeString(translate(databaseHolder.sizesMap.get(heroSizeId).getName()));
    return this;
  }


  @Ignore
  public String getHeroHitPointsStringFromList() {
    return CustomStringParsers.stringWithCommaToSum(heroHitPoints);
  }

  @Ignore
  public HeroValues(HeroValues source) {
    new HeroValues(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentHeroId(),
        source.getHeroHitPoints(),
        source.getHeroAbilityScoreStr(),
        source.getHeroAbilityScoreDex(),
        source.getHeroAbilityScoreCon(),
        source.getHeroAbilityScoreInt(),
        source.getHeroAbilityScoreWis(),
        source.getHeroAbilityScoreCha(),
        source.getHeroClassIdList(),
        source.getHeroRaceId(),
        source.getHeroAlignmentId(),
        source.getHeroDeityId(),
        source.getHeroSizeId(),
        source.getHeroGender());
  }

  public String getDamageReduction() {
    StringBuilder out = new StringBuilder();
    //getHeroValues().getRace().getSpecialQualities().iterator()
    //    .forEachRemaining((SpecQ) -> (SpecQ.getName().equals(SpecialQualities.DamageReduction)) ? out.append("") : out.append(SpecQ));
    //getHeroValues().getRaceTemplate() == null ? "" : getHeroValues().getRaceTemplate().getSpecialQualities().getDamageReduction());
    // + getItemDamageReduction()
    // + getEffectDamageReduction();//todo proper value, multiple DR possible
    return String.valueOf(out);
  }

  public String getArmourClass() {
    int out = 10; //todo proper value
    return String.valueOf(out);
  }

  public String getArmourClassTouch() {
    int out = 10;//todo proper value
    return String.valueOf(out);
  }

  public String getArmourClassFlatfooted() {
    int out = 10;//todo proper value
    return String.valueOf(out);
  }

  public String getSpeed() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getInitiative() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getAttack() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getAttackMelee() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getAttackRanged() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getGrapple() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public String getSpellResistance() {
    int out = 0;//todo proper value
    return String.valueOf(out);
  }

  public Integer getFortitude() {
    int out = 0;//todo proper value
    return out;
  }

  public Integer getReflex() {
    int out = 0;//todo proper value
    return out;
  }

  public Integer getWill() {
    int out = 0;//todo proper value
    return out;
  }
}
