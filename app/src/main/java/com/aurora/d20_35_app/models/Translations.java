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
@Entity(tableName = "Translations")
public class Translations extends Item {

    @Ignore
    public static final String engTransColumnName = "EnglishTranslation";
    @Ignore
    public static final String polTransColumnName = "PolishTranslation";

    @Getter
    @Setter
    @ColumnInfo(name = engTransColumnName)
    private String engTrans;

    @Getter
    @Setter
    @ColumnInfo(name = polTransColumnName)
    private String polTrans;
}
