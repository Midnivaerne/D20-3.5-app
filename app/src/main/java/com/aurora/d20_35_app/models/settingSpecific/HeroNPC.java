package com.aurora.d20_35_app.models.settingSpecific;

import static com.aurora.d20_35_app.database.DBColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.d20_35_app.database.DBTableNames.HERO_NPC;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.d20_35_app.models.userData.Hero;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = HERO_NPC, inheritSuperIndices = true,
        indices = {@Index(value = SOURCE_COLUMN_NAME)},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class HeroNPC extends Hero {

    @Ignore
    public HeroNPC() {
        super();
    }

    public HeroNPC(String name,
                   String source,
                   String idAsNameBackup) {
        super(name,
                source,
                idAsNameBackup);
    }

    public HeroNPC clone() {
        return new HeroNPC(
                getName(),
                getSource(),
                getIdAsNameBackup());
    }
}
