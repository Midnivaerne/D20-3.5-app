package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.userdata.HeroEquipment;

@Dao
public abstract class HeroEquipmentDaO extends BaseDaO<HeroEquipment> {

  @Query("SELECT COUNT(*) from HeroEquipment")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroEquipment")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroEquipment")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroEquipment")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroEquipment")
  public abstract List<HeroEquipment> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroEquipment")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroEquipment WHERE Item_ID IN (:ids)")
  public abstract List<HeroEquipment> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroEquipment WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroEquipment WHERE Source == :source")
  public abstract List<HeroEquipment> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroEquipment WHERE Item_ID == :itemID")
  public abstract HeroEquipment getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroEquipment WHERE Name == :name")
  public abstract HeroEquipment getObjectWithName(String name);

  @Query("SELECT * FROM HeroEquipment WHERE Parent_Hero_Id == :parentHeroId")
  public abstract List<HeroEquipment> getEuipmentWithParentHero(Integer parentHeroId);

  @Query("DELETE FROM HeroEquipment")
  public abstract void deleteAll();
}
