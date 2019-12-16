package com.aurora.core.database.dao.constants;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import java.util.List;

import com.aurora.core.helper.BaseDaO;
import com.aurora.core.database.models.constants.RulesCombat;
import com.aurora.core.database.models.helpers.Item;
import com.aurora.core.database.models.helpers.Rules;

@Dao
public abstract class RulesCombatDaO extends BaseDaO<RulesCombat> {

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

  @Query("SELECT * FROM RulesCombat")
  public abstract List<RulesCombat> getAllObjectsAsObject();

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM RulesCombat")
  public abstract List<Rules> getItemsAsRules(); // above doesn't show Item fields (but they are created/loaded)

  @Override
  public List<Item> getAllObjectsAsItem() {
    return null;
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
