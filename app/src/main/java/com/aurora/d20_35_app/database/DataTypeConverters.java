package com.aurora.d20_35_app.database;

import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.settingSpecific.Races;

import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

public class DataTypeConverters {

    @TypeConverter
    public List<Races> racesListFromItemList(List<Item> list) {
        List<Races> tmp = new ArrayList<Races>();
        for (int i = 0; i < list.size(); i++) {
            tmp.add((Races) list.get(i));
        }
        return tmp;
    }

}
