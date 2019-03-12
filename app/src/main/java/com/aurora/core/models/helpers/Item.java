package com.aurora.core.models.helpers;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.aurora.core.database.DBColumnNames;
import com.aurora.core.models.typeHelpers.ItemType;
import com.aurora.core.utils.CustomStringParsers;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Data
public class Item implements CoreHelper {

  @Getter
  @Setter
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = DBColumnNames.ITEM_ID_COLUMN_NAME)
  private Integer itemID;

  @Getter
  @Setter
  @ColumnInfo(name = DBColumnNames.ITEM_NAME_COLUMN_NAME)
  private String name;

  @Getter
  @Setter
  @ColumnInfo(name = DBColumnNames.SOURCE_COLUMN_NAME)
  private String source;

  @Getter
  @Setter
  @ColumnInfo(name = DBColumnNames.ID_AS_NAME_BACKUP_COLUMN_NAME)
  private String idAsNameBackup;

  @Getter
  @Setter
  @Ignore
  private String content;
  @Ignore
  public String details;

  @Getter
  @Setter
  @Ignore
  private Map<ItemType, Map<Integer, String>> backupNames;

  @Ignore
  public Item() {
  }

  public Item(String name, String source, String idAsNameBackup) {
    this.name = name;
    this.source = source;
    this.idAsNameBackup = idAsNameBackup;
    backupNamesFromIdCreator();
  }

  @Ignore
  public void backupNamesFromIdCreator() {
    if (idAsNameBackup != null && !"".equals(idAsNameBackup)) {
      backupNames = CustomStringParsers.StringWithCommaAndBracketsToMap(idAsNameBackup);
    }
  }

  public Item clone() {
    return new Item(name, source, idAsNameBackup);
  }

  protected boolean canEqual(Object other) {
    return other instanceof Item;
  }

  /**
   * @param itemType
   * @return
   */
  public Item createAnItem(ItemType itemType) {
    itemType.getNewObject();
    throw new IllegalArgumentException("ItemType" + itemType + " is not recognized.");
  }

}
