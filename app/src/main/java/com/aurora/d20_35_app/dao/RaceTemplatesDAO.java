package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.RaceTemplates;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class RaceTemplatesDAO  implements BaseDAO<RaceTemplates> {

    @Query("SELECT * FROM RaceTemplates")
    public abstract List<RaceTemplates> getRaceTemplates();

    @Query("SELECT DISTINCT Source FROM RaceTemplates")
    public abstract List<String> getSources();

    @Query("DELETE FROM RaceTemplates")
    public abstract void deleteAll();
}
