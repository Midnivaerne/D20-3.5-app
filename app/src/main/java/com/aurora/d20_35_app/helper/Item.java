package com.aurora.d20_35_app.helper;

import com.aurora.d20_35_app.models.Armour;
import com.aurora.d20_35_app.models.Classes;
import com.aurora.d20_35_app.models.Equipment;
import com.aurora.d20_35_app.models.Feats;
import com.aurora.d20_35_app.models.Hero;
import com.aurora.d20_35_app.models.Monsters;
import com.aurora.d20_35_app.models.RaceTemplates;
import com.aurora.d20_35_app.models.Races;
import com.aurora.d20_35_app.models.Skills;
import com.aurora.d20_35_app.models.Spells;
import com.aurora.d20_35_app.models.Weapons;
import com.aurora.d20_35_app.enums.ItemType;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Item {

    @Getter
    @Setter
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "Item_ID")
    private int itemID;

    @Getter
    @Setter
    @ColumnInfo(name = "Name")
    private String name;

    @Getter
    @Setter
    @ColumnInfo(name = "Source")
    private String source;

    @Getter
    @Setter
    private String content;
    public String details;


    public Item(int itemID, String content, String details) {
        this.itemID = itemID;
        this.content = content;
        this.details = details;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Races;
    }

    /**
     * @param itemType
     * @return
     */
    public Item createAnItem(ItemType itemType) {
        switch (itemType) {
            case Races:
                return new Races();
            case Classes:
                return new Classes();
            case Skills:
                return new Skills();
            case Feats:
                return new Feats();
            case Weapons:
                return new Weapons();
            case Armour:
                return new Armour();
            case Equipment:
                return new Equipment();
            case Spells:
                return new Spells();
            case Monsters:
                return new Monsters();
            case RaceTemplates:
                return new RaceTemplates();
            case Hero:
                return new Hero();
        }
        throw new IllegalArgumentException("ItemType" + itemType + " is not recognized.");
    }

}
