package com.aurora.core.dao.userData;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.userData.HeroValues;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class HeroValuesDAO implements BaseDAO<HeroValues> {

  @Query("SELECT COUNT(*) from HeroValues")
  public abstract int countItems();

  @Query("SELECT Item_ID FROM HeroValues")
  public abstract List<Integer> getIds();

  @Query("SELECT Name FROM HeroValues")
  public abstract List<String> getNames();

  @Query("SELECT DISTINCT Source FROM HeroValues")
  public abstract List<String> getSources();

  @Transaction
  public List<HeroValues> getItemWithSuperFields() {
    ArrayList<HeroValues> result = new ArrayList<>(getItems());
    ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroValues")
  public abstract List<HeroValues> getItems();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroValues")
  public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroValues WHERE Source == :source")
  public abstract List<HeroValues> getItemsWithSource(String source);

  @Query("SELECT * FROM HeroValues WHERE Item_ID == :itemID")
  public abstract HeroValues getItemWithId(int itemID);

  @Query("SELECT * FROM HeroValues WHERE Name == :name")
  public abstract HeroValues getItemWithName(String name);

  @Query("DELETE FROM HeroValues")
  public abstract void deleteAll();

  @Transaction
  public List<HeroValues> getHeroStatisticsWithSuperFieldsWithParentIdIn(List<Integer> heroes_player_id_list) {
    ArrayList<HeroValues> result = new ArrayList<>(getHeroStatisticsWithParentIdIn(heroes_player_id_list));
    ArrayList<Item> resultItem = new ArrayList<>(getHeroStatisticsAsItemWithParentIdIn(heroes_player_id_list));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroValues WHERE Parent_Item_ID == :PatentId")
  public abstract HeroValues getHeroStatisticsWithParentId(Integer PatentId);

  @Query("SELECT * FROM HeroValues WHERE Parent_Item_ID IN (:heroes_player_id_list)")
  public abstract List<HeroValues> getHeroStatisticsWithParentIdIn(List<Integer> heroes_player_id_list);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroValues WHERE Parent_Item_ID IN (:heroes_player_id_list)")
  public abstract List<Item> getHeroStatisticsAsItemWithParentIdIn(List<Integer> heroes_player_id_list);
}
