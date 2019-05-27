package com.aurora.core.database.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.constants.RulesCombat;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.helpers.Rules;

@Dao
public abstract class RulesCombatDaO implements BaseDaO<RulesCombat> {

  @Query("SELECT COUNT(*) from RulesCombat")
  public abstract int countAllItems();

  @Query("SELECT Item_ID FROM RulesCombat")
  public abstract List<Integer> getAllIds();

  @Query("SELECT Name FROM RulesCombat")
  public abstract List<String> getAllNames();

  @Override
  public List<String> getAllSources() {
    return null;
  }

  @Transaction
  public List<RulesCombat> getAllObjectsAsMergedObjectItem() {
    ArrayList<RulesCombat> result = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Rules> resultItem = new ArrayList<>(getItemsAsRules());
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
  }

  @Query("SELECT * FROM RulesCombat")
  public abstract List<RulesCombat> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM RulesCombat")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
  }

  @Transaction
  public List<RulesCombat> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    ArrayList<RulesCombat> result = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Rules> resultItem = new ArrayList<>(getObjectsWithIdsAsRules(ids));
    for (int i = 0; i < result.size(); i++) {
      result.get(i).setItemID(resultItem.get(i).getItemID());
      result.get(i).setName(resultItem.get(i).getName());
    }
    return result;
  }

  @Query("SELECT * FROM RulesCombat WHERE Item_ID IN (:ids)")
  public abstract List<RulesCombat> getObjectsWithIdsAsObject(List<Integer> ids);

  @Query("SELECT * FROM RulesCombat WHERE Item_ID IN (:ids)")
  public abstract List<Rules> getObjectsWithIdsAsRules(List<Integer> ids);

  @Override
  public List<Item> getObjectsWithIdsAsItem(List<Integer> ids) {
    return null;
  }

  @Override
  public List<RulesCombat> getObjectsWithSource(String source) {
    return null;
  }

  @Query("SELECT * FROM RulesCombat WHERE Item_ID == :itemID")
  public abstract RulesCombat getObjectWithId(int itemID);

  @Query("SELECT * FROM RulesCombat WHERE Name == :name")
  public abstract RulesCombat getObjectWithName(String name);

  @Query("DELETE FROM RulesCombat")
  public abstract void deleteAll();

}
