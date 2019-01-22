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

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "HeroPlayer", inheritSuperIndices = true,
        indices = {@Index(value = "Source")},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source", onDelete = ForeignKey.CASCADE))
public class HeroPlayer extends Hero {

    @Ignore
    public HeroPlayer() {
        super();
        this.heroDescription = new HeroDescription();
    }

    public HeroPlayer(String name,
                      String source,
                      String idAsNameBackup) {
        new HeroPlayer(name, source, idAsNameBackup, new HeroDescription());
    }

    public HeroPlayer(String name,
                      String source,
                      String idAsNameBackup,
                      HeroDescription heroDescription) {
        super(name,
                source,
                idAsNameBackup);
        this.heroDescription = heroDescription.clone();
    }

    @Ignore
    @Getter
    @Setter
    @Embedded
    private HeroDescription heroDescription;

    public HeroPlayer clone() {
        if (getHeroDescription() != null) {
            return new HeroPlayer(
                    getName(),
                    getSource(),
                    getIdAsNameBackup(),
                    getHeroDescription());
        } else {
            return new HeroPlayer(
                    getName(),
                    getSource(),
                    getIdAsNameBackup());
        }
    }
}
