package com.aurora.d20_35_app.models.userData;

import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.utils.CustomStringParsers;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ABILITY_SCORE_CHA_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ABILITY_SCORE_CON_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ABILITY_SCORE_DEX_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ABILITY_SCORE_INT_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ABILITY_SCORE_STR_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_ABILITY_SCORE_WIS_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_HIT_POINTS_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.HERO_PARENT_ITEM_ID_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.HERO_STATISTICS;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = HERO_STATISTICS, inheritSuperIndices = true,
        indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_ITEM_ID_COLUMN_NAME)},
        foreignKeys = {
                @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = HeroPlayer.class, parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_ITEM_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroStatistics extends Item {

    @Ignore
    public HeroStatistics() {
        super();
    }

    public HeroStatistics(String name,
                          String source,
                          String idAsNameBackup,
                          Integer heroParentItemID,
                          String heroHitPoints,
                          Integer heroAbilityScoreStr,
                          Integer heroAbilityScoreDex,
                          Integer heroAbilityScoreCon,
                          Integer heroAbilityScoreInt,
                          Integer heroAbilityScoreWis,
                          Integer heroAbilityScoreCha) {
        super(name, source, idAsNameBackup);
        this.heroParentItemID = heroParentItemID;
        this.heroHitPoints = heroHitPoints;
        this.heroAbilityScoreStr = heroAbilityScoreStr;
        this.heroAbilityScoreDex = heroAbilityScoreDex;
        this.heroAbilityScoreCon = heroAbilityScoreCon;
        this.heroAbilityScoreInt = heroAbilityScoreInt;
        this.heroAbilityScoreWis = heroAbilityScoreWis;
        this.heroAbilityScoreCha = heroAbilityScoreCha;
    }

    @Getter
    @Setter
    @ColumnInfo(name = HERO_PARENT_ITEM_ID_COLUMN_NAME)
    private Integer heroParentItemID;

    @Getter
    @Setter
    @ColumnInfo(name = HERO_HIT_POINTS_COLUMN_NAME)
    private String heroHitPoints;

    public String getHeroHitPointsStringFromList() {
        return CustomStringParsers.StringWithCommaToSum(heroHitPoints);
    }

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ABILITY_SCORE_STR_COLUMN_NAME)
    private Integer heroAbilityScoreStr;

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ABILITY_SCORE_DEX_COLUMN_NAME)
    private Integer heroAbilityScoreDex;

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ABILITY_SCORE_CON_COLUMN_NAME)
    private Integer heroAbilityScoreCon;

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ABILITY_SCORE_INT_COLUMN_NAME)
    private Integer heroAbilityScoreInt;

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ABILITY_SCORE_WIS_COLUMN_NAME)
    private Integer heroAbilityScoreWis;

    @Getter
    @Setter
    @ColumnInfo(name = HERO_ABILITY_SCORE_CHA_COLUMN_NAME)
    private Integer heroAbilityScoreCha;

    public static int getStatisticModifier(int stat) {
        return ((stat / 2) - 5);
    }

    public HeroStatistics clone() {
        return new HeroStatistics(
                getName(),
                getSource(),
                getIdAsNameBackup(),
                getHeroParentItemID(),
                getHeroHitPoints(),
                getHeroAbilityScoreStr(),
                getHeroAbilityScoreDex(),
                getHeroAbilityScoreCon(),
                getHeroAbilityScoreInt(),
                getHeroAbilityScoreWis(),
                getHeroAbilityScoreCha());
    }
}
