package com.aurora.d20_35_app.dao.userData;

import com.aurora.d20_35_app.helper.BaseDAO;
import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.userData.HeroDescription;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;

@Dao
public abstract class HeroDescriptionDAO implements BaseDAO<HeroDescription> {

    @Query("SELECT COUNT(*) from HeroDescription")
    public abstract int countItems();

    @Query("SELECT Item_ID FROM HeroDescription")
    public abstract List<Integer> getIds();

    @Query("SELECT Name FROM HeroDescription")
    public abstract List<String> getNames();

    @Query("SELECT DISTINCT Source FROM HeroDescription")
    public abstract List<String> getSources();

    @Transaction
    public List<HeroDescription> getItemWithSuperFields() {
        ArrayList<HeroDescription> result = new ArrayList<>();
        result.addAll(getItems());
        ArrayList<Item> resultItem = new ArrayList<>();
        resultItem.addAll(getItemsAsItem());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM HeroDescription")
    public abstract List<HeroDescription> getItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM HeroDescription")
    public abstract List<Item> getItemsAsItem(); // above doesn't show Item fields (but they are created/loaded)

    @Query("SELECT * FROM HeroDescription WHERE Source == :source")
    public abstract List<HeroDescription> getItemsWithSource(String source);

    @Query("SELECT * FROM HeroDescription WHERE Item_ID == :itemID")
    public abstract HeroDescription getItemWithId(int itemID);

    @Query("SELECT * FROM HeroDescription WHERE Name == :name")
    public abstract HeroDescription getItemWithName(String name);

    @Query("DELETE FROM HeroDescription")
    public abstract void deleteAll();

    @Transaction
    public List<HeroDescription> getHeroDescriptionsWithSuperFieldsWithParentIdIn(List<Integer> heroes_player_id_list) {
        ArrayList<HeroDescription> result = new ArrayList<>();
        result.addAll(getHeroDescriptionsWithParentIdIn(heroes_player_id_list));
        ArrayList<Item> resultItem = new ArrayList<>();
        resultItem.addAll(getHeroDescriptionsAsItemWithParentIdIn(heroes_player_id_list));
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setItemID(resultItem.get(i).getItemID());
            result.get(i).setName(resultItem.get(i).getName());
            result.get(i).setSource(resultItem.get(i).getSource());
            result.get(i).setIdAsNameBackup(resultItem.get(i).getIdAsNameBackup());
        }
        return result;
    }

    @Query("SELECT * FROM HeroDescription WHERE ParentItemID == :PatentId")
    public abstract HeroDescription getHeroDescriptionWithParentId(Integer PatentId);

    @Query("SELECT * FROM HeroDescription WHERE ParentItemID IN (:heroes_player_id_list)")
    public abstract List<HeroDescription> getHeroDescriptionsWithParentIdIn(List<Integer> heroes_player_id_list);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM HeroDescription WHERE ParentItemID IN (:heroes_player_id_list)")
    public abstract List<Item> getHeroDescriptionsAsItemWithParentIdIn(List<Integer> heroes_player_id_list);
}
