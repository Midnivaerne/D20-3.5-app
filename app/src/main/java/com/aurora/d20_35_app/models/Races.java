package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(tableName = "Races", inheritSuperIndices = true)
public class Races extends Item {

    @Ignore
    public static final String raceDescriptionColumnName = "RaceDescription";
    @Ignore
    public static final String raceAttributeModifiersColumnName = "RaceAttributeModifiers";
    @Ignore
    public static final String raceSizeColumnName = "RaceSize";
    @Ignore
    public static final String raceSpeedColumnName = "RaceSpeed";
    @Ignore
    public static final String raceFeatsColumnName = "RaceFeats";
    @Ignore
    public static final String raceSkillsColumnName = "RaceSkillModifiers";
    @Ignore
    public static final String raceLanguagesColumnName = "RaceLanguages";
    @Ignore
    public static final String favouriteClassColumnName = "FavouriteClass";

    @Getter
    @Setter
    @ColumnInfo(name = raceDescriptionColumnName)
    private String raceDescription;

    @Getter
    @Setter
    @ColumnInfo(name = raceAttributeModifiersColumnName)
    private String raceAttributeModifiers;

    @Getter
    @Setter
    @ColumnInfo(name = raceSizeColumnName)
    private String raceSize;

    @Getter
    @Setter
    @ColumnInfo(name = raceSpeedColumnName)
    private String raceSpeed;

    @Getter
    @Setter
    @ColumnInfo(name = raceFeatsColumnName)
    private String raceFeats;

    @Getter
    @Setter
    @ColumnInfo(name = raceSkillsColumnName)
    private String raceSkills;

    @Getter
    @Setter
    @ColumnInfo(name = raceLanguagesColumnName)
    private String raceLanguages;

    @Getter
    @Setter
    @ColumnInfo(name = favouriteClassColumnName)
    private String favouriteClass;


}
