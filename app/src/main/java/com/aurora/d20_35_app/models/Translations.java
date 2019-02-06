package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.database.DBColumnNames;

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
@Entity(tableName = "Translations", inheritSuperIndices = true,
        indices = {@Index(value = {"Source"})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source", onDelete = ForeignKey.CASCADE))
public class Translations extends Item {

    @Ignore
    public Translations() {
        super();
    }

    public Translations(String name,
                        String source,
                        String idAsNameBackup,
                        String category,
                        String language,
                        String trans) {
        super(name, source, idAsNameBackup);
        this.category = category;
        this.language = language;
        this.trans = trans;
    }

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.CATEGORY_COLUMN_NAME)
    private String category;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.LANGUAGE_COLUMN_NAME)
    private String language;

    @Getter
    @Setter
    @ColumnInfo(name = DBColumnNames.TRANS_COLUMN_NAME)
    private String trans;

    public Translations clone() {
        return new Translations(
                getName(),
                getSource(),
                getIdAsNameBackup(),
                getCategory(),
                getLanguage(),
                getTrans());
    }

}
