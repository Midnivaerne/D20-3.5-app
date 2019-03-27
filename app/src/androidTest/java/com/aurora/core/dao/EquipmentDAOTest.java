package com.aurora.core.dao;

import static java.lang.Math.toIntExact;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.Arrays;
import java.util.List;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.models.usables.Equipment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EquipmentDAOTest {

  private static final Equipment EQUIPMENT1 = new Equipment("name1", "source1", "1=name1");
  private static final Equipment EQUIPMENT2 = new Equipment("name2", "source2", "2=name2");
  private static final Equipment EQUIPMENT3 = new Equipment("name3", "source3", "3=name3");
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
    Assert.assertTrue(mDatabaseHolder.equipmentDaO().getAllObjectsAsObject().isEmpty());
  }

  @Test
  public void insertAndGetItem() {
    long id1 = mDatabaseHolder.equipmentDaO().insert(EQUIPMENT1);
    EQUIPMENT1.setItemID(toIntExact(id1));

    Assert.assertEquals(1, toIntExact(id1));
    Assert.assertEquals(EQUIPMENT1, mDatabaseHolder.equipmentDaO().getObjectWithId(toIntExact(id1)));
  }

  @Test
  public void insertAndGetItems() {
    long id1 = mDatabaseHolder.equipmentDaO().insert(EQUIPMENT1);
    EQUIPMENT1.setItemID(toIntExact(id1));

    List idList = mDatabaseHolder.equipmentDaO().insertAll(Arrays.asList(EQUIPMENT2, EQUIPMENT3));
    EQUIPMENT2.setItemID(toIntExact((long) idList.get(0)));
    EQUIPMENT3.setItemID(toIntExact((long) idList.get(1)));

    Assert.assertEquals(EQUIPMENT1, mDatabaseHolder.equipmentDaO().getObjectWithId(toIntExact(id1)));
    Assert.assertEquals(EQUIPMENT2, mDatabaseHolder.equipmentDaO().getObjectWithId(toIntExact((long) idList.get(0))));
    Assert.assertEquals(EQUIPMENT3, mDatabaseHolder.equipmentDaO().getObjectWithId(toIntExact((long) idList.get(1))));
  }

  @Test
  public void updateAndGetItem() {
    long id1 = mDatabaseHolder.equipmentDaO().insert(EQUIPMENT1);
    Equipment updatedEquipment = EQUIPMENT1.clone();
    updatedEquipment.setName("updatedName");
    updatedEquipment.setItemID(toIntExact(id1));
    mDatabaseHolder.equipmentDaO().update(updatedEquipment);
    Assert.assertEquals(updatedEquipment, mDatabaseHolder.equipmentDaO().getObjectWithId(toIntExact(id1)));
  }

  @Test
  public void deleteAllAndGetItem() {
    mDatabaseHolder.equipmentDaO().insert(EQUIPMENT1);
    mDatabaseHolder.equipmentDaO().deleteAll();
    Assert.assertTrue(mDatabaseHolder.equipmentDaO().getAllObjectsAsObject().isEmpty());
  }

  @Test
  public void countItems() {
    mDatabaseHolder.equipmentDaO().insertAll(Arrays.asList(EQUIPMENT1, EQUIPMENT2, EQUIPMENT3));
    Assert.assertEquals(3, mDatabaseHolder.equipmentDaO().countAllItems());
  }

  @Test
  public void getIds() {
    long id1 = mDatabaseHolder.equipmentDaO().insert(EQUIPMENT1);
    List idList = mDatabaseHolder.equipmentDaO().insertAll(Arrays.asList(EQUIPMENT2, EQUIPMENT3));
    Assert.assertEquals(1, toIntExact(id1));
    Assert.assertEquals(2, toIntExact((long) idList.get(0)));
    Assert.assertEquals(3, toIntExact((long) idList.get(1)));
  }
}