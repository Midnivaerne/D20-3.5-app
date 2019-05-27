package com.aurora.core.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.RACE_TEMPLATES;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DbColumnNames;
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = RACE_TEMPLATES, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class RaceTemplates extends Item {

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_DESCRIPTION_COLUMN_NAME)
  private String raceTemplateDescription;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_ATTRIBUTE_MODIFIERS_COLUMN_NAME)
  private String raceTemplateAttributeModifiers;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_SIZE_COLUMN_NAME)
  private String raceTemplateSize;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_SPEED_COLUMN_NAME)
  private String raceTemplateSpeed;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_SPECIAL_ATTACKS_COLUMN_NAME)
  private String raceTemplateSpecialAttacksIds;

  @Ignore
  private List<SpecialAttacks> specialAttacks;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_SPECIAL_QUALITIES_COLUMN_NAME)
  private String raceTemplateSpecialQualitiesIds;

  @Ignore
  private List<SpecialQualities> specialQualities;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_FEATS_COLUMN_NAME)
  private String raceTemplateFeats;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_SKILLS_COLUMN_NAME)
  private String raceTemplateSkills;

  @ColumnInfo(name = DbColumnNames.RACE_TEMPLATE_LANGUAGES_COLUMN_NAME)
  private String raceTemplateLanguages;

  @Ignore
  public RaceTemplates() {
    super();
  }

  public RaceTemplates(String name,
      String source,
      String idAsNameBackup,
      String raceTemplateDescription,
      String raceTemplateAttributeModifiers,
      String raceTemplateSize,
      String raceTemplateSpeed,
      String raceTemplateSpecialAttacksIds,
      String raceTemplateSpecialQualitiesIds,
      String raceTemplateFeats,
      String raceTemplateSkills,
      String raceTemplateLanguages) {
    super(name, source, idAsNameBackup);
    this.raceTemplateDescription = raceTemplateDescription;
    this.raceTemplateAttributeModifiers = raceTemplateAttributeModifiers;
    this.raceTemplateSize = raceTemplateSize;
    this.raceTemplateSpeed = raceTemplateSpeed;
    this.raceTemplateSpecialAttacksIds = raceTemplateSpecialAttacksIds;
    this.raceTemplateSpecialQualitiesIds = raceTemplateSpecialQualitiesIds;
    this.raceTemplateFeats = raceTemplateFeats;
    this.raceTemplateSkills = raceTemplateSkills;
    this.raceTemplateLanguages = raceTemplateLanguages;
  }

  @Ignore
  public RaceTemplates generateSpecials(DatabaseHolder databaseHolder) {
    return this.generateSpecialAttacksFromIds(databaseHolder).generateSpecialQualitiesFromIds(databaseHolder);
  }

  @Ignore
  private RaceTemplates generateSpecialAttacksFromIds(DatabaseHolder databaseHolder) {
    List<Integer> lst = new ArrayList<>();
    if (raceTemplateSpecialAttacksIds != null) {
      for (String pair : raceTemplateSpecialAttacksIds.split(",")) {
        lst.add(Integer.valueOf(pair.split("=")[0]));
      }
      specialAttacks = databaseHolder.specialAttacksDaO().getObjectsWithIdsAsMergedObjectItem(lst);
    }
    return this;
  }

  @Ignore
  private RaceTemplates generateSpecialQualitiesFromIds(DatabaseHolder databaseHolder) {
    List<Integer> lst = new ArrayList<>();
    if (raceTemplateSpecialQualitiesIds != null) {
      for (String pair : raceTemplateSpecialQualitiesIds.split(",")) {
        lst.add(Integer.valueOf(pair.split("=")[0]));
      }
      specialQualities = databaseHolder.specialQualitiesDaO().getObjectsWithIdsAsMergedObjectItem(lst);
    }
    return this;
  }

  public RaceTemplates clone() {
    return new RaceTemplates(
        getName(),
        getSource(),
        getIdAsNameBackup(),
        getRaceTemplateDescription(),
        getRaceTemplateAttributeModifiers(),
        getRaceTemplateSize(),
        getRaceTemplateSpeed(),
        getRaceTemplateSpecialAttacksIds(),
        getRaceTemplateSpecialQualitiesIds(),
        getRaceTemplateFeats(),
        getRaceTemplateSkills(),
        getRaceTemplateLanguages());
  }
}
