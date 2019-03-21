package com.aurora.core.helper;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import com.aurora.core.models.helpers.Item;
import java.util.List;


public interface BaseDAO<T> {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
//or replace?
  long insert(T object);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
//or replace?
  List<Long> insertAll(List<T> objects);

  @Update
  void update(T object);

  @Delete
  void delete(T object);

  int countAllItems();

  List<Integer> getAllIds();

  List<String> getAllNames();

  List<String> getAllSources();

  List<T> getAllObjectsAsMergedObjectItem();

  List<T> getAllObjectsAsObject();

  List<Item> getAllObjectsAsItem();

  List<T> getObjectsWithIdsAsMergedObjectItem(List<Integer> Ids);

  List<T> getObjectsWithIdsAsObject(List<Integer> Ids);

  List<Item> getObjectsWithIdsAsItem(List<Integer> Ids);

  List<T> getObjectsWithSource(String source);

  T getObjectWithId(int id);

  T getObjectWithName(String name);

  void deleteAll();
}

