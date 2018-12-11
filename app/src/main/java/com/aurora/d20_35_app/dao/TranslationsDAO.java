package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Translations;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class TranslationsDAO  implements BaseDAO<Translations> {
    @Query("SELECT * FROM Translations")
    public abstract List<Translations> getTranslations();

    @Query("SELECT DISTINCT Source FROM Translations")
    public abstract List<String> getSources();

    @Query("DELETE FROM Translations")
    public abstract void deleteAll();
}
