package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Feats;
import com.aurora.d20_35_app.utilsDatabase.DatabaseHolder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static java.lang.Math.toIntExact;

@RunWith(AndroidJUnit4.class)
public class FeatsDAOTest {
    private static final Feats FEATS1 = new Feats("name1", "source1");
    private static final Feats FEATS2 = new Feats("name2", "source2");
    private static final Feats FEATS3 = new Feats("name3", "source3");
    private DatabaseHolder mDatabaseHolder;

    @Before
    public void setUp() throws Exception {
        mDatabaseHolder = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                DatabaseHolder.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        mDatabaseHolder.featsDAO().deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        mDatabaseHolder.featsDAO().deleteAll();
        mDatabaseHolder.close();
    }

    @Test
    public void getItemWhenNoItemInserted() {
        Assert.assertTrue(mDatabaseHolder.featsDAO().getItems().isEmpty());
    }

    @Test
    public void insertAndGetItem() {
        long id1 = mDatabaseHolder.featsDAO().insert(FEATS1);
        FEATS1.setItemID(toIntExact(id1));

        Assert.assertEquals(1, toIntExact(id1));
        Assert.assertEquals(mDatabaseHolder.featsDAO().getItemWithId(toIntExact(id1)), FEATS1);
    }

    @Test
    public void insertAndGetItems() {
        long id1 = mDatabaseHolder.featsDAO().insert(FEATS1);
        FEATS1.setItemID(toIntExact(id1));

        List idList = mDatabaseHolder.featsDAO().insertAll(Arrays.asList(FEATS2, FEATS3));
        FEATS2.setItemID(toIntExact((long) idList.get(0)));
        FEATS3.setItemID(toIntExact((long) idList.get(1)));

        Assert.assertEquals(FEATS1, mDatabaseHolder.featsDAO().getItemWithId(toIntExact(id1)));
        Assert.assertEquals(FEATS2, mDatabaseHolder.featsDAO().getItemWithId(toIntExact((long) idList.get(0))));
        Assert.assertEquals(FEATS3, mDatabaseHolder.featsDAO().getItemWithId(toIntExact((long) idList.get(1))));
    }

    @Test
    public void updateAndGetItem() {
        long id1 = mDatabaseHolder.featsDAO().insert(FEATS1);
        Feats updatedFeats = FEATS1.clone();
        updatedFeats.setName("updatedName");
        updatedFeats.setItemID(toIntExact(id1));
        mDatabaseHolder.featsDAO().update(updatedFeats);
        Assert.assertEquals(updatedFeats, mDatabaseHolder.featsDAO().getItemWithId(toIntExact(id1)));
    }

    @Test
    public void deleteAllAndGetItem() {
        mDatabaseHolder.featsDAO().insert(FEATS1);
        mDatabaseHolder.featsDAO().deleteAll();
        Assert.assertTrue(mDatabaseHolder.featsDAO().getItems().isEmpty());
    }

    @Test
    public void countItems() {
        mDatabaseHolder.featsDAO().insertAll(Arrays.asList(FEATS1, FEATS2, FEATS3));
        Assert.assertEquals(3, mDatabaseHolder.featsDAO().countItems());
    }

    @Test
    public void getIds() {
        long id1 = mDatabaseHolder.featsDAO().insert(FEATS1);
        List idList = mDatabaseHolder.featsDAO().insertAll(Arrays.asList(FEATS2, FEATS3));
        Assert.assertEquals(1, toIntExact(id1));
        Assert.assertEquals(2, toIntExact((long) idList.get(0)));
        Assert.assertEquals(3, toIntExact((long) idList.get(1)));
    }
}