package com.aurora.core.helper;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

import com.aurora.core.models.helpers.Item;


public interface BaseDaO<T> {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
//todo or replace?
  long insert(T object);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
//todo or replace?
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

  List<T> getObjectsWithIdsAsMergedObjectItem(List<Integer> ids);

  List<T> getObjectsWithIdsAsObject(List<Integer> ids);

  List<Item> getObjectsWithIdsAsItem(List<Integer> ids);

  List<T> getObjectsWithSource(String source);

  T getObjectWithId(int id);

  T getObjectWithName(String name);

  void deleteAll();
}

