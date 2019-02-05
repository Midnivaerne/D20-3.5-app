package com.aurora.d20_35_app.models.settingSpecific;

import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.Databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Races", inheritSuperIndices = true,
        indices = {@Index(value = {"Source"})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source", onDelete = ForeignKey.CASCADE))
public class Races extends Item {

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
                 String raceFeats,
                 String raceSkills,
                 String raceLanguages,
                 String favouriteClass) {
        super(name, source, idAsNameBackup);
        this.raceDescription = raceDescription;
        this.raceAttributeModifiers = raceAttributeModifiers;
        this.raceSize = raceSize;
        this.raceSpeed = raceSpeed;
        this.raceFeats = raceFeats;
        this.raceSkills = raceSkills;
        this.raceLanguages = raceLanguages;
        this.favouriteClass = favouriteClass;
    }

    @Ignore
    public static final String RACE_DESCRIPTION_COLUMN_NAME = "RaceDescription";
    @Ignore
    public static final String RACE_ATTRIBUTE_MODIFIERS_COLUMN_NAME = "RaceAttributeModifiers";
    @Ignore
    public static final String RACE_SIZE_COLUMN_NAME = "RaceSize";
    @Ignore
    public static final String RACE_SPEED_COLUMN_NAME = "RaceSpeed";
    @Ignore
    public static final String RACE_FEATS_COLUMN_NAME = "RaceFeats";
    @Ignore
    public static final String RACE_SKILLS_COLUMN_NAME = "RaceSkillModifiers";
    @Ignore
    public static final String RACE_LANGUAGES_COLUMN_NAME = "RaceLanguages";
    @Ignore
    public static final String RACE_FAVOURITE_CLASS_COLUMN_NAME = "FavouriteClass";

    @Getter
    @Setter
    @ColumnInfo(name = RACE_DESCRIPTION_COLUMN_NAME)
    private String raceDescription;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_ATTRIBUTE_MODIFIERS_COLUMN_NAME)
    private String raceAttributeModifiers;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_SIZE_COLUMN_NAME)
    private String raceSize;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_SPEED_COLUMN_NAME)
    private String raceSpeed;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_FEATS_COLUMN_NAME)
    private String raceFeats;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_SKILLS_COLUMN_NAME)
    private String raceSkills;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_LANGUAGES_COLUMN_NAME)
    private String raceLanguages;

    @Getter
    @Setter
    @ColumnInfo(name = RACE_FAVOURITE_CLASS_COLUMN_NAME)
    private String favouriteClass;

    public Races clone() {
        return new Races(getName(),
                getSource(),
                getIdAsNameBackup(),
                getRaceDescription(),
                getRaceAttributeModifiers(),
                getRaceSize(),
                getRaceSpeed(),
                getRaceFeats(),
                getRaceSkills(),
                getRaceLanguages(),
                getFavouriteClass());
    }

}
