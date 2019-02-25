package com.aurora.d20_35_app.models.userData;

import com.aurora.d20_35_app.database.DBColumnNames;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.database.DatabaseManager;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.constants.RulesAlignments;
import com.aurora.d20_35_app.models.constants.RulesSizes;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Deities;
import com.aurora.d20_35_app.models.typeHelpers.ItemType;
import com.aurora.d20_35_app.utils.CustomStringParsers;

import java.util.HashMap;
import java.util.Map;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ALIGNMENT_ID_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_DEITY_ID_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_SIZE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.HERO_DESCRIPTION;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = HERO_DESCRIPTION, inheritSuperIndices = true,
        indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME), @Index(HERO_ALIGNMENT_ID_COLUMN_NAME), @Index(HERO_DEITY_ID_COLUMN_NAME), @Index(HERO_SIZE_COLUMN_NAME)},
        foreignKeys = {
                @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = HeroPlayer.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = RulesAlignments.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_ALIGNMENT_ID_COLUMN_NAME),
                @ForeignKey(entity = RulesSizes.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_SIZE_COLUMN_NAME),
                @ForeignKey(entity = Deities.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_DEITY_ID_COLUMN_NAME)}
)
public class HeroDescription extends Item {

    @Ignore
    public HeroDescription() {
        super();
    }

    public HeroDescription(String name,
                           String source,
                           String idAsNameBackup,
                           Integer heroParentItemID,
                           String heroPlayer,
                           String heroClassAndLevel,
                           String heroRace,
                           Integer heroAlignmentId,
                           Integer heroDeityId,
                           Integer heroSizeId,
                           Integer heroAge,
                           String heroGender,
                           String heroHeight,
                           String heroWeight,
                           String heroEyes,
                           String heroHair,
                           String heroSkin) {
        super(name, source, idAsNameBackup);
        this.heroParentItemID = heroParentItemID;
        this.heroPlayer = heroPlayer;
        this.heroClassAndLevel = heroClassAndLevel;
        this.heroRace = heroRace;
        this.heroAlignmentId = heroAlignmentId;
        this.heroDeityId = heroDeityId;
        this.heroSizeId = heroSizeId;
        this.heroAge = heroAge;
        this.heroGender = heroGender;
        this.heroHeight = heroHeight;
        this.heroWeight = heroWeight;
        this.heroEyes = heroEyes;
        this.heroHair = heroHair;
        this.heroSkin = heroSkin;
    }

    @Getter
    @Setter
    @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
    private Integer heroParentItemID;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_PLAYER_COLUMN_NAME)
    private String heroPlayer;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_CLASS_AND_LEVEL_COLUMN_NAME)
    private String heroClassAndLevel;

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

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_RACE_COLUMN_NAME)
    private String heroRace;

    public String getRaceStringFromId(DatabaseHolder databaseHolder) {
        return heroRace; //todo get race (and template) from this id('s)
    }

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ALIGNMENT_ID_COLUMN_NAME)
    private Integer heroAlignmentId;

    public String getAlignmentStringFromId(DatabaseHolder databaseHolder) {
        return databaseHolder.RULES_ALIGNMENTS_MAP.get(heroAlignmentId).getName();//todo translate
    }

    @Getter
    @Setter
    @ColumnInfo(name = HERO_DEITY_ID_COLUMN_NAME)
    private Integer heroDeityId;

    public String getDeityStringFromId(DatabaseHolder databaseHolder) {
        return databaseHolder.DEITIES_MAP.get(heroDeityId).getName();//todo translate
    }

    @Getter
    @Setter
    @ColumnInfo(name = HERO_SIZE_COLUMN_NAME)
    private Integer heroSizeId;

    public String getSizeStringFromId(DatabaseHolder databaseHolder) {
        return databaseHolder.RULES_SIZES_MAP.get(heroSizeId).getName();//todo translate
    }

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_AGE_COLUMN_NAME)
    private Integer heroAge;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_GENDER_COLUMN_NAME)
    private String heroGender;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_HEIGHT_COLUMN_NAME)
    private String heroHeight;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_WEIGHT_COLUMN_NAME)
    private String heroWeight;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_EYES_COLUMN_NAME)
    private String heroEyes;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_HAIR_COLUMN_NAME)
    private String heroHair;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_SKIN_COLUMN_NAME)
    private String heroSkin;


    public HeroDescription clone() {
        return new HeroDescription(
                getName(),
                getSource(),
                getIdAsNameBackup(),
                getHeroParentItemID(),
                getHeroPlayer(),
                getHeroClassAndLevel(),
                getHeroRace(),
                getHeroAlignmentId(),
                getHeroDeityId(),
                getHeroSizeId(),
                getHeroAge(),
                getHeroGender(),
                getHeroHeight(),
                getHeroWeight(),
                getHeroEyes(),
                getHeroHair(),
                getHeroSkin());
    }
}
