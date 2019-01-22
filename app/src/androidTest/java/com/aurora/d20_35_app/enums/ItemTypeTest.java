package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.models.Armour;
import com.aurora.d20_35_app.models.Classes;
import com.aurora.d20_35_app.utils.database.DatabaseHolder;
import com.aurora.d20_35_app.utils.database.DatabaseManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import static com.aurora.d20_35_app.enums.ItemType.Armour;
import static com.aurora.d20_35_app.enums.ItemType.Classes;
import static com.aurora.d20_35_app.enums.ItemType.contains;

public class ItemTypeTest {

    private static final Armour ARMOUR1 = new Armour("name1", "source1", "price1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
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
        Assert.assertEquals("Armour", Armour.toString());
        Assert.assertNotEquals("Classes", Armour.toString());
        Assert.assertNotEquals("Armour", Classes.toString());
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains("Armour"));
        Assert.assertFalse(contains("RandomText"));
    }

    @Test
    public void getDAOTest() {
        Assert.assertEquals(mDatabaseHolder.armourDAO(), Armour.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.classesDAO(), Armour.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.armourDAO(), Classes.getDAO(mDatabaseHolder));
    }

    @Test
    public void getDatabaseListTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_LIST.add(ARMOUR1);
        Assert.assertEquals(mDatabaseHolder.ARMOUR_LIST, Armour.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.CLASSES_LIST, Armour.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.ARMOUR_LIST, Classes.getDatabaseList(mDatabaseHolder));
    }

    @Test
    public void getDatabaseMapTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_MAP.put(1, ARMOUR1);
        Assert.assertEquals(mDatabaseHolder.ARMOUR_MAP, Armour.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.CLASSES_MAP, Armour.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.ARMOUR_MAP, Classes.getDatabaseMap(mDatabaseHolder));
    }

    @Test
    public void getAllFromDatabaseTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        Assert.assertEquals(Armour.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.armourDAO().getItems());
        Assert.assertNotEquals(Classes.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.armourDAO().getItems());
        Assert.assertNotEquals(Armour.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.classesDAO().getItems());
    }

    @Test
    public void getNewObjectTest() {
        Assert.assertEquals(com.aurora.d20_35_app.models.Armour.class, Armour.getNewObject().getClass());
        Assert.assertEquals(new Armour(), Armour.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.Classes.class, Armour.getNewObject().getClass());
        Assert.assertNotEquals(new Classes(), Armour.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.Armour.class, Classes.getNewObject().getClass());
        Assert.assertNotEquals(new Armour(), Classes.getNewObject());
    }

    @Test
    public void fromHolderToDatabaseTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_LIST.add(ARMOUR1);
        mDatabaseHolder.ARMOUR_MAP.put(1, ARMOUR1);
        Armour.fromHolderToDatabase(mDatabaseHolder);
        Assert.assertEquals(mDatabaseHolder.ARMOUR_LIST.get(0), mDatabaseHolder.armourDAO().getItemWithId(1));
        Assert.assertEquals(mDatabaseHolder.ARMOUR_MAP.get(1), mDatabaseHolder.armourDAO().getItemWithId(1));
    }

    @Test
    public void fromDatabaseToHolderTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        Armour.fromDatabaseToHolder(mDatabaseHolder);
        Assert.assertEquals(mDatabaseHolder.armourDAO().getItemWithId(1), mDatabaseHolder.ARMOUR_LIST.get(0));
        Assert.assertEquals(mDatabaseHolder.armourDAO().getItemWithId(1), mDatabaseHolder.ARMOUR_MAP.get(1));
    }

    @Test
    public void deleteAllTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        mDatabaseHolder.ARMOUR_LIST.add(ARMOUR1);
        mDatabaseHolder.ARMOUR_MAP.put(1, ARMOUR1);
        Assert.assertFalse(mDatabaseHolder.armourDAO().getItems().isEmpty());
        Assert.assertFalse(mDatabaseHolder.ARMOUR_LIST.isEmpty());
        Assert.assertFalse(mDatabaseHolder.ARMOUR_MAP.isEmpty());
        Armour.deleteAll(mDatabaseHolder);
        Assert.assertTrue(mDatabaseHolder.armourDAO().getItems().isEmpty());
        Assert.assertTrue(mDatabaseHolder.ARMOUR_LIST.isEmpty());
        Assert.assertTrue(mDatabaseHolder.ARMOUR_MAP.isEmpty());
    }

    @Test
    public void deleteAllFromHolderTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_LIST.add(ARMOUR1);
        mDatabaseHolder.ARMOUR_MAP.put(1, ARMOUR1);
        Assert.assertFalse(mDatabaseHolder.ARMOUR_LIST.isEmpty());
        Assert.assertFalse(mDatabaseHolder.ARMOUR_MAP.isEmpty());
        Armour.deleteAllFromHolder(mDatabaseHolder);
        Assert.assertTrue(mDatabaseHolder.ARMOUR_LIST.isEmpty());
        Assert.assertTrue(mDatabaseHolder.ARMOUR_MAP.isEmpty());
    }

    @Test
    public void deleteAllFromDatabaseTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        Assert.assertFalse(mDatabaseHolder.armourDAO().getItems().isEmpty());
        Armour.deleteAllFromDatabase(mDatabaseHolder);
        Assert.assertTrue(mDatabaseHolder.armourDAO().getItems().isEmpty());
    }
}