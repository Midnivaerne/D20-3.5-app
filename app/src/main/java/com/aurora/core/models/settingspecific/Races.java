package com.aurora.core.models.settingspecific;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.RACES;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DbColumnNames;
import com.aurora.core.models.Databases;
import com.aurora.core.models.helpers.Item;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = RACES, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Races extends Item {

  @ColumnInfo(name = DbColumnNames.RACE_DESCRIPTION_COLUMN_NAME)
  private String raceDescription;

  @ColumnInfo(name = DbColumnNames.RACE_ATTRIBUTE_MODIFIERS_COLUMN_NAME)
  private String raceAttributeModifiers;

  @ColumnInfo(name = DbColumnNames.RACE_SIZE_COLUMN_NAME)
  private String raceSize;

  @ColumnInfo(name = DbColumnNames.RACE_SPEED_COLUMN_NAME)
  private String raceSpeed;

  @ColumnInfo(name = DbColumnNames.RACE_SPECIAL_ATTACKS_COLUMN_NAME)
  private String raceSpecialAttacksIds;

  @Ignore
  private List<SpecialAttacks> specialAttacks;

  @ColumnInfo(name = DbColumnNames.RACE_SPECIAL_QUALITIES_COLUMN_NAME)
  private String raceSpecialQualitiesIds;

  @Ignore
  private List<SpecialQualities> specialQualities;

  @ColumnInfo(name = DbColumnNames.RACE_FEATS_COLUMN_NAME)
  private String raceFeats;

  @ColumnInfo(name = DbColumnNames.RACE_SKILLS_COLUMN_NAME)
  private String raceSkills;

  @ColumnInfo(name = DbColumnNames.RACE_LANGUAGES_COLUMN_NAME)
  private String raceLanguages;

  @ColumnInfo(name = DbColumnNames.RACE_FAVOURITE_CLASS_COLUMN_NAME)
  private String favouriteClass;

  @Ignore
  public Races() {
    super();
  }

  public Races(String name,
      String source,
      String idAsNameBackup,
      String raceDescription,
      String raceAttributeModifiers,
      String raceSize,
      String raceSpeed,
      String raceSpecialAttacksIds,
      String raceSpecialQualitiesIds,
      String raceFeats,
      String raceSkills,
      String raceLanguages,
      String favouriteClass) {
    super(name, source, idAsNameBackup);
    this.raceDescription = raceDescription;
    this.raceAttributeModifiers = raceAttributeModifiers;
    this.raceSize = raceSize;
    this.raceSpeed = raceSpeed;
    this.raceSpecialAttacksIds = raceSpecialAttacksIds;
    this.raceSpecialQualitiesIds = raceSpecialQualitiesIds;
    this.raceFeats = raceFeats;
    this.raceSkills = raceSkills;
    this.raceLanguages = raceLanguages;
    this.favouriteClass = favouriteClass;
  }

  @Ignore
  public Races generateSpecials(DatabaseHolder databaseHolder) {
    return this.generateSpecialAttacksFromIds(databaseHolder).generateSpecialQualitiesFromIds(databaseHolder);
  }

  @Ignore
  private Races generateSpecialAttacksFromIds(DatabaseHolder databaseHolder) {
    List<Integer> lst = new ArrayList<>();
    if (raceSpecialAttacksIds != null) {
      for (String pair : raceSpecialAttacksIds.split(",")) {
        lst.add(Integer.valueOf(pair.split("=")[0]));
      }
      specialAttacks = new ArrayList<>(
          (Collection<? extends SpecialAttacks>) databaseHolder.specialAttacksDaO().getObjectsWithIdsAsMergedObjectItem(lst).values());
    }
    return this;
  }

  @Ignore
  private Races generateSpecialQualitiesFromIds(DatabaseHolder databaseHolder) {
    List<Integer> lst = new ArrayList<>();
    if (raceSpecialQualitiesIds != null) {
      for (String pair : raceSpecialQualitiesIds.split(",")) {
        lst.add(Integer.valueOf(pair.split("=")[0]));
      }
      specialQualities = new ArrayList<>(
          (Collection<? extends SpecialQualities>) databaseHolder.specialQualitiesDaO().getObjectsWithIdsAsMergedObjectItem(lst).values());
    }
    return this;
  }

  public Races clone() {
    return new Races(getName(),
        getSource(),
        getIdAsNameBackup(),
        getRaceDescription(),
        getRaceAttributeModifiers(),
        getRaceSize(),
        getRaceSpeed(),
        getRaceSpecialAttacksIds(),
        getRaceSpecialQualitiesIds(),
        getRaceFeats(),
        getRaceSkills(),
        getRaceLanguages(),
        getFavouriteClass());
  }

}
