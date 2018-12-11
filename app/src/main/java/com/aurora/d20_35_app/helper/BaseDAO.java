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

    List <String> getSources();
}

