package com.aurora.d20_35_app.models.userData;

import com.aurora.d20_35_app.models.typeHelpers.ItemType;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.constants.RulesAlignments;
import com.aurora.d20_35_app.models.constants.RulesSizes;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Deities;
import com.aurora.d20_35_app.utils.CustomStringParsers;
import com.aurora.d20_35_app.database.DBColumnNames;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.database.DatabaseManager;

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
@Entity(tableName = "HeroDescription", inheritSuperIndices = true,
        indices = {@Index("Source"), @Index("ParentItemID"), @Index("AlignmentId"), @Index("DeityId"), @Index("SizeId")},
        foreignKeys = {
                @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = HeroPlayer.class, parentColumns = "Item_ID", childColumns = "ParentItemID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = RulesAlignments.class, parentColumns = "Item_ID", childColumns = "AlignmentId"),
                @ForeignKey(entity = RulesSizes.class, parentColumns = "Item_ID", childColumns = "SizeId"),
                @ForeignKey(entity = Deities.class, parentColumns = "Item_ID", childColumns = "DeityId")}
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
    @ColumnInfo(name = DBColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME)
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
        for (String classes : heroClasses) {
            int id = Integer.parseInt((classes.split("=")[0]));
            String className;
            String classNameFromBackup = getBackupNames().get(ItemType.CLASSES).get(id);
            Item aHeroClass = databaseHolder.CLASSES_MAP.get(id);
            if (aHeroClass != null) {
                className = aHeroClass.getName();
                if (!className.equals(classNameFromBackup)) {
                    DatabaseManager.dbCheckup();
                }
            } else {
                className = classNameFromBackup;
                DatabaseManager.dbCheckup();
            }
            String classLevel = classes.split("=")[1];
            heroClassesOut.append(className).append(" ").append(classLevel).append(" ");
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
    @ColumnInfo(name = DBColumnNames.HERO_ALIGNMENT_ID_COLUMN_NAME)
    private Integer heroAlignmentId;

    public String getAlignmentStringFromId(DatabaseHolder databaseHolder) {
        return String.valueOf(heroAlignmentId); //todo get Alignment from this id
    }

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_DEITY_ID_COLUMN_NAME)
    private Integer heroDeityId;

    public String getDeityStringFromId(DatabaseHolder databaseHolder) {
        return String.valueOf(heroDeityId); //todo get Deity from this id
    }

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.HERO_SIZE_COLUMN_NAME)
    private Integer heroSizeId;

    public String getSizeStringFromId(DatabaseHolder databaseHolder) {
        return String.valueOf(heroSizeId); //todo get Size from this id
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
