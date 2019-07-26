package com.aurora.core.database.dao.userdata;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.userdata.Hero;
import com.aurora.core.database.models.userdata.HeroPlayer;
import com.aurora.core.helper.BaseDaO;

@Dao
public abstract class HeroPlayerDaO extends BaseDaO<HeroPlayer> {

  @Query("SELECT COUNT(*) from HeroPlayer")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM HeroPlayer")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM HeroPlayer")
  public abstract List<String> getAllNames();

  @Query("SELECT DISTINCT Source FROM HeroPlayer")
  public abstract List<String> getAllSources();

  @Query("SELECT * FROM HeroPlayer")
  public abstract List<HeroPlayer> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroPlayer")
  public abstract List<Item> getAllObjectsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroPlayer WHERE Item_ID IN (:ids)")
  public abstract List<HeroPlayer> getObjectsWithIdsAsObject(List<Integer> ids);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroPlayer WHERE Item_ID IN (:ids)")
  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  @Query("SELECT * FROM HeroPlayer WHERE Source == :source")
  public abstract List<HeroPlayer> getObjectsWithSource(String source);

  @Query("SELECT * FROM HeroPlayer WHERE Item_ID == :itemID")
  public abstract HeroPlayer getObjectWithId(int itemID);

  @Query("SELECT * FROM HeroPlayer WHERE Name == :name")
  public abstract HeroPlayer getObjectWithName(String name);

  @Query("DELETE FROM HeroPlayer")
  public abstract void deleteAll();

  @Transaction
  public Map<Integer, HeroPlayer> getHeroPlayerObjectsWithHeroAndItemFields() {
    Map<Integer, HeroPlayer> result = new HashMap<>();
    ArrayList<HeroPlayer> resultHP = new ArrayList<>(getAllObjectsAsObject());
    System.out.println("1>>" + resultHP);//todo delete
    ArrayList<Hero> resultH = new ArrayList<>(getAllObjectsAsHero());
    System.out.println("2>>" + resultH);//todo delete
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < resultHP.size(); i++) {
      ((Item) resultHP.get(i)).setItemID(resultItem.get(i).getItemID());
      ((Item) resultHP.get(i)).setName(resultItem.get(i).getName());
      ((Item) resultHP.get(i)).setSource(resultItem.get(i).getSource());
      ((Item) resultHP.get(i)).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
      ((Hero) resultHP.get(i)).setRightHandHeldItemId(resultH.get(i).getRightHandHeldItemId());
      ((Hero) resultHP.get(i)).setLeftHandHeldItemId(resultH.get(i).getLeftHandHeldItemId());
      ((Hero) resultHP.get(i)).setWornItemId(resultH.get(i).getWornItemId());
      result.put(resultItem.get(i).getItemID(), resultHP.get(i));
      System.out.println("3>>" + resultHP.get(i));//todo delete
    }
    return result;
  }

  @Query("SELECT Item_ID,Name,Source,Id_As_Name_Backup,Right_Hand_Held_Item_Id,Left_Hand_Held_Item_Id,Worn_Item_Id FROM HeroPlayer")
  abstract List<Hero> getAllObjectsAsHero();

  @Transaction
  public List<Long> insertAllWithHeroUpdate(List<HeroPlayer> heroesPlayerList) {
    List<Long> insertIds = insertAll(heroesPlayerList);
    for (int i = 0; i < insertIds.size(); i++) {
      HeroPlayer heroPlayer = heroesPlayerList.get(i);
      updateHeroFields(Math.toIntExact(insertIds.get(i)), heroPlayer.getRightHandHeldItemId(), heroPlayer.getLeftHandHeldItemId(),
          heroPlayer.getWornItemId());
    }
    return insertIds;
  }

  @Query("UPDATE HeroPlayer SET Right_Hand_Held_Item_Id=:rightHandHeldItemId,Left_Hand_Held_Item_Id=:leftHandHeldItemId,Worn_Item_Id=:wornItemId WHERE Item_ID=:HeroId")
  abstract void updateHeroFields(Integer HeroId, Integer rightHandHeldItemId, Integer leftHandHeldItemId, Integer wornItemId);
}
