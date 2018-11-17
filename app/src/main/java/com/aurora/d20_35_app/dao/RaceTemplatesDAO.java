package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.RaceTemplates;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface RaceTemplatesDAO {

    @Query("SELECT * FROM RaceTemplates")
    List<RaceTemplates> getRaceTemplates();
}
