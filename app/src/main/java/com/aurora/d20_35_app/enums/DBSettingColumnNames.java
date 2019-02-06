package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.models.Translations;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Races;
import com.aurora.d20_35_app.models.usables.Armour;
import com.aurora.d20_35_app.models.userData.HeroDescription;
import com.aurora.d20_35_app.models.userData.HeroPlayer;

import lombok.Getter;

public enum DBSettingColumnNames implements DBColumnNamesMethods<DBSettingColumnNames, Item> {


    ////////////////////////////////////////////////////////////////
    //////////////////////////  Databases  ////////////////////////

    /**
     * Data more or less depending on setting
     */
    /////////////////////////////////////////////////////////////
    //////////////////////////  ITEM  //////////////////////////
    COL_ITEM_ID(Item.ITEM_ID_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setItemID(Integer.parseInt(data));
        }
    },
    COL_ITEM_NAME(Item.ITEM_NAME_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setName(data);
        }
    },
    COL_SOURCE(Item.SOURCE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setSource(data);
        }
    },
    COL_ID_AS_NAME_BACKUP(Item.ID_AS_NAME_BACKUP_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            item.setIdAsNameBackup(data);
        }
    },

    //////////////////////////////////////////////////////////////
    //////////////////////////  ARMOUR  //////////////////////////
    COL_ARMOUR_PRICE(Armour.ARMOUR_PRICE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourPrice(data);
        }
    },
    COL_ARMOUR_DEFLECTION(Armour.ARMOUR_DEFLECTION_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourDeflection(data);
        }
    },
    ColArmourMaxDexterityBonus(Armour.ARMOUR_MAX_DEXTERITY_BONUS_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMaxDexterityBonus(data);
        }
    },
    COL_ARMOUR_PENALTY(Armour.ARMOUR_PENALTY_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourPenalty(data);
        }
    },
    COL_ARMOUR_ARCANE_FAILURE(Armour.ARMOUR_ARCANE_FAILURE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourArcaneFailure(data);
        }
    },
    COL_ARMOUR_MAX_SPEED(Armour.ARMOUR_MAX_SPEED_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMaxSpeed(data);
        }
    },
    COL_ARMOUR_WEIGHT(Armour.ARMOUR_WEIGHT_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourWeight(data);
        }
    },
    COL_ARMOUR_SPECIAL_PROPERTIES(Armour.ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourSpecialProperties(data);
        }
    },
    COL_ARMOUR_MATERIAL(Armour.ARMOUR_MATERIAL_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Armour) item).setArmourMaterial(data);
        }
    },
    COL_ARMOUR_MAGIC_IMPROVEMENTS(Armour.ARMOUR_MAGIC_IMPROVEMENTS_COLUMN_NAME, false) {
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


    ///////////////////////////////////////////////////////////////
    ////////////////////////  HERO PLAYER /////////////////////////

    //////////////////////////////////////////////////////////////////
    //////////////////////  HERO DESCRIPTION  ///////////////////////
    COL_HERO_PLAYER(HeroDescription.HERO_PLAYER_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroPlayer(data);
        }
    },

    COL_HERO_CLASS_AND_LEVEL(HeroDescription.HERO_CLASS_AND_LEVEL_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroClassAndLevel(data);
        }
    },

    COL_HERO_RACE(HeroDescription.HERO_RACE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroRace(data);
        }
    },
    COL_HERO_ALIGNMENT(HeroDescription.HERO_ALIGNMENT_ID_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroAlignmentId(Integer.parseInt(data));
        }
    },
    COL_HERO_DEITY(HeroDescription.HERO_DEITY_ID_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroDeityId(Integer.parseInt(data));
        }
    },
    COL_HERO_SIZE(HeroDescription.HERO_SIZE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroSizeId(Integer.parseInt(data));
        }
    },
    COL_HERO_AGE(HeroDescription.HERO_AGE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroAge(Integer.parseInt(data));
        }
    },
    COL_HERO_GENDER(HeroDescription.HERO_GENDER_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroGender(data);
        }
    },
    COL_HERO_HEIGHT(HeroDescription.HERO_HEIGHT_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroHeight(data);
        }
    },
    COL_HERO_WEIGHT(HeroDescription.HERO_WEIGHT_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroWeight(data);
        }
    },
    COL_HERO_EYES(HeroDescription.HERO_EYES_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroEyes(data);
        }
    },
    COL_HERO_HAIR(HeroDescription.HERO_HAIR_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroHair(data);
        }
    },
    COL_HERO_SKIN(HeroDescription.HERO_SKIN_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((HeroPlayer) item).getHeroDescription().setHeroSkin(data);
        }
    },


    /////////////////////////////////////////////////////////////////
    //////////////////////////  MONSTERS  //////////////////////////

    //////////////////////////////////////////////////////////////
    //////////////////////////  RACES  //////////////////////////
    COL_RACE_DESCRIPTION(Races.RACE_DESCRIPTION_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceDescription(data);
        }
    },
    COL_RACE_ATTRIBUTE_MODIFIERS(Races.RACE_ATTRIBUTE_MODIFIERS_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceAttributeModifiers(data);
        }
    },
    COL_RACE_SIZE(Races.RACE_SIZE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceSize(data);
        }
    },
    COL_RACE_SPEED(Races.RACE_SPEED_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceSpeed(data);
        }
    },
    COL_RACE_FEATS(Races.RACE_FEATS_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceFeats(data);
        }
    },
    COL_RACE_SKILLS(Races.RACE_SKILLS_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceSkills(data);
        }
    },
    COL_RACE_LANGUAGES(Races.RACE_LANGUAGES_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Races) item).setRaceLanguages(data);
        }
    },
    COL_FAVOURITE_CLASS(Races.RACE_FAVOURITE_CLASS_COLUMN_NAME, false) {
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

    //////////////////////////////////////////////////////////////
    //////////////////////////  DEITIES  //////////////////////////

    ////////////////////////////////////////////////////////////////
    //////////////////////////  TRANSLATIONS  //////////////////////
    COL_CATEGORY(Translations.CATEGORY_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Translations) item).setCategory(data);
        }
    },
    COL_LANGUAGE(Translations.LANGUAGE_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Translations) item).setLanguage(data);
        }
    },
    COL_TRANSLATION(Translations.TRANS_COLUMN_NAME, false) {
        @Override
        public void setParameter(Item item, String data) {
            ((Translations) item).setTrans(data);
        }
    };

    @Getter
    private String columnName;

    @Getter
    private Boolean columnIsUsed;

    @Override
    public void setColumnIsUsed(Boolean columnIsUsed) {
        this.columnIsUsed = columnIsUsed;
    }

    DBSettingColumnNames(String columnName, boolean colBool) {
        this.columnName = columnName;
        this.columnIsUsed = colBool;
    }

    @Override
    public String toString() {
        return this.columnName;
    }

}
