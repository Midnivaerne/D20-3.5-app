package com.aurora.d20_35_app.models.userData;

import com.aurora.d20_35_app.models.Databases;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.HERO_PLAYER;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = HERO_PLAYER, inheritSuperIndices = true,
        indices = {@Index(value = SOURCE_COLUMN_NAME)},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class HeroPlayer extends Hero {

    @Ignore
    public HeroPlayer() {
        super();
        this.heroDescription = new HeroDescription();
    }

    public HeroPlayer(String name,
                      String source,
                      String idAsNameBackup) {
        new HeroPlayer(name, source, idAsNameBackup, null, null);
    }

    public HeroPlayer(String name,
                      String source,
                      String idAsNameBackup,
                      HeroStatistics heroStatistics,
                      HeroDescription heroDescription) {
        super(name, source, idAsNameBackup, heroStatistics);
        this.heroDescription = heroDescription == null ? new HeroDescription() : heroDescription.clone();
    }

    @Ignore
    @Getter
    @Setter
    @Embedded
    private HeroDescription heroDescription;

    public HeroPlayer clone() {
        return new HeroPlayer(
                getName(),
                getSource(),
                getIdAsNameBackup(),
                getHeroStatistics(),
                getHeroDescription());
    }
}
