package com.aurora.core.dao.settingSpecific;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import com.aurora.core.helper.BaseDAO;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingSpecific.HeroNPC;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class HeroNPCDAO implements BaseDAO<HeroNPC> {

  @Query("SELECT COUNT(*) from HeroNPC")
  public abstract int countItems();

  @Query("SELECT Item_ID FROM HeroNPC")
  public abstract List<Integer> getIds();

  @Query("SELECT Name FROM HeroNPC")
  public abstract List<String> getNames();

  @Query("SELECT DISTINCT Source FROM HeroNPC")
  public abstract List<String> getSources();

  @Transaction
  public List<HeroNPC> getItemWithSuperFields() {
    ArrayList<HeroNPC> result = new ArrayList<>(getItems());
    ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
      result.get(i).setSource(resultItem.get(i).getSource());
      result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
    }
    return result;
  }

  @Query("SELECT * FROM HeroNPC")
  public abstract List<HeroNPC> getItems();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM HeroNPC")
  public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

  @Query("SELECT * FROM HeroNPC WHERE Source == :source")
  public abstract List<HeroNPC> getItemsWithSource(String source);

  @Query("SELECT * FROM HeroNPC WHERE Item_ID == :itemID")
  public abstract HeroNPC getItemWithId(int itemID);

  @Query("SELECT * FROM HeroNPC WHERE Name == :name")
  public abstract HeroNPC getItemWithName(String name);

  @Query("DELETE FROM HeroNPC")
  public abstract void deleteAll();
}
