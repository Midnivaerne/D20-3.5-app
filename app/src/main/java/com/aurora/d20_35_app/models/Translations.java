package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.models.helpers.Item;

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

    @Ignore
    public static final String CATEGORY_COLUMN_NAME = "Category";
    @Ignore
    public static final String LANGUAGE_COLUMN_NAME = "Language";
    @Ignore
    public static final String TRANS_COLUMN_NAME = "Translation";

    @Getter
    @Setter
    @ColumnInfo(name = CATEGORY_COLUMN_NAME)
    private String category;

    @Getter
    @Setter
    @ColumnInfo(name = LANGUAGE_COLUMN_NAME)
    private String language;

    @Getter
    @Setter
    @ColumnInfo(name = TRANS_COLUMN_NAME)
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
