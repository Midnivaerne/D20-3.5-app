package com.aurora.d20_35_app.dao;

import com.aurora.d20_35_app.models.Classes;
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
public class ClassesDAOTest {

    private static final Classes CLASSES1 = new Classes("name1", "source1");
    private static final Classes CLASSES2 = new Classes("name2", "source2");
    private static final Classes CLASSES3 = new Classes("name3", "source3");
    private DatabaseHolder mDatabaseHolder;

    @Before
    public void setUp() throws Exception {
        mDatabaseHolder = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                DatabaseHolder.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        mDatabaseHolder.classesDAO().deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        mDatabaseHolder.classesDAO().deleteAll();
        mDatabaseHolder.close();
    }

    @Test
    public void getItemWhenNoItemInserted() {
        Assert.assertTrue(mDatabaseHolder.classesDAO().getItems().isEmpty());
    }

    @Test
    public void insertAndGetItem() {
        long id1 = mDatabaseHolder.classesDAO().insert(CLASSES1);
        CLASSES1.setItemID(toIntExact(id1));

        Assert.assertEquals(1, toIntExact(id1));
        Assert.assertEquals(mDatabaseHolder.classesDAO().getItemWithId(toIntExact(id1)), CLASSES1);
    }

    @Test
    public void insertAndGetItems() {
        long id1 = mDatabaseHolder.classesDAO().insert(CLASSES1);
        CLASSES1.setItemID(toIntExact(id1));

        List idList = mDatabaseHolder.classesDAO().insertAll(Arrays.asList(CLASSES2, CLASSES3));
        CLASSES2.setItemID(toIntExact((long) idList.get(0)));
        CLASSES3.setItemID(toIntExact((long) idList.get(1)));

        Assert.assertEquals(CLASSES1, mDatabaseHolder.classesDAO().getItemWithId(toIntExact(id1)));
        Assert.assertEquals(CLASSES2, mDatabaseHolder.classesDAO().getItemWithId(toIntExact((long) idList.get(0))));
        Assert.assertEquals(CLASSES3, mDatabaseHolder.classesDAO().getItemWithId(toIntExact((long) idList.get(1))));
    }

    @Test
    public void updateAndGetItem() {
        long id1 = mDatabaseHolder.classesDAO().insert(CLASSES1);
        Classes updatedClasses = CLASSES1.clone();
        updatedClasses.setName("updatedName");
        updatedClasses.setItemID(toIntExact(id1));
        mDatabaseHolder.classesDAO().update(updatedClasses);
        Assert.assertEquals(updatedClasses, mDatabaseHolder.classesDAO().getItemWithId(toIntExact(id1)));
    }

    @Test
    public void deleteAllAndGetItem() {
        mDatabaseHolder.classesDAO().insert(CLASSES1);
        mDatabaseHolder.classesDAO().deleteAll();
        Assert.assertTrue(mDatabaseHolder.classesDAO().getItems().isEmpty());
    }

    @Test
    public void countItems() {
        mDatabaseHolder.classesDAO().insertAll(Arrays.asList(CLASSES1, CLASSES2, CLASSES3));
        Assert.assertEquals(3, mDatabaseHolder.classesDAO().countItems());
    }

    @Test
    public void getIds() {
        long id1 = mDatabaseHolder.classesDAO().insert(CLASSES1);
        List idList = mDatabaseHolder.classesDAO().insertAll(Arrays.asList(CLASSES2, CLASSES3));
        Assert.assertEquals(1, toIntExact(id1));
        Assert.assertEquals(2, toIntExact((long) idList.get(0)));
        Assert.assertEquals(3, toIntExact((long) idList.get(1)));
    }
}