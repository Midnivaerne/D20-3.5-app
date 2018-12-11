package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(tableName = "Translations", inheritSuperIndices = true)
public class Translations extends Item {

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
}
