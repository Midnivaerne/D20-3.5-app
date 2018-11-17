package com.aurora.d20_35_app.enums;

public enum ItemType {
    /**
     * Races
     */
    Races("Races"),
    /**
     * Classes
     */
    Classes("Classes"),
    /**
     * Skills
     */
    Skills("Skills"),
    /**
     * Feats
     */
    Feats("Feats"),
    /**
     * Weapons
     */
    Weapons("Weapons"),
    /**
     * Armour
     */
    Armour("Armour"),
    /**
     * Equipment
     */
    Equipment("Equipment"),
    /**
     * Spells
     */
    Spells("Spells"),
    /**
     * Monsters
     */
    Monsters("Monsters"),
    /**
     * RaceTemplates
     */
    RaceTemplates("RaceTemplates"),
    /**
     * Hero
     */
    Hero("Hero");

    private String itemType;

    ItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return this.itemType;
    }

}
