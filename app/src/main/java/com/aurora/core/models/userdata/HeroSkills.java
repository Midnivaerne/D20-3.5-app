package com.aurora.core.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_SKILLS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_SKILLS;
import static com.aurora.core.utils.CommonUtils.SPLITTER_COMA;
import static com.aurora.core.utils.CommonUtils.SPLITTER_EQUALITY;
import static com.aurora.core.utils.CommonUtils.SPLITTER_SLASH;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Skills;
import com.aurora.core.models.typehelpers.ItemType;
import com.aurora.player.playercharacterutils.PlayerCharacterAbilityScoresEnum;
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
  private Map<Skills, Integer> skillListAsSkillAndRank = new HashMap<>();

  @Ignore
  private Map<Skills, Integer> allSettingSkillsWithOtherModifiers = new HashMap<>();

  @Ignore
  private Map<Skills, Map<PlayerCharacterSkillsValues, Integer>> valuesHolder = new HashMap<>();

  @Ignore
  private Map<PlayerCharacterAbilityScoresEnum, Integer> attributeModifiers = new HashMap();

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

  HeroSkills generateSkillListAsSkillAndRank(DatabaseHolder databaseHolder) {
    for (String skillRankPair : heroSkills.split(SPLITTER_COMA)) {
      skillListAsSkillAndRank
          .put(databaseHolder.skillsMap.get(Integer.valueOf(skillRankPair.split(SPLITTER_EQUALITY)[0])),
              Integer.valueOf(skillRankPair.split(SPLITTER_EQUALITY)[1]));
    }
    return this;
  }

  HeroSkills loadAllSettingSkills(DatabaseHolder databaseHolder, String raceSkills) {
    Map<Skills, Integer> raceSkillsMap = new HashMap<>();
    for (String skillRankPair : raceSkills.split(SPLITTER_COMA)) {
      raceSkillsMap.put(databaseHolder.skillsMap.get(Integer.valueOf(skillRankPair.split(SPLITTER_EQUALITY)[0])),
          Integer.valueOf(skillRankPair.split(SPLITTER_EQUALITY)[1]));
    }
    for (Skills skill : databaseHolder.skillsList) {
      if (!allSettingSkillsWithOtherModifiers.containsKey(skill)) {
        allSettingSkillsWithOtherModifiers.put(skill, 0);
      }
      if (raceSkillsMap.containsKey(skill)) {
        allSettingSkillsWithOtherModifiers
            .replace(skill, allSettingSkillsWithOtherModifiers.get(skill) + raceSkillsMap.get(skill));
      }
      if ("true".equals(skill.getSkillImprovesOther())) {
        for (String entry : skill.getSkillOtherToImprove().split(SPLITTER_COMA)) {
          Skills skillToImprove = ((Skills) getObjectWithNameFromList(entry.split(SPLITTER_EQUALITY)[0],
              new ArrayList<>(allSettingSkillsWithOtherModifiers.keySet())));
          System.out.println("---------" + entry);//todo delete
          Integer rankNeeded = Integer.valueOf(entry.split(SPLITTER_EQUALITY)[1].split(SPLITTER_SLASH)[0]);
          Integer actualRank = skillListAsSkillAndRank.containsKey(skill) ? skillListAsSkillAndRank.get(skill) : 0;
          if (rankNeeded.compareTo(actualRank) <= 0) {
            if (allSettingSkillsWithOtherModifiers.containsKey(skillToImprove)) {
              allSettingSkillsWithOtherModifiers.replace(skillToImprove, allSettingSkillsWithOtherModifiers.get(skillToImprove) + Integer
                  .valueOf(entry.split(SPLITTER_EQUALITY)[1].split(SPLITTER_SLASH)[1]));
            } else {
              allSettingSkillsWithOtherModifiers
                  .put(skillToImprove, Integer.valueOf(entry.split(SPLITTER_EQUALITY)[1].split(SPLITTER_SLASH)[1]));
            }
          }
        }
      }
    }
    for (Skills skill : allSettingSkillsWithOtherModifiers.keySet()) {
      HashMap<PlayerCharacterSkillsValues, Integer> values = new HashMap<>();
      values.put(PlayerCharacterSkillsValues.RANK, skillListAsSkillAndRank.containsKey(skill) ? skillListAsSkillAndRank.get(skill) : 0);
      values.put(PlayerCharacterSkillsValues.ATTRIBUTE_MODIFIER,
          (attributeModifiers.get(PlayerCharacterAbilityScoresEnum.getEnumFromShortDescription(skill.getSkillAttribute()))));
      values.put(PlayerCharacterSkillsValues.OTHER, (allSettingSkillsWithOtherModifiers.get(skill)));
      valuesHolder.put(skill, values);
    }
    return this;
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
