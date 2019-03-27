package com.aurora.core.models;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.TRANSLATIONS;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.aurora.core.database.DbColumnNames;
import com.aurora.core.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = TRANSLATIONS, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = @ForeignKey(entity = Databases.class,
        parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE))
public class Translations extends Item {

  @Getter
  @Setter
  @ColumnInfo(name = DbColumnNames.CATEGORY_COLUMN_NAME)
  private String category;
  @Getter
  @Setter
  @ColumnInfo(name = DbColumnNames.LANGUAGE_COLUMN_NAME)
  private String language;
  @Getter
  @Setter
  @ColumnInfo(name = DbColumnNames.TRANS_COLUMN_NAME)
  private String trans;

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
