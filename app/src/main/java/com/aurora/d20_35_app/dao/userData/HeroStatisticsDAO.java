package com.aurora.d20_35_app.dao.userData;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.userData.HeroStatistics;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public abstract class HeroStatisticsDAO implements BaseDAO<HeroStatistics> {

    @Query("SELECT COUNT(*) from HeroStatistics")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM HeroStatistics")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM HeroStatistics")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM HeroStatistics")
    public abstract List<String> getSources();

    @Transaction
    public List<HeroStatistics> getItemWithSuperFields() {
        ArrayList<HeroStatistics> result = new ArrayList<>(getItems());
        ArrayList<Item> resultItem = new ArrayList<>(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM HeroStatistics")
    public abstract List<HeroStatistics> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM HeroStatistics")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM HeroStatistics WHERE Source == :source")
    public abstract List<HeroStatistics> getItemsWithSource(String source);

    @Query("SELECT * FROM HeroStatistics WHERE Item_ID == :itemID")
    public abstract HeroStatistics getItemWithId(int itemID);

    @Query("SELECT * FROM HeroStatistics WHERE Name == :name")
    public abstract HeroStatistics getItemWithName(String name);

    @Query("DELETE FROM HeroStatistics")
    public abstract void deleteAll();

    @Transaction
    public List<HeroStatistics> getHeroStatisticsWithSuperFieldsWithParentIdIn(List<Integer> heroes_player_id_list) {
        ArrayList<HeroStatistics> result = new ArrayList<>(getHeroStatisticsWithParentIdIn(heroes_player_id_list));
        ArrayList<Item> resultItem = new ArrayList<>(getHeroStatisticsAsItemWithParentIdIn(heroes_player_id_list));
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM HeroStatistics WHERE Parent_Item_ID == :PatentId")
    public abstract HeroStatistics getHeroStatisticsWithParentId(Integer PatentId);

    @Query("SELECT * FROM HeroStatistics WHERE Parent_Item_ID IN (:heroes_player_id_list)")
    public abstract List<HeroStatistics> getHeroStatisticsWithParentIdIn(List<Integer> heroes_player_id_list);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM HeroStatistics WHERE Parent_Item_ID IN (:heroes_player_id_list)")
    public abstract List<Item> getHeroStatisticsAsItemWithParentIdIn(List<Integer> heroes_player_id_list);
}
