package com.aurora.core.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SKILL_USE_ARMOUR_PENALTY_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SKILL_ATTRIBUTE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SKILL_CAN_HAVE_SUBSKILL_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SKILL_EXCLUSIVE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SKILL_IMPROVES_OTHER_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SKILL_OTHER_TO_IMPROVE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SKILL_SUBSKILL_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.SKILLS;

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
@Entity(tableName = SKILLS, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Skills extends Item {

  @ColumnInfo(name = SKILL_ATTRIBUTE_COLUMN_NAME)
  private String skillAttribute;

  @ColumnInfo(name = SKILL_EXCLUSIVE_COLUMN_NAME)
  private String skillExclusive;

  @ColumnInfo(name = SKILL_USE_ARMOUR_PENALTY_COLUMN_NAME)
  private String skillUseArmourPenalty;

  @ColumnInfo(name = SKILL_CAN_HAVE_SUBSKILL_COLUMN_NAME)
  private String skillCanHaveSubskills;

  @ColumnInfo(name = SKILL_SUBSKILL_COLUMN_NAME)
  private String skillSubskill;

  @ColumnInfo(name = SKILL_IMPROVES_OTHER_COLUMN_NAME)
  private String skillImprovesOther;

  @ColumnInfo(name = SKILL_OTHER_TO_IMPROVE_COLUMN_NAME)
  private String skillOtherToImprove;

  @Ignore
  public Skills() {
    super();
  }

  @Ignore
  public Skills(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  @Ignore
  public Skills(String name,
      String source,
      String idAsNameBackup) {
    new Skills(name, source, idAsNameBackup, null, null, null, null, null, null, null);
  }

  public Skills(String name,
      String source,
      String idAsNameBackup,
      String skillAttribute,
      String skillExclusive,
      String skillUseArmourPenalty,
      String skillCanHaveSubskills,
      String skillSubskill,
      String skillImprovesOther,
      String skillOtherToImprove) {
    super(name, source, idAsNameBackup);
    this.skillAttribute = skillAttribute;
    this.skillExclusive = skillExclusive;
    this.skillUseArmourPenalty = skillUseArmourPenalty;
    this.skillCanHaveSubskills = skillCanHaveSubskills;
    this.skillSubskill = skillSubskill;
    this.skillImprovesOther = skillImprovesOther;
    this.skillOtherToImprove = skillOtherToImprove;
  }

  @Ignore
  public Skills(Skills source) {
    new Skills(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getSkillAttribute(),
        source.getSkillExclusive(),
        source.getSkillUseArmourPenalty(),
        source.getSkillCanHaveSubskills(),
        source.getSkillSubskill(),
        source.getSkillImprovesOther(),
        source.getSkillOtherToImprove());
  }
}
