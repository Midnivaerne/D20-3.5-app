package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Translations;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface TranslationsDAO {
    @Query("SELECT * FROM Translations")
    List<Translations> getTranslations();

    @Query("SELECT DISTINCT Source FROM Translations")
    List<String> getSources();
}
