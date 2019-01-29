package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

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
    public static final String categoryColumnName = "Category";
    @Ignore
    public static final String languageColumnName = "Language";
    @Ignore
    public static final String transColumnName = "Translation";

    @Getter
    @Setter
    @ColumnInfo(name = categoryColumnName)
    private String category;

    @Getter
    @Setter
    @ColumnInfo(name = languageColumnName)
    private String language;

    @Getter
    @Setter
    @ColumnInfo(name = transColumnName)
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
