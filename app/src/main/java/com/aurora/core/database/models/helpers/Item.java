package com.aurora.core.database.models.helpers;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import com.aurora.core.database.DbColumnNames;
import com.aurora.core.database.models.typehelpers.ItemType;
import com.aurora.core.utils.CustomStringParsers;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder(toBuilder = true)
public class Item implements CoreHelper {

  @Ignore
  public String details;

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = DbColumnNames.ITEM_ID_COLUMN_NAME)
  private Integer itemID;

  @ColumnInfo(name = DbColumnNames.ITEM_NAME_COLUMN_NAME)
  private String name;

  @ColumnInfo(name = DbColumnNames.SOURCE_COLUMN_NAME)
  private String source;

  @ColumnInfo(name = DbColumnNames.ID_AS_NAME_BACKUP_COLUMN_NAME)
  private String idAsNameBackup;

  @Ignore
  private String content;

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
      backupNames = CustomStringParsers.stringWithCommaAndBracketsToMap(idAsNameBackup);
    }
  }

  @Ignore
  public Item(Item sourceItem) {
    new Item(
        sourceItem.getName(),
        sourceItem.getSource(),
        sourceItem.getIdAsNameBackup());
  }

  protected boolean canEqual(Object other) {
    return other instanceof Item;
  }

  /**
   * Method that creates new item of selected type.
   */
  public Item createAnItem(ItemType itemType) {
    itemType.getNewObject();
    throw new IllegalArgumentException("ItemType" + itemType + " is not recognized.");
  }

  @Override
  public String showAllContentAsString() {
    return new StringBuilder().append("Item [")
        .append("details = ").append(details)
        .append(", itemID = ").append(getItemID())
        .append(", name = ").append(getName())
        .append(", source = ").append(source)
        .append(", idAsNameBackup = ").append(getIdAsNameBackup())
        .append(", content = ").append(getContent())
        .append(", backupNames = ").append(getBackupNames())
        .append("]").toString();
  }
}
