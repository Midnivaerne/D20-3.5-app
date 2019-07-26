package com.aurora.core.database.models;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.TRANSLATIONS;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.DbColumnNames;
import com.aurora.core.database.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = TRANSLATIONS, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Translations extends Item {

  @ColumnInfo(name = DbColumnNames.CATEGORY_COLUMN_NAME)
  private String category;

  @ColumnInfo(name = DbColumnNames.LANGUAGE_COLUMN_NAME)
  private String language;

  @ColumnInfo(name = DbColumnNames.TRANS_COLUMN_NAME)
  private String trans;

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

  public Translations(Translations source) {
    new Translations(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getCategory(),
        source.getLanguage(),
        source.getTrans());
  }

}
