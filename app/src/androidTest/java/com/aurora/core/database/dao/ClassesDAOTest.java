package com.aurora.core.database.dao;

import static java.lang.Math.toIntExact;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.Arrays;
import java.util.List;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.database.models.settingspecific.Classes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ClassesDAOTest {

  private static final Classes CLASSES1 = new Classes("name1", "source1", "1=name1");
  private static final Classes CLASSES2 = new Classes("name2", "source2", "2=name2");
  private static final Classes CLASSES3 = new Classes("name3", "source3", "3=name3");
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
    Assert.assertTrue(mDatabaseHolder.classesDaO().getAllObjectsAsObject().isEmpty());
  }

  @Test
  public void insertAndGetItem() {
    long id1 = mDatabaseHolder.classesDaO().insert(CLASSES1);
    CLASSES1.setItemID(toIntExact(id1));

    Assert.assertEquals(1, toIntExact(id1));
    Assert.assertEquals(CLASSES1, mDatabaseHolder.classesDaO().getObjectWithId(toIntExact(id1)));
  }

  @Test
  public void insertAndGetItems() {
    long id1 = mDatabaseHolder.classesDaO().insert(CLASSES1);
    CLASSES1.setItemID(toIntExact(id1));

    List idList = mDatabaseHolder.classesDaO().insertAll(Arrays.asList(CLASSES2, CLASSES3));
    CLASSES2.setItemID(toIntExact((long) idList.get(0)));
    CLASSES3.setItemID(toIntExact((long) idList.get(1)));

    Assert.assertEquals(CLASSES1, mDatabaseHolder.classesDaO().getObjectWithId(toIntExact(id1)));
    Assert.assertEquals(CLASSES2, mDatabaseHolder.classesDaO().getObjectWithId(toIntExact((long) idList.get(0))));
    Assert.assertEquals(CLASSES3, mDatabaseHolder.classesDaO().getObjectWithId(toIntExact((long) idList.get(1))));
  }

  @Test
  public void updateAndGetItem() {
    long id1 = mDatabaseHolder.classesDaO().insert(CLASSES1);
    Classes updatedClasses = CLASSES1.clone();
    updatedClasses.setName("updatedName");
    updatedClasses.setItemID(toIntExact(id1));
    mDatabaseHolder.classesDaO().update(updatedClasses);
    Assert.assertEquals(updatedClasses, mDatabaseHolder.classesDaO().getObjectWithId(toIntExact(id1)));
  }

  @Test
  public void deleteAllAndGetItem() {
    mDatabaseHolder.classesDaO().insert(CLASSES1);
    mDatabaseHolder.classesDaO().deleteAll();
    Assert.assertTrue(mDatabaseHolder.classesDaO().getAllObjectsAsObject().isEmpty());
  }

  @Test
  public void countItems() {
    mDatabaseHolder.classesDaO().insertAll(Arrays.asList(CLASSES1, CLASSES2, CLASSES3));
    Assert.assertEquals(3, mDatabaseHolder.classesDaO().countAllItems());
  }

  @Test
  public void getIds() {
    long id1 = mDatabaseHolder.classesDaO().insert(CLASSES1);
    List idList = mDatabaseHolder.classesDaO().insertAll(Arrays.asList(CLASSES2, CLASSES3));
    Assert.assertEquals(1, toIntExact(id1));
    Assert.assertEquals(2, toIntExact((long) idList.get(0)));
    Assert.assertEquals(3, toIntExact((long) idList.get(1)));
  }
}