package com.aurora.core.database.dao;

import static java.lang.Math.toIntExact;

import androidx.room.Room;
import androidx.room.Transaction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.Arrays;
import java.util.List;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.models.usables.Armour;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ArmourDaOTest {

  private static final Armour ARMOUR1 = new Armour("name1", "source1", "1=idBqp1", "price1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
  private static final Armour ARMOUR2 = new Armour("name2", "source2", "2=idBqp2", "price2", "2", "2", "2", "2", "2", "2", "2", "2", "2");
  private static final Armour ARMOUR3 = new Armour("name3", "source3", "3=idBqp3", "price3", "3", "3", "3", "3", "3", "3", "3", "3", "3");
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
  @Transaction
  public void getItemWhenNoItemInserted() {
    Assert.assertTrue(mDatabaseHolder.armourDaO().getAllObjectsAsObject().isEmpty());
  }

  @Test
  public void insertAndGetItem() {
    long id1 = mDatabaseHolder.armourDaO().insert(ARMOUR1);
    ARMOUR1.setItemID(toIntExact(id1));

    Assert.assertEquals(1, toIntExact(id1));
    Assert.assertEquals(ARMOUR1, mDatabaseHolder.armourDaO().getObjectWithId(toIntExact(id1)));
  }

  @Test
  public void insertAndGetItems() {
    long id1 = mDatabaseHolder.armourDaO().insert(ARMOUR1);
    ARMOUR1.setItemID(toIntExact(id1));

    List idList = mDatabaseHolder.armourDaO().insertAll(Arrays.asList(ARMOUR2, ARMOUR3));
    ARMOUR2.setItemID(toIntExact((long) idList.get(0)));
    ARMOUR3.setItemID(toIntExact((long) idList.get(1)));

    Assert.assertEquals(ARMOUR1, mDatabaseHolder.armourDaO().getObjectWithId(toIntExact(id1)));
    Assert.assertEquals(ARMOUR2, mDatabaseHolder.armourDaO().getObjectWithId(toIntExact((long) idList.get(0))));
    Assert.assertEquals(ARMOUR3, mDatabaseHolder.armourDaO().getObjectWithId(toIntExact((long) idList.get(1))));
  }

  @Test
  public void updateAndGetItem() {
    long id1 = mDatabaseHolder.armourDaO().insert(ARMOUR1);
    Armour updatedArmour = ARMOUR1.clone();
    updatedArmour.setName("updatedName");
    updatedArmour.setItemID(toIntExact(id1));
    mDatabaseHolder.armourDaO().update(updatedArmour);
    Assert.assertEquals(updatedArmour, mDatabaseHolder.armourDaO().getObjectWithId(toIntExact(id1)));
  }

  @Test
  public void deleteAllAndGetItem() {
    mDatabaseHolder.armourDaO().insert(ARMOUR1);
    mDatabaseHolder.armourDaO().deleteAll();
    Assert.assertTrue(mDatabaseHolder.armourDaO().getAllObjectsAsObject().isEmpty());
  }

  @Test
  public void countItems() {
    mDatabaseHolder.armourDaO().insertAll(Arrays.asList(ARMOUR1, ARMOUR2, ARMOUR3));
    Assert.assertEquals(3, mDatabaseHolder.armourDaO().countAllItems());
  }

  @Test
  public void getIds() {
    long id1 = mDatabaseHolder.armourDaO().insert(ARMOUR1);
    List idList = mDatabaseHolder.armourDaO().insertAll(Arrays.asList(ARMOUR2, ARMOUR3));
    Assert.assertEquals(1, toIntExact(id1));
    Assert.assertEquals(2, toIntExact((long) idList.get(0)));
    Assert.assertEquals(3, toIntExact((long) idList.get(1)));
  }
}