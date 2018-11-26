package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Feats;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface FeatsDAO {

    @Query("SELECT * FROM Feats")
    List<Feats> getFeats();

    @Query("SELECT DISTINCT Source FROM Feats")
    List<String> getSources();
}
