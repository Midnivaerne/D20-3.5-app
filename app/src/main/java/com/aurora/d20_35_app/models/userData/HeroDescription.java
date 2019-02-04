package com.aurora.d20_35_app.models.userData;

import com.aurora.d20_35_app.enums.ItemType;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.utils.CustomStringParsers;
import com.aurora.d20_35_app.utils.database.DatabaseHolder;
import com.aurora.d20_35_app.utils.database.DatabaseManager;

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
        indices = {@Index("Source"), @Index("ParentItemID"), @Index("Alignment"), @Index("Deity"), @Index("Size")},
        foreignKeys = {
                @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = HeroPlayer.class, parentColumns = "Item_ID", childColumns = "ParentItemID", onDelete = ForeignKey.CASCADE),
                //todo uncomment when tables and data will be ready
                /**@ForeignKey(entity = Alignments.class, parentColumns = "Item_ID", childColumns = "Alignment"),
                 @ForeignKey(entity = Deities.class, parentColumns = "Item_ID", childColumns = "Deity"),
                 @ForeignKey(entity = Sizes.class, parentColumns = "Item_ID", childColumns = "Size")**/}
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
                           Integer heroAlignment,
                           Integer heroDeity,
                           Integer heroSize,
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
        this.heroAlignment = heroAlignment;
        this.heroDeity = heroDeity;
        this.heroSize = heroSize;
        this.heroAge = heroAge;
        this.heroGender = heroGender;
        this.heroHeight = heroHeight;
        this.heroWeight = heroWeight;
        this.heroEyes = heroEyes;
        this.heroHair = heroHair;
        this.heroSkin = heroSkin;
    }

    @Ignore
    public static final String heroParentItemIDColumnName = "ParentItemID";

    @Ignore
    public static final String heroPlayerColumnName = "Player";

    @Ignore
    public static final String heroClassAndLevelColumnName = "ClassAndLevel";

    @Ignore
    public static final String heroRaceColumnName = "Race";

    @Ignore
    public static final String heroAlignmentColumnName = "Alignment";

    @Ignore
    public static final String heroDeityColumnName = "Deity";

    @Ignore
    public static final String heroSizeColumnName = "Size";

    @Ignore
    public static final String heroAgeColumnName = "Age";

    @Ignore
    public static final String heroGenderColumnName = "Gender";

    @Ignore
    public static final String heroHeightColumnName = "Height";

    @Ignore
    public static final String heroWeightColumnName = "Weight";

    @Ignore
    public static final String heroEyesColumnName = "Eyes";

    @Ignore
    public static final String heroHairColumnName = "Hair";

    @Ignore
    public static final String heroSkinColumnName = "Skin";

    @Getter
    @Setter
    @ColumnInfo(name = heroParentItemIDColumnName)
    private Integer heroParentItemID;

    @Getter
    @Setter
    @ColumnInfo(name = heroPlayerColumnName)
    private String heroPlayer;

    @Getter
    @Setter
    @ColumnInfo(name = heroClassAndLevelColumnName)
    private String heroClassAndLevel;

    @Ignore
    public String getHeroClassAndLevelStringFromId(DatabaseHolder databaseHolder) {
        StringBuilder heroClassesOut = new StringBuilder();
        String[] heroClasses = CustomStringParsers.StringWithCommaToTable(heroClassAndLevel);
        for (String classes : heroClasses) {
            int id = Integer.parseInt((classes.split("=")[0]));
            String className;
            String classNameFromBackup = getBackupNames().get(ItemType.Classes).get(id);
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
    @ColumnInfo(name = heroRaceColumnName)
    private String heroRace;

    public String getRaceStringFromId(DatabaseHolder databaseHolder) {
        return heroRace; //todo get race (and template) from this id('s)
    }

    @Getter
    @Setter
    @ColumnInfo(name = heroAlignmentColumnName)
    private Integer heroAlignment;

    public String getAlignmentStringFromId(DatabaseHolder databaseHolder) {
        return String.valueOf(heroAlignment); //todo get Alignment from this id
    }

    @Getter
    @Setter
    @ColumnInfo(name = heroDeityColumnName)
    private Integer heroDeity;

    public String getDeityStringFromId(DatabaseHolder databaseHolder) {
        return String.valueOf(heroDeity); //todo get Deity from this id
    }

    @Getter
    @Setter
    @ColumnInfo(name = heroSizeColumnName)
    private Integer heroSize;

    public String getSizeStringFromId(DatabaseHolder databaseHolder) {
        return String.valueOf(heroSize); //todo get Size from this id
    }

    @Getter
    @Setter
    @ColumnInfo(name = heroAgeColumnName)
    private Integer heroAge;

    @Getter
    @Setter
    @ColumnInfo(name = heroGenderColumnName)
    private String heroGender;

    @Getter
    @Setter
    @ColumnInfo(name = heroHeightColumnName)
    private String heroHeight;

    @Getter
    @Setter
    @ColumnInfo(name = heroWeightColumnName)
    private String heroWeight;

    @Getter
    @Setter
    @ColumnInfo(name = heroEyesColumnName)
    private String heroEyes;

    @Getter
    @Setter
    @ColumnInfo(name = heroHairColumnName)
    private String heroHair;

    @Getter
    @Setter
    @ColumnInfo(name = heroSkinColumnName)
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
                getHeroAlignment(),
                getHeroDeity(),
                getHeroSize(),
                getHeroAge(),
                getHeroGender(),
                getHeroHeight(),
                getHeroWeight(),
                getHeroEyes(),
                getHeroHair(),
                getHeroSkin());
    }
}
