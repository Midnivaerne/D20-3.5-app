package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.models.Armour;
import com.aurora.d20_35_app.models.Races;
import com.aurora.d20_35_app.models.Translations;

import lombok.Getter;
import lombok.Setter;

public enum DBColumnNames {

    //////////////////////////  ITEM  //////////////////////////
    ColItemID(Item.itemIdColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setItemID(Integer.parseInt(data));
        }
    },
    ColName(Item.nameColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setName(data);
        }
    },
    ColSource(Item.sourceColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setSource(data);
        }
    },
    ////////////////////////////////////////////////////////////////
    //////////////////////////  DATABASES  ////////////////////////

    //////////////////////////////////////////////////////////////
    //////////////////////////  ARMOUR  //////////////////////////
    ColArmourPrice(Armour.armourPriceColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourPrice(data);
        }
    },
    ColArmourDeflection(Armour.armourDeflectionColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourDeflection(data);
        }
    },
    ColArmourMaxDexterityBonus(Armour.armourMaxDexterityBonusColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMaxDexterityBonus(data);
        }
    },
    ColArmourPenalty(Armour.armourPenaltyColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourPenalty(data);
        }
    },
    ColArmourArcaneFailure(Armour.armourArcaneFailureColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourArcaneFailure(data);
        }
    },
    ColArmourMaxSpeed(Armour.armourMaxSpeedColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMaxSpeed(data);
        }
    },
    ColArmourWeight(Armour.armourWeightColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourWeight(data);
        }
    },
    ColArmourSpecialProperties(Armour.armourSpecialPropertiesColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourSpecialProperties(data);
        }
    },
    ColArmourMaterial(Armour.armourMaterialColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMaterial(data);
        }
    },
    ColArmourMagicImprovements(Armour.armourMagicImprovementsColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMagicImprovements(data);
        }
    },
    ////////////////////////////////////////////////////////////////
    //////////////////////////  CLASSES  //////////////////////////

    //////////////////////////////////////////////////////////////////
    //////////////////////////  EQUIPMENT  //////////////////////////

    //////////////////////////////////////////////////////////////
    //////////////////////////  FEATS  //////////////////////////

    /////////////////////////////////////////////////////////////
    //////////////////////////  HERO  //////////////////////////

    /////////////////////////////////////////////////////////////////
    //////////////////////////  MONSTERS  //////////////////////////

    //////////////////////////////////////////////////////////////
    //////////////////////////  RACES  //////////////////////////
    ColRaceDescription(Races.raceDescriptionColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceDescription(data);
        }
    },
    ColRaceAttributeModifiers(Races.raceAttributeModifiersColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceAttributeModifiers(data);
        }
    },
    ColRaceSize(Races.raceSizeColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceSize(data);
        }
    },
    ColRaceSpeed(Races.raceSpeedColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceSpeed(data);
        }
    },
    ColRaceFeats(Races.raceFeatsColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceFeats(data);
        }
    },
    ColRaceSkills(Races.raceSkillsColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceSkills(data);
        }
    },
    ColRaceLanguages(Races.raceLanguagesColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceLanguages(data);
        }
    },
    ColFavouriteClass(Races.favouriteClassColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setFavouriteClass(data);
        }
    },
    ///////////////////////////////////////////////////////////////////////
    //////////////////////////  RACE TEMPLATES  //////////////////////////

    ///////////////////////////////////////////////////////////////
    //////////////////////////  SKILLS  //////////////////////////

    ///////////////////////////////////////////////////////////////
    //////////////////////////  SPELLS  //////////////////////////

    ////////////////////////////////////////////////////////////////
    //////////////////////////  WEAPONS  //////////////////////////

    ////////////////////////////////////////////////////////////////
    //////////////////////////  TRANSLATIONS  //////////////////////
    ColCategory(Translations.categoryColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Translations) item).setCategory(data);
        }
    },
    ColLanguage(Translations.languageColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Translations) item).setLanguage(data);
        }
    },
    ColTranslation(Translations.transColumnName, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Translations) item).setTrans(data);
        }
    };

    private String columnName;

    @Getter
    @Setter
    private Boolean columnIsUsed;

    DBColumnNames(String columnName, boolean colBool) {
        this.columnName = columnName;
        this.columnIsUsed = colBool;
    }

    @Override
    public String toString() {
        return this.columnName;
    }

    public static DBColumnNames fromString(String name) {
        for (DBColumnNames b : DBColumnNames.values()) {
            if (b.columnName.equals(name)) {
                return b;
            }
        }
        return null;
    }

    public static boolean contains(String name) {
        for (DBColumnNames it : DBColumnNames.values()) {
            if (it.toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public abstract void setParameter(Item item, String data);
}
