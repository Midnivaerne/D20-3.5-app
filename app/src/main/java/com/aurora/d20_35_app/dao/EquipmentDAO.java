package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Equipment;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface EquipmentDAO {

    @Query("SELECT * FROM Equipment")
    List<Equipment> getEquipment();
}
