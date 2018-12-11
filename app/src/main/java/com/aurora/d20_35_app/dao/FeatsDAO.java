package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Feats;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class FeatsDAO  implements BaseDAO<Feats> {

    @Query("SELECT * FROM Feats")
    public abstract List<Feats> getFeats();

    @Query("SELECT DISTINCT Source FROM Feats")
    public abstract List<String> getSources();


    @Query("DELETE FROM Feats")
    public abstract void deleteAll();
}
