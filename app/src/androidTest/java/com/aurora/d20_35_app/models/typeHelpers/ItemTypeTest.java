package com.aurora.d20_35_app.models.typeHelpers;

import com.aurora.d20_35_app.models.usables.Armour;
import com.aurora.d20_35_app.models.settingSpecific.Classes;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.database.DatabaseManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import static com.aurora.d20_35_app.models.typeHelpers.ItemType.ARMOUR;
import static com.aurora.d20_35_app.models.typeHelpers.ItemType.CLASSES;
import static com.aurora.d20_35_app.models.typeHelpers.ItemType.contains;

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
        Assert.assertEquals(mDatabaseHolder.armourDAO(), ARMOUR.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.classesDAO(), ARMOUR.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.armourDAO(), ARMOUR.getDAO(mDatabaseHolder));
    }

    @Test
    public void getDatabaseListTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_LIST.add(ARMOUR1);
        Assert.assertEquals(mDatabaseHolder.ARMOUR_LIST, ARMOUR.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.CLASSES_LIST, ARMOUR.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.ARMOUR_LIST, CLASSES.getDatabaseList(mDatabaseHolder));
    }

    @Test
    public void getDatabaseMapTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_MAP.put(1, ARMOUR1);
        Assert.assertEquals(mDatabaseHolder.ARMOUR_MAP, ARMOUR.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.CLASSES_MAP, ARMOUR.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.ARMOUR_MAP, CLASSES.getDatabaseMap(mDatabaseHolder));
    }

    @Test
    public void getAllFromDatabaseTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        Assert.assertEquals(ARMOUR.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.armourDAO().getItems());
        Assert.assertNotEquals(CLASSES.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.armourDAO().getItems());
        Assert.assertNotEquals(ARMOUR.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.classesDAO().getItems());
    }

    @Test
    public void getNewObjectTest() {
        Assert.assertEquals(com.aurora.d20_35_app.models.usables.Armour.class, ARMOUR.getNewObject().getClass());
        Assert.assertEquals(new Armour(), ARMOUR.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.settingSpecific.Classes.class, ARMOUR.getNewObject().getClass());
        Assert.assertNotEquals(new Classes(), ARMOUR.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.usables.Armour.class, CLASSES.getNewObject().getClass());
        Assert.assertNotEquals(new Armour(), CLASSES.getNewObject());
    }

    @Test
    public void fromHolderToDatabaseTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.ARMOUR_LIST.add(ARMOUR1);
        mDatabaseHolder.ARMOUR_MAP.put(1, ARMOUR1);
        ARMOUR.fromHolderToDatabase(mDatabaseHolder);
        Assert.assertEquals(mDatabaseHolder.ARMOUR_LIST.get(0), mDatabaseHolder.armourDAO().getItemWithId(1));
        Assert.assertEquals(mDatabaseHolder.ARMOUR_MAP.get(1), mDatabaseHolder.armourDAO().getItemWithId(1));
    }

    @Test
    public void fromDatabaseToHolderTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        ARMOUR.fromDatabaseToHolder(mDatabaseHolder);
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
        ARMOUR.deleteAll(mDatabaseHolder);
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
        ARMOUR.deleteAllFromHolder(mDatabaseHolder);
        Assert.assertTrue(mDatabaseHolder.ARMOUR_LIST.isEmpty());
        Assert.assertTrue(mDatabaseHolder.ARMOUR_MAP.isEmpty());
    }

    @Test
    public void deleteAllFromDatabaseTest() {
        ARMOUR1.setItemID(1);
        mDatabaseHolder.armourDAO().insert(ARMOUR1);
        Assert.assertFalse(mDatabaseHolder.armourDAO().getItems().isEmpty());
        ARMOUR.deleteAllFromDatabase(mDatabaseHolder);
        Assert.assertTrue(mDatabaseHolder.armourDAO().getItems().isEmpty());
    }
}