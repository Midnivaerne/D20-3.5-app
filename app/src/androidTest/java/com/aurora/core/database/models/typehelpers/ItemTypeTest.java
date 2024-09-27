package com.aurora.core.database.models.typehelpers;

import static com.aurora.core.database.models.typehelpers.CoreTypeHelper.contains;
import static com.aurora.core.database.models.typehelpers.ItemType.ARMOUR;
import static com.aurora.core.database.models.typehelpers.ItemType.CLASSES;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.database.models.settingspecific.Classes;
import com.aurora.core.database.models.usables.Armour;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTypeTest {

    private static final Armour ARMOUR1 = new Armour("name1", "source1", "price1", "1", "1", "1", "1", "1", "1", "1", "1", "1","1");
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
    public void toStringTest() {
        Assert.assertEquals("Armour", ARMOUR.toString());
        Assert.assertNotEquals("Classes", ARMOUR.toString());
        Assert.assertNotEquals("Armour", CLASSES.toString());
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains("Armour",ItemType.class));
        Assert.assertFalse(contains("RandomText",ItemType.class));
    }

    @Test
    public void getDAOTest() {
      Assert.assertEquals(mDatabaseHolder.armourDaO(), ARMOUR.getDaO(mDatabaseHolder));
      Assert.assertNotEquals(mDatabaseHolder.classesDaO(), ARMOUR.getDaO(mDatabaseHolder));
      Assert.assertNotEquals(mDatabaseHolder.armourDaO(), ARMOUR.getDaO(mDatabaseHolder));
    }

    @Test
    public void getDatabaseListTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourList.add(ARMOUR1);
      Assert.assertEquals(mDatabaseHolder.armourList, ARMOUR.getDatabaseList(mDatabaseHolder));
      Assert.assertNotEquals(mDatabaseHolder.classesList, ARMOUR.getDatabaseList(mDatabaseHolder));
      Assert.assertNotEquals(mDatabaseHolder.armourList, CLASSES.getDatabaseList(mDatabaseHolder));
    }

    @Test
    public void getDatabaseMapTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourMap.put(1, ARMOUR1);
      Assert.assertEquals(mDatabaseHolder.armourMap, ARMOUR.getDatabaseMap(mDatabaseHolder));
      Assert.assertNotEquals(mDatabaseHolder.classesMap, ARMOUR.getDatabaseMap(mDatabaseHolder));
      Assert.assertNotEquals(mDatabaseHolder.armourMap, CLASSES.getDatabaseMap(mDatabaseHolder));
    }

    @Test
    public void getAllFromDatabaseTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourDaO().insert(ARMOUR1);
      Assert.assertEquals(ARMOUR.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.armourDaO().getAllObjectsAsObject());
      Assert.assertNotEquals(CLASSES.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.armourDaO().getAllObjectsAsObject());
      Assert.assertNotEquals(ARMOUR.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.classesDaO().getAllObjectsAsObject());
    }

    @Test
    public void getNewObjectTest() {
      Assert.assertEquals(com.aurora.core.database.models.usables.Armour.class, ARMOUR.getNewObject().getClass());
        Assert.assertEquals(new Armour(), ARMOUR.getNewObject());

      Assert.assertNotEquals(com.aurora.core.database.models.settingspecific.Classes.class, ARMOUR.getNewObject().getClass());
        Assert.assertNotEquals(new Classes(), ARMOUR.getNewObject());

      Assert.assertNotEquals(com.aurora.core.database.models.usables.Armour.class, CLASSES.getNewObject().getClass());
        Assert.assertNotEquals(new Armour(), CLASSES.getNewObject());
    }

    @Test
    public void fromHolderToDatabaseTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourList.add(ARMOUR1);
      mDatabaseHolder.armourMap.put(1, ARMOUR1);
        ARMOUR.fromHolderToDatabase(mDatabaseHolder);
      Assert.assertEquals(mDatabaseHolder.armourList.get(0), mDatabaseHolder.armourDaO().getObjectWithId(1));
      Assert.assertEquals(mDatabaseHolder.armourMap.get(1), mDatabaseHolder.armourDaO().getObjectWithId(1));
    }

    @Test
    public void fromDatabaseToHolderTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourDaO().insert(ARMOUR1);
        ARMOUR.fromDatabaseToHolder(mDatabaseHolder);
      Assert.assertEquals(mDatabaseHolder.armourDaO().getObjectWithId(1), mDatabaseHolder.armourList.get(0));
      Assert.assertEquals(mDatabaseHolder.armourDaO().getObjectWithId(1), mDatabaseHolder.armourMap.get(1));
    }

    @Test
    public void deleteAllTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourDaO().insert(ARMOUR1);
      mDatabaseHolder.armourList.add(ARMOUR1);
      mDatabaseHolder.armourMap.put(1, ARMOUR1);
      Assert.assertFalse(mDatabaseHolder.armourDaO().getAllObjectsAsObject().isEmpty());
      Assert.assertFalse(mDatabaseHolder.armourList.isEmpty());
      Assert.assertFalse(mDatabaseHolder.armourMap.isEmpty());
        ARMOUR.deleteAll(mDatabaseHolder);
      Assert.assertTrue(mDatabaseHolder.armourDaO().getAllObjectsAsObject().isEmpty());
      Assert.assertTrue(mDatabaseHolder.armourList.isEmpty());
      Assert.assertTrue(mDatabaseHolder.armourMap.isEmpty());
    }

    @Test
    public void deleteAllFromHolderTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourList.add(ARMOUR1);
      mDatabaseHolder.armourMap.put(1, ARMOUR1);
      Assert.assertFalse(mDatabaseHolder.armourList.isEmpty());
      Assert.assertFalse(mDatabaseHolder.armourMap.isEmpty());
        ARMOUR.deleteAllFromHolder(mDatabaseHolder);
      Assert.assertTrue(mDatabaseHolder.armourList.isEmpty());
      Assert.assertTrue(mDatabaseHolder.armourMap.isEmpty());
    }

    @Test
    public void deleteAllFromDatabaseTest() {
        ARMOUR1.setItemID(1);
      mDatabaseHolder.armourDaO().insert(ARMOUR1);
      Assert.assertFalse(mDatabaseHolder.armourDaO().getAllObjectsAsObject().isEmpty());
        ARMOUR.deleteAllFromDatabase(mDatabaseHolder);
      Assert.assertTrue(mDatabaseHolder.armourDaO().getAllObjectsAsObject().isEmpty());
    }
}