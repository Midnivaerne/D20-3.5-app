package com.aurora.core.database.dao;

import static java.lang.Math.toIntExact;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.Arrays;
import java.util.List;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.models.settingspecific.Feats;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FeatsDAOTest {
    private static final Feats FEATS1 = new Feats("name1", "source1", "1=name1");
    private static final Feats FEATS2 = new Feats("name2", "source2", "2=name2");
    private static final Feats FEATS3 = new Feats("name3", "source3", "3=name3");
    private DatabaseHolder mDatabaseHolder;

    @Before
    public void setUp() throws Exception {
        mDatabaseHolder = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                DatabaseHolder.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        DatabaseManager.clearWholeDatabaseAndAllHolders(mDatabaseHolder);
    }

    @After
    public void tearDown() throws Exception {
        DatabaseManager.clearWholeDatabaseAndAllHolders(mDatabaseHolder);
        DatabaseManager.closeDatabase(mDatabaseHolder);
    }

    @Test
    public void getItemWhenNoItemInserted() {
      Assert.assertTrue(mDatabaseHolder.featsDaO().getAllObjectsAsObject().isEmpty());
    }

    @Test
    public void insertAndGetItem() {
      long id1 = mDatabaseHolder.featsDaO().insert(FEATS1);
        FEATS1.setItemID(toIntExact(id1));

        Assert.assertEquals(1, toIntExact(id1));
      Assert.assertEquals(FEATS1, mDatabaseHolder.featsDaO().getObjectWithId(toIntExact(id1)));
    }

    @Test
    public void insertAndGetItems() {
      long id1 = mDatabaseHolder.featsDaO().insert(FEATS1);
        FEATS1.setItemID(toIntExact(id1));

      List idList = mDatabaseHolder.featsDaO().insertAll(Arrays.asList(FEATS2, FEATS3));
        FEATS2.setItemID(toIntExact((long) idList.get(0)));
        FEATS3.setItemID(toIntExact((long) idList.get(1)));

      Assert.assertEquals(FEATS1, mDatabaseHolder.featsDaO().getObjectWithId(toIntExact(id1)));
      Assert.assertEquals(FEATS2, mDatabaseHolder.featsDaO().getObjectWithId(toIntExact((long) idList.get(0))));
      Assert.assertEquals(FEATS3, mDatabaseHolder.featsDaO().getObjectWithId(toIntExact((long) idList.get(1))));
    }

    @Test
    public void updateAndGetItem() {
      long id1 = mDatabaseHolder.featsDaO().insert(FEATS1);
        Feats updatedFeats = FEATS1.clone();
        updatedFeats.setName("updatedName");
        updatedFeats.setItemID(toIntExact(id1));
      mDatabaseHolder.featsDaO().update(updatedFeats);
      Assert.assertEquals(updatedFeats, mDatabaseHolder.featsDaO().getObjectWithId(toIntExact(id1)));
    }

    @Test
    public void deleteAllAndGetItem() {
      mDatabaseHolder.featsDaO().insert(FEATS1);
      mDatabaseHolder.featsDaO().deleteAll();
      Assert.assertTrue(mDatabaseHolder.featsDaO().getAllObjectsAsObject().isEmpty());
    }

    @Test
    public void countItems() {
      mDatabaseHolder.featsDaO().insertAll(Arrays.asList(FEATS1, FEATS2, FEATS3));
      Assert.assertEquals(3, mDatabaseHolder.featsDaO().countAllItems());
    }

    @Test
    public void getIds() {
      long id1 = mDatabaseHolder.featsDaO().insert(FEATS1);
      List idList = mDatabaseHolder.featsDaO().insertAll(Arrays.asList(FEATS2, FEATS3));
        Assert.assertEquals(1, toIntExact(id1));
        Assert.assertEquals(2, toIntExact((long) idList.get(0)));
        Assert.assertEquals(3, toIntExact((long) idList.get(1)));
    }
}