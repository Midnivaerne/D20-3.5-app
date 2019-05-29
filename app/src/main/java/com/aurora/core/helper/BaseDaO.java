package com.aurora.core.helper;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.models.helpers.CoreHelper;
import com.aurora.core.models.helpers.Item;

public abstract class BaseDaO<T extends CoreHelper> {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public abstract long insert(T object);//todo or replace OnConflictStrategy?

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public abstract List<Long> insertAll(List<T> objects);//todo or replace OnConflictStrategy?

  @Update
  public abstract void update(T object);

  @Delete
  public abstract void delete(T object);

  public abstract int countAllItems();

  public abstract List<Integer> getAllIds();

  public abstract List<String> getAllNames();

  public abstract List<String> getAllSources();

  @Transaction
  public Map<Integer, ?> getAllObjectsAsMergedObjectItem() {
    Map<Integer, T> result = new HashMap<>();
    ArrayList<T> resultT = new ArrayList<>(getAllObjectsAsObject());
    ArrayList<Item> resultItem = new ArrayList<>(getAllObjectsAsItem());
    for (int i = 0; i < resultT.size(); i++) {
      ((Item) resultT.get(i)).setItemID(resultItem.get(i).getItemID());
      ((Item) resultT.get(i)).setName(resultItem.get(i).getName());
      ((Item) resultT.get(i)).setSource(resultItem.get(i).getSource());
      ((Item) resultT.get(i)).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
      result.put(resultItem.get(i).getItemID(), resultT.get(i));
    }
    return result;
  }

  public abstract List<T> getAllObjectsAsObject();

  public abstract List<Item> getAllObjectsAsItem();

  @Transaction
  public Map<Integer, ?> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids) {
    Map<Integer, T> result = new HashMap<>();
    ArrayList<T> resultT = new ArrayList<>(getObjectsWithIdsAsObject(ids));
    ArrayList<Item> resultItem = new ArrayList<>(getObjectsWithIdsAsItem(ids));
    for (int i = 0; i < resultT.size(); i++) {
      ((Item) resultT.get(i)).setItemID(resultItem.get(i).getItemID());
      ((Item) resultT.get(i)).setName(resultItem.get(i).getName());
      ((Item) resultT.get(i)).setSource(resultItem.get(i).getSource());
      ((Item) resultT.get(i)).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
      result.put(resultItem.get(i).getItemID(), resultT.get(i));
    }
    return result;
  }

  public abstract List<T> getObjectsWithIdsAsObject(List<Integer> ids);

  public abstract List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  public abstract List<T> getObjectsWithSource(String source);

  public abstract T getObjectWithId(int id);

  public abstract T getObjectWithName(String name);

  public abstract void deleteAll();
}

