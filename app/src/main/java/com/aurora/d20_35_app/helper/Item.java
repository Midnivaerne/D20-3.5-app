package com.aurora.d20_35_app.helper;

import com.aurora.d20_35_app.enums.ItemType;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Data
public class Item {

    @Ignore
    public static final String itemIdColumnName = "Item_ID";
    @Ignore
    public static final String nameColumnName = "Name";
    @Ignore
    public static final String sourceColumnName = "Source";

    @Getter
    @Setter
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = itemIdColumnName)
    private int itemID;

    @Getter
    @Setter
    @ColumnInfo(name = nameColumnName)
    private String name;

    @Getter
    @Setter
    @ColumnInfo(name = sourceColumnName)
    private String source;

    @Getter
    @Setter
    @Ignore
    private String content;
    @Ignore
    public String details;

    @Ignore
    public Item() {
    }

    public Item(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public Item clone() {
        return new Item(name, source);
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
