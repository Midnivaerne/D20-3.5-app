package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.Equipment;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public abstract class EquipmentDAO  implements BaseDAO<Equipment> {

    @Query("SELECT * FROM Equipment")
    public abstract List<Equipment> getEquipment();

    @Query("SELECT DISTINCT Source FROM Equipment")
    public abstract List<String> getSources();

    @Query("DELETE FROM Equipment")
    public abstract void deleteAll();
}
