package com.aurora.core.database.models.userdata;

import static com.aurora.core.database.DbColumnNames.HERO_PARENT_HERO_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_WEAPON_MATERIAL_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_WEAPON_PARENT_WEAPON_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_WEAPON_SELECTED_AMMO_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.HERO_WEAPON_WEAPON_SPECIFICS_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.ITEM_ID_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.HERO_WEAPONS;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.HeroChild;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.settingspecific.MaterialTypes;
import com.aurora.core.database.models.typehelpers.ItemType;
import com.aurora.core.database.models.usables.WeaponSpecifics;
import com.aurora.core.database.models.usables.Weapons;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = HERO_WEAPONS, inheritSuperIndices = true,
    indices = {@Index(SOURCE_COLUMN_NAME), @Index(HERO_PARENT_HERO_ID_COLUMN_NAME), @Index(HERO_WEAPON_PARENT_WEAPON_ID_COLUMN_NAME)},
    foreignKeys = {
        @ForeignKey(entity = Databases.class,
            parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = HeroPlayer.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_PARENT_HERO_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Weapons.class,
            parentColumns = ITEM_ID_COLUMN_NAME, childColumns = HERO_WEAPON_PARENT_WEAPON_ID_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class HeroWeapons extends Item implements HeroChild {

  @ColumnInfo(name = HERO_PARENT_HERO_ID_COLUMN_NAME)
  private Integer heroParentHeroId;

  @ColumnInfo(name = HERO_WEAPON_PARENT_WEAPON_ID_COLUMN_NAME)
  private Integer weaponId;

  @Ignore
  private Weapons weapon;

  @ColumnInfo(name = HERO_WEAPON_WEAPON_SPECIFICS_COLUMN_NAME)
  private String weaponSpecificIds;

  @ColumnInfo(name = HERO_WEAPON_SELECTED_AMMO_ID_COLUMN_NAME)
  private Integer selectedAmmoId;

  @Ignore
  private HeroWeapons selectedAmmo;

  @ColumnInfo(name = HERO_WEAPON_MATERIAL_ID_COLUMN_NAME)
  private Integer weaponMaterialId;

  @Ignore
  private MaterialTypes weaponMaterial;

  @Ignore
  private List<WeaponSpecifics> weaponSpecificsList;

  public HeroWeapons() {
    super();
  }

  public HeroWeapons(Map<ItemType, Map<Integer, String>> backupNames) {
    super();
    this.setBackupNames(backupNames);
  }

  public HeroWeapons(String name,
      String source,
      String idAsNameBackup) {
    new HeroWeapons(name, source, idAsNameBackup, null, null, null, null);
  }

  public HeroWeapons(String name,
      String source,
      String idAsNameBackup,
      Integer heroParentHeroId,
      Integer weaponId,
      String weaponSpecificIds,
      Integer selectedAmmoId) {
    super(name, source, idAsNameBackup);
    this.setHeroParentHeroId(heroParentHeroId);
    this.setWeaponId(weaponId);
    this.setWeaponSpecificIds(weaponSpecificIds);
    this.setSelectedAmmoId(selectedAmmoId);
  }

  @Ignore
  public HeroWeapons(HeroWeapons source) {
    new HeroWeapons(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getHeroParentHeroId(),
        source.getWeaponId(),
        source.getWeaponSpecificIds(),
        source.getSelectedAmmoId()
    );
  }

  @Ignore
  HeroWeapons generateAll(DatabaseHolder databaseHolder) {
    setWeapon(databaseHolder.weaponsMap.get(getWeaponId()));
    getWeapon().generateAll(databaseHolder);
    if (getWeaponMaterialId() != null) {
      setWeaponMaterial(databaseHolder.materialTypesMap.get(getWeaponMaterialId()));
    }
    if (getWeaponSpecificIds() != null && getWeaponSpecificIds() != "") {
      for (String weaponSpecificId : weaponSpecificIds.split(",")) {
        weaponSpecificsList.add(databaseHolder.weaponSpecificsMap.get(Integer.valueOf(weaponSpecificId)));
      }
    }
    return this;
  }
}
