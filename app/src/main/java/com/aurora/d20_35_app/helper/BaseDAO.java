package com.aurora.d20_35_app.helper;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;


public interface BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)//or replace?
    void insert(T object);

    @Insert(onConflict = OnConflictStrategy.IGNORE)//or replace?
    void insertAll(List<T> objects);

    @Update
    void update(T object);

    @Delete
    void delete(T object);

    int countItems();

    List<Integer> getIds();

    List<String> getNames();

    List<String> getSources();

    List<T> getItems();

    List<Item> getItemsAsItem();

    List<T> getItemsWithSource(String source);

    T getItemWithId(String id);

    T getItemWithName(String name);

    void deleteAll();
}

